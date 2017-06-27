package keijiban;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/** 投稿画面初期処理(投稿データ読込) */
public class DisplaySetup {

	// 投稿データの読み込み
	public ArrayList<JavaBeans> LoadList(String value) {

		// 他クラスのインスタンス化
		JavaBeans bean = null;

		// ArrayListの作成
		ArrayList<JavaBeans> list = new ArrayList<JavaBeans>();

		// クエリの作成
		String query = "SELECT tb.TOKO_NO, tb.USER_ID, tb.TOKO_TITLE, tb.TOKO_MESSAGE, tb.TOKO_DATE, "
				+ "tb.EDIT_DATE, mt.USER_NAME FROM TB_TOKO tb INNER JOIN MT_USER mt ON tb.USER_ID = mt.USER_ID ";
		
		// 絞込み・ソートを実行しない場合
		if (value == null) {
			query = query + "ORDER BY tb.EDIT_DATE DESC";
			
		// 絞込み,ソート処理を実行する場合
		} else {
			query = query + value;
		}

		// DB処理
		try (Connection con = DBManager.getConnection(); Statement stm = con.createStatement()) {

			// 投稿データ取得
			ResultSet rs = stm.executeQuery(query);

			// 投稿データをArrayListに格納
			while (rs.next()) {
				bean = new JavaBeans();

				bean.setNumber(rs.getInt("tb.TOKO_NO"));
				bean.setId(rs.getString("tb.USER_ID"));
				bean.setName(rs.getString("mt.USER_NAME"));
				bean.setTitle(rs.getString("tb.TOKO_TITLE"));
				bean.setMessage(rs.getString("tb.TOKO_MESSAGE"));
				bean.setTokoDate(rs.getTimestamp("tb.TOKO_DATE"));
				bean.setEditDate(rs.getTimestamp("tb.EDIT_DATE"));
				
				list.add(bean);
			}
		// 例外処理
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ArrayListを返す
		return list;
	}
}
