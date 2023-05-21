package Views;

import javax.swing.*;
import Controllers.Controller;
import com.github.lgooddatepicker.components.DateTimePicker;

public class updateTripView extends View{
    private JPanel panel1;
    private JTextField tripId;
    private JTextField startLocation;
    private JTextField destinatoin;
    private JTextField trainId;
    private JButton updateButton;
    private DateTimePicker startTime;
    private DateTimePicker arrivalTime;
    private JTextField price;
    private JButton backButton;

    public updateTripView() {
        super();
        super.displayFrame(panel1);
    }

    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        updateButton.addActionListener(e -> controller.tryUpdateTrip(Integer.parseInt(tripId.getText()),startLocation.getText(), destinatoin.getText(), startTime.getDateTimePermissive(), arrivalTime.getDateTimePermissive(), Integer.parseInt(trainId.getText())));
        backButton.addActionListener(e->controller.goBack());
    }
}
