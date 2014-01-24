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
public enum TreeDao {
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

    public ArrayList<Tree> getTreeByParent(String parentId) {
    	ArrayList<Tree> result = new ArrayList<Tree>();
        Tree tree = null;
    	try {
                    query = "select * from tree where parent = '" + parentId + "'";
                    connection = getConnection();
                    ptmt = connection.prepareStatement(query);
                    
					resultSet = ptmt.executeQuery();
					while (resultSet.next()) {
                            tree = new Tree();
                            tree.setId(resultSet.getInt("id"));
                            tree.setParent(resultSet.getString("parent"));
                            tree.setText(resultSet.getString("text"));
                            tree.setType(resultSet.getInt("type"));
                            tree.setEntityId(resultSet.getInt("entityId"));
                            result.add(tree);
                    }
    	  } catch (Exception e) {
				// TODO Auto-generated catch block
    		  System.out.println(e.getMessage());
				e.printStackTrace();
			}
                    return result;
    }
    public ArrayList<Tree> getAllTree() {
    	ArrayList<Tree> result = new ArrayList<Tree>();
        Tree tree = null;
    	try {
                    query = "select * from tree";
                    connection = getConnection();
                    ptmt = connection.prepareStatement(query);
                    
					resultSet = ptmt.executeQuery();
					while (resultSet.next()) {
                            tree = new Tree();
                            tree.setId(resultSet.getInt("id"));
                            tree.setParent(resultSet.getString("parent"));
                            tree.setText(resultSet.getString("text"));
                            tree.setType(resultSet.getInt("type"));
                            tree.setEntityId(resultSet.getInt("entityId"));
                            result.add(tree);
                    }
    	  } catch (Exception e) {
				// TODO Auto-generated catch block
    		  System.out.println(e.getMessage());
				e.printStackTrace();
			}
                    return result;
    }
}
