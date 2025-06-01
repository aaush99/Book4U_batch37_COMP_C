/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

/**
 *
 * @author Nitro
 */
import java.sql.*;


public interface Database{
    Connection openConnection();
    void closeConnection(Connection conn);
    ResultSet runQuery(Connection conn, String query);//reference type
    int executeUpdate(Connection conn, String query);

    public Object open();
}
