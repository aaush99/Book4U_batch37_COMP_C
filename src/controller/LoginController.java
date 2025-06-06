/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.UserDao;
import Model.LoginRequest;
import Model.UserData;
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
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                String email = userView.getEmailtext().getText();
                String password = userView.getPasswordfield().getText();
                LoginRequest user = new LoginRequest(email, password);
                               System.out.println(email);
                               
                UserData loginUser = userDao.login(user);
                if (loginUser == null) {
                    JOptionPane.showMessageDialog(userView, "Invalid Credentials");
                } else {
                    // success
                    JOptionPane.showMessageDialog(userView, "Login Successful");
                    Dash dashboard = new Dash();
                    dashboard.setVisible(true);
                    close();
                }
            } catch (Exception ex) {
                System.out.println("Error adding user: " + ex.getMessage());
            }
            
            
        }
    } 
}
