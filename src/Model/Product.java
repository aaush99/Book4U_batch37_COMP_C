/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nitro
 */
public class Product
{
    private String productName;
    private String productImage;
    private int productPrice;
    private String productCategory;
    private int productId;

    public Product(String productName, String productImage, int productPrice, String productCategory) 
    {
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productCategory=productCategory;
    }

    
    public Product(int id, String name, String image, int price, String category) {
    this.productId = id;
    this.productName = name;
    this.productImage = image;
    this.productPrice = price;
    this.productCategory= category;
}
    
    
    
    
    
    
    
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    } 
    
    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

   public int getProductId() {
    return productId;
}
    
public void setProductId(int productId) {
    this.productId = productId;
}


}
