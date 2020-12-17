package oar.org.JdbcApp;

import java.sql.*;
import java.util.*;

 class JdbcDemo {

	public static void main(String[] args) 
	{
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		Savepoint sp=null;
		Scanner scr=new Scanner(System.in);
		System.out.println("Enter id:");
		int id=scr.nextInt();
		System.out.println("Enter Name:");
		String name=scr.next();
		System.out.println("Enter dept:");
		String dept=scr.next();
		System.out.println("Enter Perc:");
		double perc=scr.nextDouble();
		System.out.println("Enter Place:");
		String place=scr.next(); 
		scr.close();
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			con.setAutoCommit(false);
			pstmt1=con.prepareStatement("insert into oejm5.std1 values(?,?,?,?)");
			pstmt1.setInt(1, id);
			pstmt1.setString(2, name);
			pstmt1.setString(3, dept);
			pstmt1.setDouble(4, perc);
			pstmt1.executeUpdate();
			
			sp=con.setSavepoint();
			pstmt2=con.prepareStatement("insert into oejm5.std2 values(?,?,?)");
			pstmt2.setInt(1, id);
			pstmt2.setString(2, name);
			pstmt2.setString(3, place);
			pstmt2.executeUpdate();
			System.out.println("done");
			con.commit();
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			try {
				con.rollback(sp);
				con.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
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
			if(pstmt1!=null)
			{
				try 
				{
					pstmt1.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(pstmt2!=null)
			{
				try 
				{
					pstmt2.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}	
	}

}
