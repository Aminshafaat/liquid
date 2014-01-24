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
public enum UserDao {
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
   public User login(String email, String password)  {
           query = "select * from user where email = '" + email + "' and password = '" + General.instance.SHA1(password) + "'";
           
           User user = null;
           try {
               connection = getConnection();
               ptmt = connection.prepareStatement(query);
				resultSet = ptmt.executeQuery();
           while (resultSet.next()) {
           	  user = new User();
                 user.setId(resultSet.getInt("id"));
                 user.setName(resultSet.getString("name"));
                 user.setEmail(resultSet.getString("email"));
                 user.setType(resultSet.getInt("type"));
           }
           } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           return user;
   }
        
  /* public void add(User user) {
            try {
                    query = "insert into user (rec_id,title,location,salary,category,description,closing_date) values (?,?,?,?,?,?,?)";
                    connection = getConnection();
                    ptmt = connection.prepareStatement(query);
                    ptmt.setInt(1, user.getRec_id());
                    ptmt.setString(2, user.getTitle());
                    ptmt.setString(6, user.getdescription());
                    ptmt.setDate(7,  new java.sql.Date(user.getClosing_date().getTime()));
                    ptmt.executeUpdate();
                    System.out.println("A user is Added Successfully");
            } catch (SQLException e) {
                    e.printStackTrace();
            } finally {
                    try {
                            if (ptmt != null)
                                    ptmt.close();
                            if (connection != null)
                                    connection.close();
                    } catch (SQLException e) {
                            e.printStackTrace();
                    } catch (Exception e) {
                            e.printStackTrace();
                    }

            }
    }
    public void update(User user) throws Exception {

        try {
            query = "update user set rec_id=?,title=?,location=?,salary=?,category=?,description=?,closing_date=? where user_id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(query);
            ptmt.setInt(1, user.getRec_id());
            ptmt.setString(2, user.getTitle());
            ptmt.setString(3, user.getLocation());
            ptmt.setString(4, user.getSalary());
            ptmt.setString(5, user.getCategory());
            ptmt.setString(6, user.getdescription());
            ptmt.setDate(7,  new java.sql.Date(user.getClosing_date().getTime()));
            ptmt.setInt(8,  user.getUser_id());
            ptmt.executeUpdate();
            System.out.println("A user is updated Successfully");
    } catch (SQLException e) {
            e.printStackTrace();
    } finally {
            try {
                    if (ptmt != null)
                            ptmt.close();
                    if (connection != null)
                            connection.close();
            } catch (SQLException e) {
                    e.printStackTrace();
            } catch (Exception e) {
                    e.printStackTrace();
            }

    }
    }
    
     public boolean delete(int user_id)  {
    	 try {
    	 query = "DELETE FROM user WHERE user_id="+user_id;
    
			return db.executeNonQuery(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return false;
     }*/
  /*  public ArrayList<User> getAllUsers() {
    	ArrayList<User> result = new ArrayList<User>();
        User user = null;
    	try {
                    query = "select * from user";
                    connection = getConnection();
                    ptmt = connection.prepareStatement(query);
                    
						resultSet = ptmt.executeQuery();
					while (resultSet.next()) {
                            user = new User();
                            user.setUser_id(resultSet.getInt("user_id"));
                            user.setFirstName(resultSet.getString("firstname"));
                            user.setLastName(resultSet.getString("lastname"));
                            user.setEmail(resultSet.getString("email"));
                            user.setPhone(resultSet.getString("phone"));
                            user.setAddress(resultSet.getString("address"));
                            user.setUserType(resultSet.getInt("userType"));
                            result.add(user);
                    }
    	  } catch (Exception e) {
				// TODO Auto-generated catch block
    		  System.out.println(e.getMessage());
				e.printStackTrace();
			}
                    return result;
    }
    public ArrayList<User> getUsersByType(int userType) {
    	ArrayList<User> result = new ArrayList<User>();
        User user = null;
    	try {
                    query = "select * from user where userType="+userType;
                    connection = getConnection();
                    ptmt = connection.prepareStatement(query);
                    
						resultSet = ptmt.executeQuery();
					while (resultSet.next()) {
                            user = new User();
                            user.setUser_id(resultSet.getInt("user_id"));
                            user.setFirstName(resultSet.getString("firstname"));
                            user.setLastName(resultSet.getString("lastname"));
                            user.setEmail(resultSet.getString("email"));
                            user.setPhone(resultSet.getString("phone"));
                            user.setAddress(resultSet.getString("address"));
                            user.setUserType(resultSet.getInt("userType"));
                            result.add(user);
                    }
    	  } catch (Exception e) {
				// TODO Auto-generated catch block
    		  System.out.println(e.getMessage());
				e.printStackTrace();
			}
                    return result;
    }
    public User findById(int id)  {
            query = "select * from user where user_id="+id;
            User user = null;
            try {
                connection = getConnection();
                ptmt = connection.prepareStatement(query);
				resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
            	  user = new User();
                  user.setUser_id(resultSet.getInt("user_id"));
                  user.setFirstName(resultSet.getString("firstname"));
                  user.setLastName(resultSet.getString("lastname"));
                  user.setEmail(resultSet.getString("email"));
                  user.setPhone(resultSet.getString("phone"));
                  user.setAddress(resultSet.getString("address"));
                  user.setUserType(resultSet.getInt("userType"));
            }
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return user;
    }*/
}
