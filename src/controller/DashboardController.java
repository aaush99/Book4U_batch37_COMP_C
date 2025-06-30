/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import Dao.ProductDao;
import java.util.List;
import javax.swing.JPanel;
import Model.Product;
import View.AdminDash;
import View.ProductCard;
import java.util.ArrayList;
import javax.swing.BoxLayout;



/**
 *
 * @author User
 */
public class DashboardController {

    private final ProductDao productDao = new ProductDao();
    private final AdminDash dashboardView;
    private Product selectedProductForEdit = null;

    
     private List<ProductObserver> observers = new ArrayList<>();
    
    // Change from static to instance method
    public void addObserver(ProductObserver observer) {
        observers.add(observer);
    }
    
    // Change from static to instance method
    public void removeObserver(ProductObserver observer) {
        observers.remove(observer);
    }
    
    
    
    
    
    
    private void loadAllProducts() {
    List<Product> products = productDao.getAllProducts();
    JPanel panel = dashboardView.getProductPanel();
   panel.removeAll();

    for (Product product : products) {
        ProductCard card = new ProductCard();
        card.setProduct(product);

        // EDIT button logic
        card.getEditButton().addActionListener(e -> {
            selectedProductForEdit = card.getCurrentProduct();
            dashboardView.getProductName().setText(selectedProductForEdit.getProductName());
            dashboardView.getProductImage().setText(selectedProductForEdit.getProductImage());
            dashboardView.getProductPrice().setText(String.valueOf(selectedProductForEdit.getProductPrice()));
            dashboardView.getProductCategory().setText(selectedProductForEdit.getProductCategory());
            dashboardView.getAddButton().setText("Update Product");
        });

        panel.add(card);
    }

    panel.revalidate();
    panel.repaint();
}
    
    private void openFileChooser() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Select the image");

        int result = jFileChooser.showOpenDialog(dashboardView);
        if (result == JFileChooser.APPROVE_OPTION) {
            String productImage = jFileChooser.getSelectedFile().getAbsolutePath();
            dashboardView.getProductImage().setText(productImage);

        }
    } 
    public DashboardController(AdminDash dashboardView) {
        this.dashboardView = dashboardView;

        dashboardView.addProductListener(new AddProductListener());

        dashboardView.getProductImage().addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openFileChooser();

            } 
        });
        
         loadAllProducts();
    }
    
private void clearFields() {
        dashboardView.getProductName().setText("");
        dashboardView.getProductImage().setText("");
        dashboardView.getProductPrice().setText("");
        dashboardView.getProductCategory().setText("");
    }

    public void open() {
        this.dashboardView.setVisible(true);
    }

    public void close() {
        this.dashboardView.dispose();
    }

    List<Product> getAllProducts() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   class AddProductListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String name = dashboardView.getProductName().getText();
            String image = dashboardView.getProductImage().getText();
            int price = Integer.parseInt(dashboardView.getProductPrice().getText());
            String category = dashboardView.getProductCategory().getText();
            if (selectedProductForEdit == null) {
                
                Product product = new Product(name, image, price, category);
                productDao.createProduct(product);
            } else {
                
                selectedProductForEdit.setProductName(name);
                selectedProductForEdit.setProductImage(image);
                selectedProductForEdit.setProductPrice(price);
                selectedProductForEdit.setProductCategory(category);
             
                productDao.updateProduct(selectedProductForEdit);

                dashboardView.getAddButton().setText("Add Product");
                selectedProductForEdit = null;
            }

            loadAllProducts();
//            clearFields();
        } catch (Exception ex) {
            System.out.println("Error saving product: " + ex.getMessage());
        }
    }

    
}


   

    
    
} 