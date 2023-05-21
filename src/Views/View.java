package Views;

import javax.swing.*;
import java.awt.*;
import Models.Model;
import Controllers.*;

public abstract class View extends JFrame {
    Controller controller;
    public View(){
    }
    public void addEventListener(Controller controller){
        this.controller = controller;
    }
    public void displayFrame(JPanel panel){
        this.setTitle("Train System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
//        this.setSize(800, 500);
        this.setMinimumSize(new Dimension(400, 400));
//        this.setPreferredSize(new Dimension(800,500));
        this.getContentPane().setBackground(Color.white);
        this.displayPanel(panel);
        this.setVisible(true);
        this.pack();
    }
//    public void updateModel(Model model){
//        this.model = model;
//    }
    public View getNewView(){
        this.dispose();
        return ViewFactory.createView(controller);
    }
    private void displayPanel(JPanel panel){
        panel.setVisible(true);
        this.add(panel);
    }
    public void showError(String str){
        JOptionPane.showMessageDialog(this, str);
    }
}
