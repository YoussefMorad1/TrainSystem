package Views;

import Controllers.Controller;
import Models.DataBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

public class CancelTicketView extends View {
    private JPanel panel1;
    Controller controller;
    private JTable table1;
    CancelTicketView(Controller controller) {
        super();
        this.controller = controller;
        createUIComponents();
        super.displayFrame(panel1);
    }
    private void createUIComponents() {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] columns = {"Trip ID", "Start Location", "Destination", "Start Time",
                "Arrive Time", "Train ID", "Still Available?"};
        for (String str : columns) {
            tableModel.addColumn(str);
        }
        tableModel.addRow(columns);
        ResultSet userTrips = new DataBase().getUserTrips(controller.getModel().getUserName());
        try {
            while (userTrips.next()) {
                Vector<String> arr = new Vector<>();
                for (int i = 1; i <= 8; i++) {
                    if (i == 4) continue;
                    arr.add(userTrips.getString(i));
                }
                arr.add(userTrips.getInt(7) == 1 ? "Yes" : "No");
                tableModel.addRow(arr);
            }
        } catch (Exception ignored) {
        }
        table1 = new JTable(tableModel);
    }
}
