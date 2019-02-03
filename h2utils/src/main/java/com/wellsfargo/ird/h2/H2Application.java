package com.wellsfargo.ird.h2;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wellsfargo.ird.h2.config.H2Config;

public class H2Application {

	static String ENTITY_MAP_TABLE_NAME = "T_BOT_ENTITY_VALMAP";
    public static void main( String[] args )
    {
    	H2Config config = new H2Config();
    	config.createConnection();
    	config.createTable("T_BOT_ENTITY_VALMAP", entityValueMapSchema);
    	config.insert(insertValueMap);
    	ResultSet rs = config.select("select * from " + ENTITY_MAP_TABLE_NAME);
    	
    	try {
			while(rs.next()) {
				try {
					System.out.println(rs.getString("key")+ "  " + rs.getString("createdBy") + 
							"   " + rs.getTimestamp("createdDate") + "  " + 
							rs.getTimestamp("updatedDate")
							);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    static String entityValueMapSchema = "create table " + ENTITY_MAP_TABLE_NAME + 
    		"(entityValueMapId INTEGER auto_increment," + 
    		"dialogId INTEGER ," +
    		"key VARCHAR(255)," +
    		"createdBy VARCHAR(255)," +
    		"createdDate timestamp as current_timestamp," +
    		"updatedBy VARCHAR(255)," +
    		"updatedDate timestamp  as current_timestamp)";
// 
    static String insertValueMap = "insert into " + ENTITY_MAP_TABLE_NAME +
    		//" (dialogId, key, createdBy, updatedBy )" +
    		" values (default, 123, 'fdsjfkjea|kds|kjfkj', 'me', default, 'me', default)";
//	"values (default, ?, ?, ?, default, ?, default)";

}
