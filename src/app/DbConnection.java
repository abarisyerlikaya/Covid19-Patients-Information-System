package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.postgresql.util.PSQLException;

public class DbConnection {
	static String url = "jdbc:postgresql://localhost:5432/covid19-patients";
	static Connection conn;

	static void connect() {
		try {
			conn = DriverManager.getConnection(url, "postgres", "0852");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "İşlem başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
		}

	}

	static void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "İşlem başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
		}
	}

	static ResultSet select(String query) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			return rs;
		} catch (SQLException e) {
			return null;
		}
	}

	static boolean update(String query) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			SQLWarning sw = stmt.getWarnings();
			if (sw != null)
				JOptionPane.showMessageDialog(null, sw.getMessage(), "Bilgi", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} catch (SQLException e) {
			if (e.getMessage().contains("HATA: Bir hasta aynı gün içerisinde bir kez test olabilir."))
				JOptionPane.showMessageDialog(null, e.getMessage().split("Where:")[0], "Hata",
						JOptionPane.ERROR_MESSAGE);
			else if (e.getMessage().contains("HATA: Hastanın durumu"))
				JOptionPane.showMessageDialog(null, e.getMessage().split("Where:")[0].split("HATA:")[1], "Bilgi",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "İşlem başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

}
