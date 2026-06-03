package procedural;

public class Main {
    public static void main(String[] args) {
        int baseSalary = 50_000;
        int extraHours = 10;
        int hourlyRate = 20;

        int totalSalary = calculateSalary(baseSalary, extraHours, hourlyRate);
        System.out.println("Total Salary: " + totalSalary);
    }

    public static int calculateSalary(int baseSalary, int extraHours, int hourlyRate) {
        return baseSalary + extraHours * hourlyRate;
    }
}
