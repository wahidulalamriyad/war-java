package abstraction;

public class Main {
    public static void main(String[] args) {
        var employee = new Employee();
//        employee.setBaseSalary(-1);
        employee.setBaseSalary(50_000);
        employee.setHourlyRate(20);

        int totalSalary = employee.calculateSalary(10);
        System.out.println("Total Salary: " + totalSalary);
    }
}
