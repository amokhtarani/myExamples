/**
 * 
 */
package com.wellsfargo.ird.h2.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.annotation.Configuration;

/**
 * @author akbar
 *
 */
@Configuration
public class H2Config {

   public static final String H2_JDBC_DRIVER = "org.h2.Driver";
   public static final String H2_DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
   private static final String H2_DB_USER = "";
   private static final String H2_DB_PASSWORD = "";
   public static  String DROP_TABLE = "DROP TABLE IF EXISTS ";

   private static Connection conn = null;
   private static Statement stmt = null;
   public void createConnection() {
      try {
           Class.forName(H2_JDBC_DRIVER);

           System.out.println("Connecting to database...");
           conn = DriverManager.getConnection(H2_DB_URL, H2_DB_USER, H2_DB_PASSWORD);

           System.out.println("Creating table in given database...");
           stmt = conn.createStatement();

//           stmt.close();
 //          conn.close();
       } catch(SQLException se) {
          
           se.printStackTrace();
       } catch(Exception e) {
           
           e.printStackTrace();
       } 
       System.out.println("Goodbye!");

   }

   public void createTable(String tableName, String schema) {
     try {
		stmt.execute(DROP_TABLE + tableName);
	    stmt.executeUpdate(schema);
	     System.out.println("Dropped table in given database...");
	     System.out.println("Created table in given database...");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
   
   public void insert(String sql) {
	   try {
		stmt.executeUpdate(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   public ResultSet select(String sql) {
	   ResultSet rs = null;
	   try {
		rs = stmt.executeQuery(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return rs;
   }
}
