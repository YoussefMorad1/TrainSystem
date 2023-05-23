package Views;

import javax.swing.*;
import Controllers.Controller;
import com.github.lgooddatepicker.components.DateTimePicker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateTripView extends View{
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
    private int tripId;

    public UpdateTripView(int tripID, Controller controller) {
        super();
        this.tripId = tripID;
        this.controller = controller;
        showTripInfo();
        super.displayFrame(panel1);
    }
    public void showTripInfo(){
        String pattern = "yyyy-MM-dd HH:mm:ss.S";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String[] info = controller.getTripInfo(tripId);
        tripIdField.setText(info[0]);
        startLocation.setText(info[1]);
        destinatoin.setText(info[2]);
        startTime.setDateTimePermissive(LocalDateTime.parse(info[4], formatter));
        arrivalTime.setDateTimePermissive(LocalDateTime.parse(info[5], formatter));
        trainId.setText(info[7]);
        priceField.setText(info[8]);
        tripIdField.setEditable(false);
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
