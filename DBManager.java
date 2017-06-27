package keijiban;

import java.sql.Connection;
import java.sql.DriverManager;

/** DB接続用クラス*/

public class DBManager {

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/DB_KEIJIBAN", "KEIUSER", "SSS");
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
