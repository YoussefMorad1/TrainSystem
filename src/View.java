import javax.swing.*;
import java.awt.*;

public abstract class View extends JFrame {
    public View(){
        this.setTitle("Train System");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 500);
        this.getContentPane().setBackground(Color.white);
    }
    public abstract void addEventListener(Controller controller);
    public void displayPanel(JPanel panel){
        this.add(panel);
    }
}
