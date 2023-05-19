import javax.swing.*;
import java.awt.*;

public abstract class View extends JFrame {
    Model model;
    public View(Model model){
        this.model = model;
    }
    public abstract void addEventListener(Controller controller);
    public void displayFrame(JPanel panel){
        this.setTitle("Train System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 500);
        this.getContentPane().setBackground(Color.white);
        this.displayPanel(panel);
        this.setVisible(true);
    }
    public void updateModel(Model model){
        this.model = model;
    }
    public View getNewView(){
        this.dispose();
        return ViewFactory.createView(model);
    }
    private void displayPanel(JPanel panel){
        panel.setVisible(true);
        this.add(panel);
    }
    public void showError(String str){
        JOptionPane.showMessageDialog(this, str);
    }
    public Boolean isAdmin(){
        return model.isAdmin();
    }
    public String getUserName() {
        return model.getUserName();
    }
    public String getPassword() {
        return model.getPassword();
    }
}
