/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author User
 */
public class LoginRequest {
    
    private String email;
    private String password;
    public LoginRequest(String email,  String password){
        this.email = email;
        this.password = password;
    }
    /**
     * @return the email
     */
    

    /**
     * @return the password
     */
 
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
       public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
