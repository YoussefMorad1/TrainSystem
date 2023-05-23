package Views;

import Controllers.Controller;

import javax.swing.*;

public class AdminView extends View{
    private JPanel panel1;
    private JButton trainAddButoon;
    private JButton trainUpdatebutton;
    private JButton addATripButton;
    private JButton viewAllTrips;
    private JButton backButton;
    private JButton reportButton;

    public AdminView() {
        super();
        super.displayFrame(panel1);
    }

    @Override
    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        trainAddButoon.addActionListener(e -> controller.openAddTrain());
        trainUpdatebutton.addActionListener(e -> controller.viewAllTrains());
        addATripButton.addActionListener(e -> controller.openAddTrip());
        viewAllTrips.addActionListener(e -> controller.viewAllTrips());
        backButton.addActionListener(e->{
            if(showConfirmDialog("Are you sure you want to logout?"))
                controller.goBack();
        });
        reportButton.addActionListener(e->controller.openReport());
    }
}
