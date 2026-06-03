package staticmembers;

public class Employee {
    private int baseSalary; // Instance Member
    private int hourlyRate; // Instance Member
    public static int numberOfEmployees; // Static Member

    public Employee(int baseSalary) { // Constructor
//        setBaseSalary(baseSalary);
//        setHourlyRate(0);
        this(baseSalary, 0); // Second Constructor Overloading
    }

    public Employee(int baseSalary, int hourlyRate) { // Constructor
        setBaseSalary(baseSalary);
        setHourlyRate(hourlyRate);
        numberOfEmployees++;
    }

    public static void printNumberOfEmployees() { // Static Method
        System.out.println("Number of employees: " + numberOfEmployees);
    }

    public int calculateSalary() { // Method Overloading
        return baseSalary;
    }

    public int calculateSalary(int extraHours) { // Method Overloading
        return baseSalary + extraHours * hourlyRate;
    }

    private int getBaseSalary() { // Getter
        return baseSalary;
    }

    private void setBaseSalary(int baseSalary) { // Setter is set to private
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Base salary cannot be negative.");
        }
        this.baseSalary = baseSalary;
    }

    private int getHourlyRate() { // Getter
        return hourlyRate;
    }

    private void setHourlyRate(int hourlyRate) { // Setter is set to private
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative.");
        }
        this.hourlyRate = hourlyRate;
    }
}
