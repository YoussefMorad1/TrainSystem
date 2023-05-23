import Controllers.Controller;
import Controllers.State;
import Models.*;
import Views.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args)  {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Model model = new Model(State.LOGIN);
        View view = new LoginView();
        Controller controller = new Controller(model, view);
    }
}