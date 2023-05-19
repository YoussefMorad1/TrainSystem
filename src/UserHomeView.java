import javax.swing.*;
import java.awt.*;

public class UserHomeView extends View {
    private JPanel mainPanel;
    private JButton bookATicketButton;
    private JButton cancelATicketButton;
    private JButton editProfileButton;
    public UserHomeView(){
        super();
        displayPanel(mainPanel);
    }

    public void addEventListener(Controller controller) {
        bookATicketButton.addActionListener(controller);
        cancelATicketButton.addActionListener(controller);
        editProfileButton.addActionListener(controller);
    }
}
