/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bookforyou;
import Controller.DashboardController;
import View.AdminDash;
import View.SignUp;
import controller.SignUpController;
/**
 *
 * @author Nitro
 */
public class BookForYou {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
//        SignUp signup = new SignUp();
//        SignUpController contr = new SignUpController(signup);
//        contr.open();
        AdminDash DashboardForm = new  AdminDash();                 
        DashboardController controller= new DashboardController(DashboardForm);  
        controller.open();  
        
    }
    
}
