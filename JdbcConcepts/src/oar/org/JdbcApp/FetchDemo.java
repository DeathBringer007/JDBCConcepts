package oar.org.JdbcApp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FetchDemo {

	public static void main(String[] args) 
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//Scanner input for absolute method
		Scanner scr= new Scanner(System.in);
		int rNum=scr.nextInt();
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			
			//con=DriverManager.getConnection(url,info);			

			pstmt=con.prepareStatement("select * from oejm5.std");
			
			//pstmt=con.prepareStatement("select name,perc from oejm5.std");
			//stmt.execute("insert into oejm5.std values(2,'San','CSE',55.06)");
			
			rs=pstmt.executeQuery();
			
			/*if(rs.next()) 
			{
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String branch=rs.getString(3);
				double perc=rs.getDouble(4);
				
				System.out.println("Student Detail:\n"
						+id +" "
						+name+" "
						+branch+ " "
						+perc+" ");*/
						
				/*String name=rs.getString(1);
				double perc=rs.getDouble(2);
				
				System.out.println("Student Detail:\n"
						+name+" "
						+perc+" ");*/
			if(rs.absolute(rNum)) 
			{
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String branch=rs.getString(3);
				double perc=rs.getDouble(4);
				
				System.out.println("Student Detail:\nId: "+id+"\nName: "+name+"\nBranch: "+branch+"\nPercentage: "+perc);
			}
			else
			System.err.println("Result not found");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		
		{
			scr.close();
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
			if(pstmt!=null)
			{
				try 
				{
					pstmt.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(rs!=null)
			{
				try 
				{
					rs.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
	}

}
