/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.UserData;
import View.SignUp;
import View.Login;
import Controller.LoginController;
/**
 *
 * @author User
 */
public class SignUpController {
    private final UserDao userDao = new UserDao();
    private final SignUp userView;

    public SignUpController(SignUp userView) {
        this.userView = userView;

        userView.addAddUserListener(new AddUserListener());
        userView.addLoginListener(new AddLoginListener());
          
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
                String username = userView.getUsernameField().getText();
                String email = userView.getEmailField().getText();
                String password = userView.getPasswordField().getText();
                int phonenumber=Integer.parseInt(userView.getPhonenumber().getText());
                signUp(username, email, password, phonenumber);
                
                 
//                UserData user = new UserData(username, email, password, phonenumber);
//                boolean check = userDao.checkUser(user);

//                if (check) {
//                    JOptionPane.showMessageDialog(userView, "Duplicate user");
//                } else {
//                    userDao.UserDao(user);

                //}
            } catch (Exception ex) {
                System.out.println("Error adding user: " + ex.getMessage());
            }

        }
        
        public boolean signUp(String username, String email, String password, int phonenumber) {
            if (username == null || username.trim().isEmpty() || username.equals("Set Username")) {
                JOptionPane.showMessageDialog(null, "Username cannot be empty.");
                return false;
            }
            
            if (email == null || email.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Email cannot be empty.");
                return false;
            }
            
            if (password == null || password.trim().isEmpty() || password.equals("Set Password")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid password.");
                return false;
            }

           if (phonenumber == 0 ) 
           {
                JOptionPane.showMessageDialog(null, "Phonenumber cannot be empty.");
                return false;
            
            }

            if (userDao.isEmailExists(email)) {
                JOptionPane.showMessageDialog(null, "Email already in use!");
                return false;
            }
 
            UserData user = new UserData(username, email, password, phonenumber);
            boolean success = userDao.checkUser(user);

            if (success) {
                JOptionPane.showMessageDialog(userView, "User signed up failed!");
             
            } else {
                userDao.UserDao(user);
                    JOptionPane.showMessageDialog(null, "signup Successful");
                    userView.dispose();
                    
                    Login signIn = new Login();
                    signIn.setVisible(true);
                    Login userloginform= new Login();
                    LoginController controller = new LoginController(userloginform);
                    controller.open();
                    close();
        }
            return success;
        }
    }

    
      
    
    class AddLoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Login loginView = new Login();
            LoginController login = new LoginController(loginView);
            
            login.open();
            close();
        }
    }
    

}
