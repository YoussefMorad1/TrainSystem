package Views;

import javax.swing.*;
import Controllers.Controller;
import com.github.lgooddatepicker.components.DateTimePicker;

public class addTripView extends View{
    private JPanel panel1;
    private JTextField startLocation;
    private JTextField destinatoin;
    private JTextField priceField;
    private JTextField trainId;
    private JButton addButton;
    private DateTimePicker startTime;
    private DateTimePicker arrivalTime;
    private JButton backButton;

    public addTripView() {
        super();
        super.displayFrame(panel1);
    }

    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        addButton.addActionListener(e -> controller.tryAddTrip(
                    startLocation.getText(), destinatoin.getText(),
                    startTime.getDateTimePermissive(), arrivalTime.getDateTimePermissive(),
                    Integer.parseInt(trainId.getText()), Float.parseFloat(priceField.getText())
                )
        );
        backButton.addActionListener(e -> controller.goBack());
        /*
        * available seats should be equal to number of seats in the given train ID: DONE
        * you must validate that the given train ID already exists
        **/
    }
}
