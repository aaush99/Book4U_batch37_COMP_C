// CustomerDashboardController.java
package Controller;

import Model.Product;
import View.Dash;
import View.CustomerProductCard;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import Controller.CustomerDashboardController;
import Controller.DashboardController;
import Dao.ProductDao;
import java.util.ArrayList;
public class CustomerDashboardController implements ProductObserver {
    private final Dash dashboardView ;
    private final DashboardController adminController;
    
    
    
    
    public CustomerDashboardController(Dash dashboardView, DashboardController adminController) {
    this.dashboardView = dashboardView;
    this.adminController = adminController;
    setupUI();
    
    if (adminController != null) {
        this.adminController.addObserver(this);
        refreshProductDisplay(adminController.getAllProducts());
    } else {
        ProductDao productDao=new ProductDao();
        refreshProductDisplay(productDao.getAllProducts());
        
    }
    
}

   
    
        
    
    private void setupUI() {
        // Initialize your UI compone nts here
        dashboardView.getProductPanel().setLayout(new BoxLayout(dashboardView.getProductPanel(), BoxLayout.Y_AXIS));
    }

    @Override
    public void updateProducts(List<Product> products) {
        // This will be called when products are updated in the admin dashboard
        refreshProductDisplay(products);
    }
    
    public void refreshProductDisplay(List<Product> products) {
        JPanel panel = dashboardView.getProductPanel();
        panel.removeAll();
        
        for (Product product : products) {
            CustomerProductCard card = new CustomerProductCard();
            card.setProduct(product);
            
            // Add action listeners for buttons
            
            card.getAddToCartButton().addActionListener(e -> {
                // Handle add to cart logic
            });
            
            card.getBuyButton().addActionListener(e -> {
                // Handle buy logic
            });
            
            panel.add(card);
        }
        
        panel.revalidate();
        panel.repaint();
    }

    public void open() {
        this.dashboardView.setVisible(true);
    }
    
}