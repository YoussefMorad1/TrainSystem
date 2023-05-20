package Views;

import Controllers.Controller;
import Models.DataBase;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Vector;

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
        createUIComponents();
        this.displayFrame(panel1);
    }

    public void addEventListener(Controller controller){
        super.addEventListener(controller);
        searchButton.addActionListener(e -> displayTrips());
//        bookButton.addActionListener(e -> );
    }
    private void displayTrips() {
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
        ResultSet trips = db.searchTrips(null, null, null, null);
        showIntoTable(trips);
    }
    private void showIntoTable(ResultSet trips) {
        if (tableModel == null) {
            tableModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }
        else {
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
        }
        String[] columns = {"id", "startLocation", "destination", "availableSeats", "startTime", "arriveTime", "trainId", "class"};
        for (String str : columns) {
            tableModel.addColumn(str);
        }
        tableModel.addRow(columns);
        try {
            while (trips.next()) {
                Vector<String> arr = new Vector<>();
                for (int i = 1; i <= 8; i++) {
                    if (i == 7) continue; // if 'available' column continue
                    arr.add(trips.getString(i));
                }
                arr.add(trips.getInt(9) == 1 ? "First" : trips.getInt(9) == 2 ? "Second" : "Third");
                tableModel.addRow(arr);
            }
        } catch (Exception ignored) {
        }
        table1 = new JTable(tableModel);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

}
