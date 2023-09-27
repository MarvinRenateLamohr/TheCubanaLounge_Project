package za.co.marvinlamohr.thecubanalounge;

public class Employee {
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeGender;
    private Long employeeContactNumber;
    private String employeePosition;
    private String employeeRole;
    private Double employeeSalary;
    private String employeeUsername;

    public static String employeeActive;


    public Employee(Long employeeId, String employeeFirstName, String employeeLastName, String employeeGender, Long employeeContactNumber, String employeePosition, String employeeRole,Double employeeSalary, String employeeUsername) {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeGender = employeeGender;
        this.employeeContactNumber = employeeContactNumber;
        this.employeePosition = employeePosition;
        this.employeeRole = employeeRole;
        this.employeeSalary = employeeSalary;
        this.employeeUsername = employeeUsername;
    }


    public Long getEmployeeId() {

        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {

        this.employeeId = employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;}

    public void setEmployeeFirstName(String employeeFirstName) {

        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {

        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {

        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeGender() {

        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {

        this.employeeGender = employeeGender;
    }

    public Long getEmployeeContactNumber() {

        return employeeContactNumber;
    }

    public void setEmployeeContactNumber(Long employeeContactNumber) {
        this.employeeContactNumber = employeeContactNumber;
    }

    public String getEmployeePosition() {

        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {

        this.employeePosition = employeePosition;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }
}
