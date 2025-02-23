package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseStorage implements Storage{
	private final String jdbcURL;
	private final String username;
	private final String password;
	

	public DataBaseStorage() {
		ConfigManager configManager = new ConfigManager("src\\main\\resources\\config.properties");
		jdbcURL = configManager.getProperty("DB_URL");
		username = configManager.getProperty("USERNAME");
		password = configManager.getProperty("PASSWORD");
	}
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
