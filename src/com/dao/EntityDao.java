package com.dao;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import com.model.*;
import com.dao.*;
import com.general.*;
public enum EntityDao {
	instance;
		Database db=new Database();
	   Connection connection = null;
       PreparedStatement ptmt = null;
       ResultSet resultSet = null;
       String query="";
       private Connection getConnection() throws SQLException {
          Connection conn;
          conn = ConnectionFactory.getInstance().getConnection();
          return conn;
       }

    public String getEntityDescription(int type) {
    	String result = "";
    	try {
    		query = "select description from entitydesc where type = " + type;
    		connection = getConnection();
    		ptmt = connection.prepareStatement(query);
    		resultSet = ptmt.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getString("description");
            }
    	  } catch (Exception e) {
    		  System.out.println(e.getMessage());
				e.printStackTrace();
			}
        return result;
    }
    public String getEntity(int id) {
    	String result = "";
    	try {
    		query = "select value from entity where id = " + id;
    		connection = getConnection();
    		ptmt = connection.prepareStatement(query);
    		resultSet = ptmt.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getString("value");
            }
    	  } catch (Exception e) {
    		  System.out.println(e.getMessage());
				e.printStackTrace();
			}
        return result;
    }
    public String addEntity(String parent, int type, String title, String data) {
    	String result = "";
	    try {
    		query = "insert into entity (type, value) values (" + type + ",'" + data + "')";
    		connection = getConnection();
    		ptmt = connection.prepareStatement(query);
    		ptmt.executeUpdate();
    		
    		query = "insert into tree (parent, text, type, entityId) select '" + parent + "','" + title + "'," + 
    		type + ", max(id) FROM entity";
    		
    		ptmt = connection.prepareStatement(query);
    		ptmt.executeUpdate();
    		
    		if(type == 8){ // Algorithm type
        		query = "insert into tree (parent, text, type, entityId) select max(id),'Sample algorithms'," + 
       	    		"108, 0 FROM tree";
   	    		ptmt = connection.prepareStatement(query);
   	    		ptmt.executeUpdate();
   	    		
        		query = "insert into tree (parent, text, type, entityId) select max(id)-1,'Benchmarks'," + 
           	    		"104, 0 FROM tree";
       	    		ptmt = connection.prepareStatement(query);
       	    		ptmt.executeUpdate();

        		query = "insert into tree (parent, text, type, entityId) select max(id)-2,'How to'," + 
           	    		"106, 0 FROM tree";
       	    		ptmt = connection.prepareStatement(query);
       	    		ptmt.executeUpdate();

        		query = "insert into tree (parent, text, type, entityId) select max(id)-3,'Datasets'," + 
           	    		"105, 0 FROM tree";
       	    		ptmt = connection.prepareStatement(query);
       	    		ptmt.executeUpdate();
    		}
    		return "ok";
    		/*resultSet = ptmt.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getString("description");
            }*/
    	  } catch (Exception e) {
    		  System.out.println(e.getMessage());
				e.printStackTrace();
			}
	    return result;
    }
}
