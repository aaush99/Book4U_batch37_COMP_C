package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Product;
import Database.Mysql;

    public class ProductDao {

Mysql mysql=new Mysql() {
    @Override
    public Object open() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
};    
    
    
    public void createProduct(Product product) {
        Connection conn = mysql.openConnection();
        
        String sql = "INSERT INTO product (productName , productImage, productPrice, productCategory) VALUES ( ?, ?, ?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getProductImage());
            pstmt.setInt(3, product.getProductPrice());
            pstmt.setString(4, product.getProductCategory());
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
   
   public List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();
    Connection conn = mysql.openConnection();
    String sql = "SELECT * FROM product";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)){
    ResultSet rs = pstmt.executeQuery();
    while (rs.next()) {
    Product product = new Product(
    rs.getInt("product_id"),
    rs.getString("productName"),
    rs.getString("productImage"),
    rs.getInt("productPrice"),
    rs.getString("productCategory")
    );
    products.add(product);
    }
    } catch (SQLException ex) {ex.printStackTrace();
    } finally {
    mysql.closeConnection(conn);
    }
    return products;
} 
 
    public void updateProduct(Product product) {
    Connection conn = mysql.openConnection();
    String sql = "UPDATE product SET productName=?, productImage=?, productPrice=?,productCategory=? WHERE product_id=?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, product.getProductName());
        pstmt.setString(2, product.getProductImage());
        pstmt.setInt(3, product.getProductPrice());
        pstmt.setString(4, product.getProductCategory());
        pstmt.setInt(5, product.getProductId());
        
        pstmt.executeUpdate();

        pstmt.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        mysql.closeConnection(conn);
    }
}   
}
    

