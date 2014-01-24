package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	static String connectionString="jdbc:mysql://liquidbenchmark.net:3306/liquid?user=liquid&password=liquid123";
	//static String connectionString="jdbc:mysql://localhost:3306/liquid?user=liquid&password=liquid123";
	private static Connection connect = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;

	public boolean Connect() throws Exception
	{
		try {
				Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager.getConnection(connectionString);
				return true;
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
				
	}
	public ResultSet executeQuery(String sqlQuery) throws Exception
	{
		try {
		Connect();
			statement = connect.createStatement();
		
		// Result set get the result of the SQL query
		resultSet = statement.executeQuery(sqlQuery);
		connect.close();
		return resultSet;
		
	} catch (SQLException e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	}
	public boolean executeNonQuery(String sqlQuery) throws Exception
	{
		try {
			Connect();
			statement = connect.createStatement();
		
		// Result set get the result of the SQL query
		statement.execute(sqlQuery);
		connect.close();
		return true;
	} catch (SQLException e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	}
}
