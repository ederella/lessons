package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseStorage implements Storage{
	private final String jdbcURL = "jdbc:h2:~/test";
	private final String username = "sa";
	private final String password = "1234";
	

	public void save(String data) {

		try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)){
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO STORAGE(DATA) VALUES ('" + data + "')");
			
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		
	}

	public String retrieve(int id) {
		try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)){
			PreparedStatement  statement = connection.prepareStatement("SELECT * FROM storage WHERE ID = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next())
				return rs.getNString("data");
			
		} catch (SQLException e) {		
			e.printStackTrace();
		}return "";
	}

}
