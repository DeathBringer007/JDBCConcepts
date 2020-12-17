package oar.org.JdbcApp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcSteps {

	public static void main(String[] args) 
	{
		Connection con=null;
		//String url="jdbc:mysql://localhost:3306";
		//Properties info= new Properties();
		//info.put("user", "root");
		//info.put("password", "root");
		Statement stmt=null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			//con=DriverManager.getConnection(url,info);
			stmt=con.createStatement();
			//stmt.execute("insert into oejm5.std values(2,'San','CSE',55.06)");
			stmt.executeUpdate("update oejm5.std set perc=70.00 where id=1");
			System.out.println("done");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(con!=null)
			{
				try 
				{
					con.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(stmt!=null)
			{
				try 
				{
					stmt.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}
 