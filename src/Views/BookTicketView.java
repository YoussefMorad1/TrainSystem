package Views;

import Models.DataBase;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class BookTicketView extends View {
    private JPanel panel1;
    private JTextField fromField;
    private JTextField toField;
//    private String[] data = {"one", "two", "three", "four"};
    private JList list;
//    private JList list;
    private JXDatePicker startDate;
    private JTable table1;
    DefaultTableModel tableModel;
    private JButton searchButton;
    private JTextField textField3;
    private JButton bookButton;

    BookTicketView() {
        super();
//        createUIComponents();
        displayTrips();
        this.displayFrame(panel1);
    }

    //    public void addEventListener(){
//    }
    public void displayTrips() {
        DataBase db = new DataBase();
        int size = list.getSelectedIndices().length;
        int[] selectedIndices = list.getSelectedIndices();
        for (int i = 0; i < size; i++) {
            selectedIndices[i]++;
        }
        ResultSet trips = db.searchTrips(fromField.getText(), toField.getText(), selectedIndices, startDate.getDate());
        showIntoTable(trips);
    }

    private void createUIComponents() {
        DataBase db = new DataBase();
        ResultSet trips = db.searchTrips(fromField.getText(), toField.getText(), null, startDate.getDate());
        showIntoTable(trips);
    }
    private void showIntoTable(ResultSet trips) {
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] columns = {"id", "startLocation", "destination", "availableSeats", "startTime", "arriveTime", "trainId"};
        for (String str : columns) {
            tableModel.addColumn(str);
        }
        tableModel.addRow(columns);
//        try {
//            while (trips.next()) {
//                Vector<Object> arr = new Vector<>();
//                for (int i = 1; i <= 8; i++) {
//                    if (i == 7) continue; // if available column continue
//                    arr.add(trips.getString(i));
//                }
//                tableModel.addRow(arr);
//            }
//        } catch (Exception ignored) {
//        }
        table1 = new JTable(tableModel);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

}
