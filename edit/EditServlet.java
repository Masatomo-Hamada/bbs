package keijiban;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** 投稿編集画面 */
public class EditServlet extends HttpServlet {
	
	// GETリクエスト
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		perform(request,response);
	}
	
	// POSTリクエスト
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		perform(request,response);
	}
	
	// 処理実行
	private void perform(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// 文字コードの指定
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 他クラスのインスタンス化
		DisplaySetup setup = new DisplaySetup();
		HttpSession session = request.getSession();
		
		// 一覧表示処理への引数
		String value = null;
		
		// フォームから送信された値を取得
		String title = request.getParameter("title");
		String message = request.getParameter("message");
		String edit = request.getParameter("edit");
		String cancel = request.getParameter("cancel");
		
		// 投稿番号を取得
		String number = String.valueOf(session.getAttribute("number"));

		// エラーフラグ
		int ERROR = 0;
		
		//　遷移先パス
		String path = Constant.DISPLAY_PATH;

		// 編集ボタン処理
		if (edit != null) {

			// タイトルが未入力,または全角・半角スペースが入力された場合
			if (title == "" || title == " " || title == "　") {
				ERROR = 1;
				request.setAttribute("TITLE_ERROR", Constant.NO_ENTRY_TITLE);
				path = Constant.EDIT_PATH;

				// タイトルが全角31字以上の場合
			} else if (title.length() > 30) {
				ERROR = 1;
				request.setAttribute("TITLE_ERROR", Constant.OVER_ENTRY_TITLE);
				path = Constant.EDIT_PATH;
			}

				// 本文が未入力,または全角・半角スペースが入力された場合
			if (message == "" || message == " " || message == "　") {
				ERROR = 1;
				request.setAttribute("MESSAGE_ERROR", Constant.NO_ENTRY_MESSAGE);
				path = Constant.EDIT_PATH;

				// タイトルが全角31字以上の場合
			} else if (message.length() > 300) {
				ERROR = 1;
				request.setAttribute("MESSAGE_ERROR", Constant.OVER_ENTRY_MESSAGE);
				path = Constant.EDIT_PATH;
			}

		// キャンセルボタン処理
		} else if (cancel != null) {
			// 投稿データの再読み込み
			request.setAttribute("listData", setup.LoadList(value));

			// データが存在しない場合
			if (setup.LoadList(value).size() == 0) {
				request.setAttribute("ERROR", Constant.NOT_EXIST_LIST);
			}
			ERROR = 2;
		}

		// エラーチェックが正常の場合
		if (ERROR == 0) {
			
			// クエリの作成
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE TB_TOKO SET TOKO_TITLE = '");
			sb.append(title);
			sb.append("', TOKO_MESSAGE = '");
			sb.append(message);
			sb.append("' WHERE TB_TOKO.TOKO_NO = '");
			sb.append(number);
			sb.append("'");
			String query = new String(sb);
			
			// DB処理
			try (Connection con = DBManager.getConnection();
					Statement stm = con.createStatement()){
				
				// 投稿TBLのデータを更新
				stm.executeUpdate(query);
				
			// 例外処理
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 投稿データの再読み込み
			request.setAttribute("listData", setup.LoadList(value));

			// データが存在しない場合
			if (setup.LoadList(value).size() == 0) {
				request.setAttribute("ERROR", Constant.NOT_EXIST_LIST);
			}
			
		// エラーの場合
		} else if (ERROR == 1) {
			
			// 遷移先の指定（投稿編集画面）
			path = Constant.EDIT_PATH;
		}
		
		// 画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
