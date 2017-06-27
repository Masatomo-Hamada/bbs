package keijiban;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** 投稿一覧画面 */
public class DisplayServlet extends HttpServlet {

	// GETリクエスト
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		perform(request, response);
	}

	// POSTリクエスト
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		perform(request, response);
	}

	// 処理実行
	private void perform(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		DisplaySetup setup = new DisplaySetup();
		JavaBeans bean = new JavaBeans();

		String value = null;

		// ArrayListの取得
		ArrayList<JavaBeans> list = setup.LoadList(value);

		// 遷移先パス（デフォルト：投稿一覧画面）
		String path = Constant.DISPLAY_PATH;

		// 新規投稿ボタン処理
		if (request.getParameter("entry") != null) {

			// 遷移先の指定（新規投稿画面）
			path = Constant.ENTRY_PATH;
		}

		// 選択削除ボタン処理
		if (request.getParameter("delete") != null) {

			// 選択データのインデックス番号をリストに格納
			String[] indexList = request.getParameterValues("check");

			// 選択データありの場合
			if (indexList != null) {

				// ユーザーIDを取得
				String loginId = (String) session.getAttribute("loginId");
				String dataId = null;

				// ユーザーID認証チェック
				for (int i = 0; i < indexList.length; i++) {

					// 選択データのユーザーIDを取得
					dataId = list.get(i).getId();

					// ユーザーID認証がエラーの場合、処理終了
					if (!loginId.equals(dataId)) {

						// エラーメッセージの登録
						request.setAttribute("ERROR", Constant.NO_MATCH_ID);
						dataId = null;
						break;
					}
				}

				// ユーザーID認証が正常の場合
				if (dataId != null) {

					// 投稿番号リストの作成
					String[] numberList = new String[indexList.length];

					// 選択データの投稿番号をリストに格納
					for (int j = 0; j < indexList.length; j++) {
						numberList[j] = String.valueOf(list.get(Integer.parseInt(indexList[j])).getNumber());
					}

					// 投稿番号を「,」で結合
					String separator = ",";
					String DeleteNumber = String.join(separator, numberList);

					// クエリの作成
					StringBuilder sb = new StringBuilder();
					sb.append("DELETE FROM TB_TOKO WHERE TOKO_NO IN ()");
					sb.insert(38, DeleteNumber);
					String query = new String(sb);

					// DB処理
					try (Connection con = DBManager.getConnection(); Statement stm = con.createStatement()) {

						// 選択データを投稿TBLから削除
						stm.executeUpdate(query);

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				// 選択データなしの場合
			} else {

				// エラーメッセージの登録
				request.setAttribute("ERROR", Constant.NO_SELECT_MESSAGE);
			}
			// 投稿一覧画面初期処理（投稿データ取得）
			request.setAttribute("listData", setup.LoadList(value));

			//　データが存在しない場合
			if (setup.LoadList(value).size() == 0) {
				request.setAttribute("ERROR", Constant.NOT_EXIST_LIST);
			}
		}

		// 編集ボタン処理
		if (request.getParameter("edit") != null) {

			// フォームから送信されたインデックス番号を取得
			int index = Integer.parseInt(request.getParameter("edit"));

			// 投稿データを取得
			bean = list.get(index);

			// セッションスコープのユーザーIDを取得
			String loginId = (String) session.getAttribute("loginId");

			// 選択データのユーザーIDを取得
			String dataId = bean.getId();

			// ユーザーID認証が正常の場合
			if (loginId.equals(dataId)) {

				// 選択データのタイトル,本文,投稿番号を登録
				request.setAttribute("title", bean.getTitle());
				request.setAttribute("message", bean.getMessage());
				session.setAttribute("number", bean.getNumber());

				// 遷移先の指定（投稿編集画面）
				path = Constant.EDIT_PATH;

				// ユーザーID認証がエラーの場合
			} else {

				// エラーメッセージの登録
				request.setAttribute("ERROR", Constant.NO_MATCH_ID);
			}
			// 投稿一覧画面初期処理（投稿データ取得）
			request.setAttribute("listData", setup.LoadList(value));

			//　データが存在しない場合
			if (setup.LoadList(value).size() == 0) {
				request.setAttribute("ERROR", Constant.NOT_EXIST_LIST);
			}
		}

		// ソート処理
		if(request.getParameter("sort") != null){
			if (!"default".equals(request.getParameter("sort"))) {
				
				// フォームから送信された値を取得
				value = "ORDER BY tb." + request.getParameter("sort");
			}
			// ソート条件を登録
			session.setAttribute("sort", request.getParameter("sort"));

			// 投稿一覧画面初期処理（投稿データ取得）
			request.setAttribute("listData", setup.LoadList(value));

			//　データが存在しない場合
			if (setup.LoadList(value).size() == 0) {
				request.setAttribute("ERROR", Constant.NOT_EXIST_LIST);
			}
		}

		// 絞込み処理
		if (request.getParameter("search") != null) {
			
			// フォームから送信された値を取得
			String search_title = request.getParameter("search_title");
			String date = request.getParameter("date");
			String search_name = request.getParameter("search_name");
			String start_year = request.getParameter("start_year");
			String start_month = request.getParameter("start_month");
			String finish_year = request.getParameter("finish_year");
			String finish_month = request.getParameter("finish_month");
			
			session.setAttribute("search_title", search_title);
			session.setAttribute("search_name", search_name);
			
			// いずれか1つ以上絞込み条件が入力または選択されている場合
			if(search_title != "" || !"default".equals(search_name) || !"year".equals(start_year)){
	
				//　クエリの作成
				StringBuilder sb = new StringBuilder();
				sb.append("WHERE ");
				
				// タイトルが入力されている場合
				if (search_title != "") {
					sb.append("tb.TOKO_TITLE LIKE '%");
					sb.append(request.getParameter("search_title"));
					sb.append("%'");
				}
				
				// 投稿・更新日時が選択されている場合
				if (!"year".equals(start_year)) {
					
					// タイトルが入力されている場合
					if (search_title != "") {
						sb.append(" AND ");
					}
					sb.append("cast(tb.");
					sb.append(date);
					sb.append(" as DATE) BETWEEN '");
					sb.append(start_year);
					sb.append("-");
					sb.append(start_month);
					sb.append("-01' AND '");
					sb.append(finish_year);
					sb.append("-");
					sb.append(finish_month);
					sb.append("-31'");
				}
				
				// 投稿者が選択されている場合
				if (!"default".equals(search_name)) {
					
					// タイトルが入力されている、または投稿・更新日時が選択されている場合
					if(search_title != "" || !"year".equals(start_year)){
						sb.append(" AND ");
					}
					sb.append("mt.USER_NAME = '");
					sb.append(search_name);
					sb.append("'");
				}
				
				if(!"default".equals(session.getAttribute("sort"))){
					sb.append(" ORDER BY tb." + session.getAttribute("sort"));
				}
				value = new String(sb);
			}
			// 投稿一覧画面初期処理（投稿データ取得）
			request.setAttribute("listData", setup.LoadList(value));

			//　データが存在しない場合
			if (setup.LoadList(value).size() == 0) {
				request.setAttribute("ERROR", Constant.NOT_EXIST_LIST);
			}
		}
		// 画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
