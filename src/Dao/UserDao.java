 
package Dao;

import Database.Mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.UserData;
import Model.LoginRequest;

/**
 *
 * @author User
 */
public class UserDao {
     Mysql mysql = new Mysql() {
         @Override
         public Object open() {
             throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         }
     };
         
     
    
    public void UserDao (UserData user) {
        Connection conn = mysql.openConnection();
        
        String sql = "INSERT INTO user( username, email, password, phonenumber) VALUES ( ?, ?, ?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setInt(4, user.getPhonenumber());
   
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            mysql.closeConnection(conn);
        }
    }
    
    public boolean isEmailExists(String email) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet result = pstmt.executeQuery();
            return result.next();  // returns true if email is found
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
     
    public boolean checkUser(UserData user){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM user where email = ? or username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getUsername());
            
            ResultSet result = pstmt.executeQuery(); 
            return result.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn); 
        }
        return false;
    }
    
      public UserData login(LoginRequest login){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM user where email = ? and password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login.getEmail());
            pstmt.setString(2, login.getPassword());
            
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                UserData user  = new UserData(                  
                    result.getString("username"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getInt("phonenumber")
                    );
                user.setId(result.getInt("id"));
                return user;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }



}
