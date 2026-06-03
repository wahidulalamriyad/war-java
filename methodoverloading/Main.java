package methodoverloading;

public class Main {
    public static void main(String[] args) {
        var employee = new Employee(50_000, 20);
        int totalSalary = employee.calculateSalary(10);
        System.out.println("Total Salary: " + totalSalary);
    }
}
