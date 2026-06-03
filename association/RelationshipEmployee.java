package association;

// ===== Position =====
class Position {
    private String title;
    private double salary;

    public Position(String title, double salary) {
        this.title = title;
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    public double getSalary() {
        return salary;
    }
}

// ===== Employee =====
class Employee {
    private String name;
    private Position position;

    public Employee(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
}

// ===== Company =====
class Company {
    private String companyName;
    private Employee[] employees;
    private int count = 0;

    public Company(String companyName, int capacity) {
        this.companyName = companyName;
        this.employees = new Employee[capacity];
    }

    public void addEmployee(Employee e) {
        if (count < employees.length) {
            employees[count] = e;
            count++;
        } else {
            System.out.println("Cannot add more employees. Capacity full.");
        }
    }

    public void showDetails() {
        System.out.println("Company: " + companyName);
        System.out.println("Employees:");

        for (int i = 0; i < count; i++) {
            System.out.println("- " + employees[i].getName()
                    + " | Position: " + employees[i].getPosition().getTitle()
                    + " | Salary: " + employees[i].getPosition().getSalary());
        }
    }
}

// ===== Main Class =====
public class RelationshipEmployee {
    public static void main(String[] args) {

        Company company = new Company("Al-Fursan", 3);

        company.addEmployee(new Employee(
                "Abdullah", new Position("Cashier", 30000)));

        company.addEmployee(new Employee(
                "Jubayer", new Position("Manager", 25000)));

        company.addEmployee(new Employee(
                "Fahim", new Position("Attendant", 5000)));

        company.showDetails();
    }
}
