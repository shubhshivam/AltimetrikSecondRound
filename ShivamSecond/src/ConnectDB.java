import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectDB {

	
	public static Connection connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/productdetails", "root", "Shivam@123!");
		return con;
		}
		catch(Exception ex) {
			
		}
		finally {
			System.out.println("Connected Successfully:");
		}
		return null;
	}
	public static void main(String[] args) {
		
	}
}
	