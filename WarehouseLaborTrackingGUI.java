package AmznLaborTracking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WarehouseLaborTrackingGUI extends JFrame implements ActionListener {

    private Warehouse warehouse;
    private JTextField employeeIdField;
    private JTextField shiftIdField;
    private JTextField startTimeField;
    private JTextField endTimeField;
    private JTextArea laborDataArea;

    public WarehouseLaborTrackingGUI() {
        warehouse = new Warehouse();

        setTitle("Warehouse Labor Tracking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel laborDataPanel = createLaborDataPanel();

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(laborDataPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdField = new JTextField();

        JLabel shiftIdLabel = new JLabel("Shift ID:");
        shiftIdField = new JTextField();

        JLabel startTimeLabel = new JLabel("Start Time (YYYY-MM-DD HH:MM):");
        startTimeField = new JTextField();

        JLabel endTimeLabel = new JLabel("End Time (YYYY-MM-DD HH:MM):");
        endTimeField = new JTextField();

        panel.add(employeeIdLabel);
        panel.add(employeeIdField);
        panel.add(shiftIdLabel);
        panel.add(shiftIdField);
        panel.add(startTimeLabel);
        panel.add(startTimeField);
        panel.add(endTimeLabel);
        panel.add(endTimeField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton addEmployeeButton = new JButton("Add New Employee");
        addEmployeeButton.addActionListener(this);

        JButton assignShiftButton = new JButton("Assign Shift");
        assignShiftButton.addActionListener(this);

        JButton retrieveDataButton = new JButton("Retrieve Labor Data");
        retrieveDataButton.addActionListener(this);

        panel.add(addEmployeeButton);
        panel.add(assignShiftButton);
        panel.add(retrieveDataButton);

        return panel;
    }

    private JPanel createLaborDataPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel laborDataLabel = new JLabel("Labor Data:");
        laborDataArea = new JTextArea(10, 30);
        laborDataArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(laborDataArea);

        panel.add(laborDataLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = -1;

        if (e.getActionCommand().equals("Add New Employee")) {
            choice = 1;
        } else if (e.getActionCommand().equals("Assign Shift")) {
            choice = 2;
        } else if (e.getActionCommand().equals("Retrieve Labor Data")) {
            choice = 3;
        }

        switch (choice) {
            case 1:
                addNewEmployee();
                break;
            case 2:
                assignShift();
                break;
            case 3:
                retrieveLaborData();
                break;
        }
    }

    private void addNewEmployee() {
        String employeeId = employeeIdField.getText();
        String name = JOptionPane.showInputDialog(this, "Enter Employee Name:");
        String position = JOptionPane.showInputDialog(this, "Enter Employee Position:");

        Employee employee = new Employee(employeeId, name, position);
        warehouse.addEmployee(employee);
        JOptionPane.showMessageDialog(this, "New employee added successfully!");
        clearInputFields();
        }
    
    private void assignShift() {
        String employeeId = employeeIdField.getText();
        String shiftId = shiftIdField.getText();
        String startTimeText = startTimeField.getText();
        String endTimeText = endTimeField.getText();

        if (!isEmployeeExists(employeeId)) {
            JOptionPane.showMessageDialog(this, "Employee does not exist. Please add the employee first.");
            return;
        }

        LocalDateTime startTime = parseDateTime(startTimeText);
        LocalDateTime endTime = parseDateTime(endTimeText);

        if (startTime == null || endTime == null) {
            JOptionPane.showMessageDialog(this, "Invalid date/time format. Please use the format YYYY-MM-DD HH:MM.");
            return;
        }

        Shift shift = new Shift();
        shift.setShiftId(shiftId);
        shift.setStartTime(startTime);
        shift.setEndTime(endTime);

        Employee employee = getEmployeeById(employeeId);
        warehouse.assignShift(employee, shiftId, startTime, endTime);
        JOptionPane.showMessageDialog(this, "Shift assigned to employee successfully!");
        clearInputFields();
    }

    private void retrieveLaborData() {
        String employeeId = employeeIdField.getText();

        if (!isEmployeeExists(employeeId)) {
            JOptionPane.showMessageDialog(this, "Employee does not exist.");
            return;
        }

        int totalHoursWorked = warehouse.calculateTotalHoursWorked(employeeId);
        JOptionPane.showMessageDialog(this, "Total Hours Worked: " + totalHoursWorked);
        clearInputFields();
    }

    private boolean isEmployeeExists(String employeeId) {
        for (Employee employee : warehouse.getEmployeeList()) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return true;
            }
        }
        return false;
    }

    private Employee getEmployeeById(String employeeId) {
        for (Employee employee : warehouse.getEmployeeList()) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    private LocalDateTime parseDateTime(String dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateTime, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    private void clearInputFields() {
        employeeIdField.setText("");
        shiftIdField.setText("");
        startTimeField.setText("");
        endTimeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WarehouseLaborTrackingGUI());
    }
   }