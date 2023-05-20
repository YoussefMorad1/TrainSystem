package Views;

import javax.swing.*;
import Controllers.Controller;
public class updateTripView extends View{
    private JPanel panel1;
    private JTextField tripId;
    private JTextField startLocation;
    private JTextField destinatoin;
    private JTextField avaliableSeats;
    private JTextField startTime;
    private JTextField arrivalTime;
    private JTextField trainId;
    private JButton updateButton;

    public updateTripView() {
        super();
        super.displayFrame(panel1);
    }

    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        updateButton.addActionListener(e -> controller.tryUpdateTrip(Integer.parseInt(tripId.getText()),startLocation.getText(), destinatoin.getText(), Integer.parseInt(avaliableSeats.getText()), startTime.getText(), arrivalTime.getText(), Integer.parseInt(trainId.getText())));
    }
}
