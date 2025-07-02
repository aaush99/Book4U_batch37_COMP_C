
package controller;

import View.ReportOnIssue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ReportController implements ActionListener {
    private JLabel AttachmentLabel;
    private ReportOnIssue userView;
    
    public ReportController(ReportOnIssue userView){
        this.userView = userView;
        this.AttachmentLabel = userView.getAttachmentLabel();
        
        userView.getAttachmentLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleImageClick();
            } 
        });
    }
    
    public void open(){
        this.userView.setVisible(true);
    }
    
   public void handleImageClick() {
        System.out.println("Image clicked!");
        
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select an Image");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();

            // Update image label in GUI
            ImageIcon icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
            userView.getAttachmentLabel().setIcon(icon);
            
//            Boolean success = chatClientDAO.updateUserImagePath(userEmail, path);
//            String results = success ? "saved ":"didn't save";
//            
//                System.out.println("image path " + results + " "+ userEmail);
                
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
