package Views;

import Controllers.Controller;
import Models.DataBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.Vector;

public class allTrainsView extends View{
    private JPanel panel1;
    private JButton backButton;
    private JTextField textField1;
    private JButton editButton;
    private JTable table1;
    private DefaultTableModel tableModel;
    allTrainsView(Controller controller){
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
        String[] columns = {"Train ID", "Seats", "Class"};
        for (String str : columns) {
            tableModel.addColumn(str);
        }
//        tableModel.addRow(columns);
        ResultSet userTrains = controller.getAllTrains();
        try {
            while (userTrains.next()) {
                Vector<Object> arr = new Vector<>();
                for (int i = 1; i <= 3; i++) {

                    arr.add(userTrains.getString(i));
                }
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
                    controller.openUpdateTrain(Integer.parseInt(textField1.getText()));
                }
        );
        backButton.addActionListener(e->controller.goBack());
    }
}
