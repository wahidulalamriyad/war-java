package gettersandsetters;

public class Employee {
    private int baseSalary;
    private int hourlyRate;

    public int calculateSalary(int extraHours) {
        return baseSalary + extraHours * hourlyRate;
    }

    public int getBaseSalary() { // Getter
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) { // Setter
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Base salary cannot be negative.");
        }
        this.baseSalary = baseSalary;
    }

    public int getHourlyRate() { // Getter
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) { // Setter
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative.");
        }
        this.hourlyRate = hourlyRate;
    }
}
