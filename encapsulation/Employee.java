package encapsulation;

public class Employee {
    public int baseSalary;
    public int hourlyRate;

    public int calculateSalary(int extraHours) {
        return baseSalary + extraHours * hourlyRate;
    }
}
