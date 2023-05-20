package Views;

import javax.swing.*;

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

}
