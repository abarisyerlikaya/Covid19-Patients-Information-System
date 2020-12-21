package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	static String url = "jdbc:postgresql://localhost:5432/covid19-patients";
	static Connection conn;

	static void connect() {
		try {
			conn = DriverManager.getConnection(url, "postgres", "0852");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	static void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static ResultSet select(String query) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static boolean update(String query) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(false);
			return false;
		}
	}

}
