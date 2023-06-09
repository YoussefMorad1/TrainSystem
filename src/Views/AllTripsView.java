package Views;

import Controllers.Controller;
import Models.DataBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.Vector;

public class AllTripsView extends View{
    private JPanel panel1;
    private JButton backButton;
    private JTextField textField1;
    private JButton editButton;
    private JTable table1;
    private DefaultTableModel tableModel;
    AllTripsView(Controller controller){
        super();
        this.controller = controller;
        createUIComponents();
        super.displayFrame(panel1);
    }
    private void createUIComponents() {
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] columns = {"Trip ID", "Start Location", "Destination", "Start Time",
                "Arrive Time", "Train ID", "Still Available?", "Price"};
        for (String str : columns) {
            tableModel.addColumn(str);
        }
//        tableModel.addRow(columns);
        ResultSet userTrips = controller.getAllTrips();
        try {
            while (userTrips.next()) {
                Vector<Object> arr = new Vector<>();
                for (int i = 1; i <= 6; i++) {
                    if (i == 4) continue;
                    arr.add(userTrips.getString(i));
                }
                arr.add(userTrips.getInt(7) == 1 ? "Yes" : "No");
                arr.add(userTrips.getInt(8));
                arr.add(userTrips.getFloat(9));
                tableModel.addRow(arr);
            }
        } catch (Exception ignored) {
        }
        table1 = new JTable(tableModel);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        editButton.addActionListener(e -> {
                    if (!textField1.getText().chars().allMatch(Character::isDigit)) {
                        this.showMessage("Wrong Trip Id");
                        return;
                    }
                    controller.openUpdateTrip(Integer.parseInt(textField1.getText()));
                }
        );
        backButton.addActionListener(e->controller.goBack());
    }
}
