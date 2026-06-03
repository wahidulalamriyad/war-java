package staticmembers;

public class Main {
    public static void main(String[] args) {
        var employee = new Employee(50_000, 20);
        int totalSalary = employee.calculateSalary(10);
        System.out.println("Total Salary: " + totalSalary);
        Employee.printNumberOfEmployees();

        var employee2 = new Employee(50_000);
        int totalSalary2 = employee2.calculateSalary(10);
        System.out.println("Total Salary: " + totalSalary2);
        Employee.printNumberOfEmployees();
    }
}
