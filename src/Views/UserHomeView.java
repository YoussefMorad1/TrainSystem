package Views;

import javax.swing.*;
import Models.Model;
import Controllers.*;

public class UserHomeView extends View {
    private JPanel mainPanel;
    private JButton bookATicketButton;
    private JButton cancelATicketButton;
    private JButton editProfileButton;
    public UserHomeView(){
        super();
        super.displayFrame(mainPanel);
    }

    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        bookATicketButton.addActionListener(controller);
        cancelATicketButton.addActionListener(controller);
        editProfileButton.addActionListener(controller);
    }
}
