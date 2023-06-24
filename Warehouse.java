package AmznLaborTracking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warehouse {
	
	private List<Employee> employeeList;
    private Map<String, List<Shift>> laborData;

    public Warehouse() {
        employeeList = new ArrayList<>();
        laborData = new HashMap<>();
    }

    // Method to add an employee
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    // Method to assign a shift to an employee
    public void assignShift(Employee employee, String shiftId, LocalDateTime startTime, LocalDateTime endTime) {
        if (employee != null) {
            Shift shift = new Shift();
            shift.setShiftId(shiftId);
            shift.setStartTime(startTime);
            shift.setEndTime(endTime);

            List<Shift> shifts = laborData.getOrDefault(employee.getEmployeeId(), new ArrayList<>());
            shifts.add(shift);
            laborData.put(employee.getEmployeeId(), shifts);
        } else {
            System.out.println("Invalid employee. Please provide a valid employee object.");
        }
    }

    // Method to calculate the total hours worked by an employee
    public int calculateTotalHoursWorked(String employeeId) {
        int totalHours = 0;
        List<Shift> shifts = laborData.getOrDefault(employeeId, new ArrayList<>());
        for (Shift shift : shifts) {
            totalHours += shift.calculateHoursWorked();
        }
        return totalHours;
    }
    
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

}
