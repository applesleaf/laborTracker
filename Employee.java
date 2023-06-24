package AmznLaborTracking;

public class Employee {
	 private String employeeId;
	    private String name;
	    private String position;

	    public Employee(String employeeId, String name, String position) {
	        this.employeeId = employeeId;
	        this.name = name;
	        this.position = position;
	    }

	    // Setter and getter methods for attributes
	    public void setEmployeeId(String employeeId) {
	        this.employeeId = employeeId;
	    }

	    public String getEmployeeId() {
	        return employeeId;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setPosition(String position) {
	        this.position = position;
	    }

	    public String getPosition() {
	        return position;
	    }
    


}
