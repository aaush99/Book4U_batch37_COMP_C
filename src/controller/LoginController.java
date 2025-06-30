/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.DashboardController;
import Dao.UserDao;
import Model.LoginRequest;
import Model.UserData;
import View.AdminDash;
import View.Dash;
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author digitech
 */
public class LoginController {

    private final UserDao userDao = new UserDao();
    private final Login userView;

    public LoginController(Login userView) {
        this.userView = userView;
        userView.addLoginUserListener(new AddUserListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class AddUserListener implements ActionListener {

        private DashboardController controller;
        private DashboardController controlleradmin;
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String email = userView.getEmailtext().getText().trim();
                String password = new String(userView.getPasswordfield().getPassword()).trim();
                
                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(userView, "Please enter both email and password");
                    return;
                } 

                LoginRequest user = new LoginRequest(email, password);
                System.out.println("Login attempt for: " + email);
                               
                UserData loginUser = userDao.login(user);
                if (loginUser == null) {
                    JOptionPane.showMessageDialog(userView, "Invalid Credentials");
                } else {
                    // Check if user is admin
                    if (userDao.isAdmin(loginUser)) {
                        JOptionPane.showMessageDialog(userView, "Admin Login Successful");
                         AdminDash dashboardView = new  AdminDash();                 
                         DashboardController controller= new DashboardController(dashboardView);  
                         controller.open();  
                      
                  
                    } else {
                        JOptionPane.showMessageDialog(userView, "Login Successful");
                        Dash dashView = new Dash();
                        CustomerDashboardController adminController=new CustomerDashboardController(dashView, controlleradmin);
                        adminController.open();

//Dashboard dashboard = new Dashboard();
////DashboardController dashboardController = new DashboardController(dashboard);
//dashboardController.open(); 
                    }
                    close();
                }
            } catch (Exception ex) {
                System.out.println("Error during login: " + ex.getMessage());
                JOptionPane.showMessageDialog(userView, "An error occurred during login");
                ex.printStackTrace();
            }
        }
    }
}
