package Views;

import javax.swing.*;
import Controllers.Controller;
import com.github.lgooddatepicker.components.DateTimePicker;

public class updateTripView extends View{
    private JPanel panel1;
    private JTextField tripIdField;
    private JTextField startLocation;
    private JTextField destinatoin;
    private JTextField trainId;
    private JButton updateButton;
    private DateTimePicker startTime;
    private DateTimePicker arrivalTime;
    private JTextField priceField;
    private JButton backButton;
    private int tripID;

    public updateTripView(int tripID) {
        super();
        this.tripID = tripID;
        super.displayFrame(panel1);
    }
    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        updateButton.addActionListener(e -> controller.tryUpdateTrip(
                    Integer.parseInt(tripIdField.getText()),startLocation.getText(),
                    destinatoin.getText(), startTime.getDateTimePermissive(), arrivalTime.getDateTimePermissive(),
                    Integer.parseInt(trainId.getText()), Float.parseFloat(priceField.getText())
                )
        );
        backButton.addActionListener(e->controller.goBack());
    }
}
