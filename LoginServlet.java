package keijiban;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** ログイン画面サーブレット */
public class LoginServlet extends HttpServlet {

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

		DisplaySetup setup = new DisplaySetup();
		String value = null;

		// 遷移先パス（デフォルト：投稿一覧画面）
		String path = Constant.DISPLAY_PATH;

		// 入力値の取得
		String loginId = request.getParameter("loginId");
		String loginPass = request.getParameter("loginPass");

		// クエリの作成
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT USER_ID, USER_NAME FROM MT_USER WHERE USER_ID = '' AND PASSWORD = ''");
		sb.insert(56, loginId);
		sb.insert(77, loginPass);
		String query = new String(sb);

		// DB処理
		try (Connection con = DBManager.getConnection(); Statement stm = con.createStatement()) {

			// 入力値と一致するデータを取得（ログイン認証）
			ResultSet rs = stm.executeQuery(query);

			// ログイン認証が正常の場合
			if (rs.next()) {

				// 氏名を取得
				String name = rs.getString("USER_NAME");

				// ユーザーID・氏名を登録
				HttpSession session = request.getSession();
				session.setAttribute("loginId", loginId);
				session.setAttribute("name", name);

				// ソート条件,絞込み条件に初期値を登録
				session.setAttribute("sort", "default");
				session.setAttribute("search_title", "");
				session.setAttribute("search_name", "default");
				
				// 投稿一覧画面初期処理（投稿データ取得）
				request.setAttribute("listData", setup.LoadList(value));

				// データが存在しない場合
				if (setup.LoadList(value).size() == 0) {
					request.setAttribute("ERROR", Constant.NOT_EXIST_LIST);
				}

				// ログイン認証がエラーの場合
			} else {

				// エラーメッセージを登録
				request.setAttribute("ERROR", Constant.WRONG_ENTRY_LOGIN);

				// 遷移先の指定（ログイン画面）
				path = Constant.LOGIN_PATH;
			}
			// 画面遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}