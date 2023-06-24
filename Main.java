package AmznLaborTracking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
    private static Warehouse warehouse = new Warehouse();

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            int choice = getUserChoice();

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
                case 4:
                    isRunning = false;
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("=== Warehouse Labor Tracking Application ===");
        System.out.println("1. Add New Employee");
        System.out.println("2. Assign Shift");
        System.out.println("3. Retrieve Labor Data");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return choice;
    }

    private static void addNewEmployee() {
        System.out.println("=== Add New Employee ===");
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.nextLine();

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee Position: ");
        String position = scanner.nextLine();

        Employee employee = new Employee(employeeId, name, position);
        warehouse.addEmployee(employee);
        System.out.println("New employee added successfully!");
    }

    private static void assignShift() {
        System.out.println("=== Assign Shift ===");
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.nextLine();

        // Check if the employee exists
        if (!isEmployeeExists(employeeId)) {
            System.out.println("Employee does not exist. Please add the employee first.");
            return;
        }

        System.out.print("Enter Shift ID: ");
        String shiftId = scanner.nextLine();

        System.out.print("Enter Start Time (YYYY-MM-DD HH:MM): ");
        LocalDateTime startTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.print("Enter End Time (YYYY-MM-DD HH:MM): ");
        LocalDateTime endTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        Shift shift = new Shift();
        shift.setShiftId(shiftId);
        shift.setStartTime(startTime);
        shift.setEndTime(endTime);

        Employee employee = getEmployeeById(employeeId);
        warehouse.assignShift(employee, shiftId, startTime, endTime);
        System.out.println("Shift assigned to employee successfully!");
    }

    private static void retrieveLaborData() {
        System.out.println("=== Retrieve Labor Data ===");
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.nextLine();

        // Check if the employee exists
        if (!isEmployeeExists(employeeId)) {
            System.out.println("Employee does not exist.");
            return;
        }

        int totalHoursWorked = warehouse.calculateTotalHoursWorked(employeeId);
        System.out.println("Total Hours Worked: " + totalHoursWorked);
    }

    private static boolean isEmployeeExists(String employeeId) {
        // Check if the employee exists in the warehouse
        // You can implement this logic based on your data structure (e.g., employeeList)
        // Return true if the employee exists, false otherwise
        // Implement your own logic here
    	for (Employee employee : warehouse.getEmployeeList()) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return true;
            }
        }
        return false;
    }
    private static Employee getEmployeeById(String employeeId) {
        // Retrieve the employee object from the warehouse based on the provided employee ID
        // You can implement this logic based on your data structure (e.g., employeeList)
        // Return the employee object if found, null otherwise
        // Implement your own logic here
    	for (Employee employee : warehouse.getEmployeeList()) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    public static void Main(String[] args) {
        // Rest of the code...

        // Example usage:
        warehouse.addEmployee(new Employee("E001", "John Doe", "Picker"));
        warehouse.addEmployee(new Employee("E002", "Jane Smith", "Packer"));

        // Example usage of the menu-driven interface:
        /*
        === Warehouse Labor Tracking Application ===
        1. Add New Employee
        2. Assign Shift
        3. Retrieve Labor Data
        4. Exit
        Enter your choice: 1

        === Add New Employee ===
        Enter Employee ID: E003
        Enter Employee Name: Mark Johnson
        Enter Employee Position: Supervisor
        New employee added successfully!

        === Warehouse Labor Tracking Application ===
        1. Add New Employee
        2. Assign Shift
        3. Retrieve Labor Data
        4. Exit
        Enter your choice: 2

        === Assign Shift ===
        Enter Employee ID: E001
        Enter Shift ID: S001
        Enter Start Time (YYYY-MM-DD HH:MM): 2023-06-23 08:00
        Enter End Time (YYYY-MM-DD HH:MM): 2023-06-23 16:30
        Shift assigned to employee successfully!

        === Warehouse Labor Tracking Application ===
        1. Add New Employee
        2. Assign Shift
        3. Retrieve Labor Data
        4. Exit
        Enter your choice: 3

        === Retrieve Labor Data ===
        Enter Employee ID: E001
        Total Hours Worked: 8
        */
    }
}
    
