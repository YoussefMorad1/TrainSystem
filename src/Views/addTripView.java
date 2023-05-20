package Views;

import javax.swing.*;
import Controllers.Controller;

public class addTripView extends View{
    private JPanel panel1;
    private JTextField startLocation;
    private JTextField destinatoin;
    private JTextField avaliableSeats;
    private JTextField startTime;
    private JTextField arrivalTime;
    private JTextField trainId;
    private JButton addButton;

    public addTripView() {
        super();
        super.displayFrame(panel1);
    }

    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        addButton.addActionListener(e -> controller.tryAddTrip(startLocation.getText(), destinatoin.getText(), Integer.parseInt(avaliableSeats.getText()), startTime.getText(), arrivalTime.getText(), Integer.parseInt(trainId.getText())));
    }
}
