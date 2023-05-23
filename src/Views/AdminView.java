package Views;

import Controllers.Controller;

import javax.swing.*;

public class AdminView extends View{
    private JPanel mainPanel;

    private JButton addTrainButton;
    private JButton addTripButton;
    private JButton viewAllTrips;
    private JButton backButton;
    private JButton viewStatiscsButton1;
    private JButton viewAllTrains;


    public AdminView() {
        super();
        super.displayFrame(mainPanel);
    }

    @Override
    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        addTrainButton.addActionListener(e -> controller.openAddTrain());
        viewAllTrains.addActionListener(e -> controller.viewAllTrains());
        addTripButton.addActionListener(e -> controller.openAddTrip());
        viewAllTrips.addActionListener(e -> controller.viewAllTrips());
        backButton.addActionListener(e->{
            if(showConfirmDialog("Are you sure you want to logout?"))
                controller.goBack();
        });
        viewStatiscsButton1.addActionListener(e->controller.openReport());
    }
}
