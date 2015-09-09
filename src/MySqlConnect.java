import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySqlConnect {
	
	private final String className = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://mysql4.000webhost.com";
	private final String user = "a1306861_Android";
	private final String password = "coderman2015";
	
	private Connection connection;
	private void connect() {
		try {
			Class.forName(className);
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connect successfully!");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error connection!");
		}
	}
	
	public static void main(String[] args) {
		MySqlConnect mySqlConnect = new MySqlConnect();
		mySqlConnect.connect();
	}
}
