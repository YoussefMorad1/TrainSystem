import Controllers.Controller;
import Controllers.State;
import Models.*;
import Views.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args)  {
//        UIManager.getLookAndFeelDefaults().put("Panel.background", Color.yellow);
        Model model = new Model(State.LOGIN);
        View view = new LoginView();
        Controller controller = new Controller(model, view);
    }
}