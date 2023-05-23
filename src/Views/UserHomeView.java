package Views;

import javax.swing.*;

import Controllers.*;

public class UserHomeView extends View {
    private JPanel mainPanel;
    private JButton bookATicketButton;
    private JButton cancelATicketButton;
    private JButton editProfileButton;
    private JButton backButton;
    public UserHomeView(){
        super();
        super.displayFrame(mainPanel);
    }
    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        bookATicketButton.addActionListener(e -> controller.changeFromHomeView(State.BOOK_TKT));
        cancelATicketButton.addActionListener(e -> controller.changeFromHomeView(State.DELETE_TKT));
        editProfileButton.addActionListener(e -> controller.changeFromHomeView(State.EDIT_INFO));
        backButton.addActionListener(e->controller.goBack());
    }
}
