package org.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
public static Connection getConnection() throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
    String url="jdbc:mysql://localhost:3306/paper";
    String user="root";
    String password="root123";
	Connection conn
	=DriverManager.getConnection(url, user, password);
    return conn;
}
public static void close(Connection  conn) throws SQLException
{
	if(conn!=null)
		conn.close();
}
public static void close(Statement stmt) throws SQLException
{
	if(stmt!=null)
		stmt.close();	
}
public static void close(ResultSet rs) throws SQLException
{
	if(rs!=null)
		rs.close();
}
public static void closeQuietly(Connection conn)
{
	try {
		close(conn);
	} catch (SQLException e) {
	}
}
public static void closeQuietly(Statement stmt)
{
	try {
		close(stmt);
	} catch (SQLException e) {
	}
}	
public static void closeQuietly(ResultSet rs)
{
	try {
		close(rs);
	} catch (SQLException e) {
	}
}
public static void closeQuietly(Connection conn,Statement stmt)
{   try{
	    closeQuietly(stmt);
       }finally{
    	closeQuietly(conn);
       }
}
public static void closeQuietly(Connection conn,Statement stmt,ResultSet rs)
{    
	try{
		closeQuietly(rs);
	}
	finally{
    	closeQuietly(conn,stmt);
       }
}
}

