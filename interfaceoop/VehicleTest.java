package interfaceoop;

// =========================================================
// Single Java file demonstrating all 5 OOP pillars:
//   1. Encapsulation -> private fields + public getters/setters
//   2. Inheritance   -> Car & Motorcycle extend Vehicle
//   3. Polymorphism  -> Vehicle reference -> Car/Motorcycle objects
//   4. Abstraction   -> abstract class Vehicle with abstract methods
//   5. Interface     -> Drivable interface implemented by Vehicle
// =========================================================


// ---------- INTERFACE ----------
interface Drivable {

    // Abstract methods (implicitly public abstract)
    void start();
    void stop();

    // Default method (Java 8+) — has a body, inherited as-is
    default void describe() {
        System.out.println("This is a drivable vehicle.");
    }
}


// ---------- ABSTRACTION ----------
// Abstract class: cannot be instantiated. Implements the Drivable interface.
abstract class Vehicle implements Drivable {

    // ---------- ENCAPSULATION ----------
    private String brand;   // hidden from outside; accessed via getter/setter

    // Constructor of the abstract class
    public Vehicle(String brand) {
        System.out.println("Vehicle constructor called");
        this.brand = brand;
    }

    // Abstract methods — must be implemented by concrete subclasses
    public abstract double calculateFuelEfficiency();

    @Override
    public abstract String toString();

    // Concrete getter / setter (encapsulation)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}


// ---------- INHERITANCE ----------
// Car "is-a" Vehicle.
class Car extends Vehicle {

    // Encapsulated fields
    private double distanceTravelled;   // km
    private double fuelConsumed;        // liters

    public Car(String brand, double distanceTravelled, double fuelConsumed) {
        super(brand);                   // call parent constructor
        System.out.println("Car constructor called");
        this.distanceTravelled = distanceTravelled;
        this.fuelConsumed = fuelConsumed;
    }

    // Getters and setters
    public double getDistanceTravelled()         { return distanceTravelled; }
    public void   setDistanceTravelled(double d) { this.distanceTravelled = d; }
    public double getFuelConsumed()              { return fuelConsumed; }
    public void   setFuelConsumed(double f)      { this.fuelConsumed = f; }

    // ---------- POLYMORPHISM (method overriding) ----------
    @Override
    public double calculateFuelEfficiency() {
        return distanceTravelled / fuelConsumed;
    }

    @Override
    public String toString() {
        return "Car brand is " + getBrand()
                + " and fuel efficiency is: " + calculateFuelEfficiency() + " km/l";
    }

    // Implementing the Drivable interface methods
    @Override
    public void start() {
        System.out.println("Car " + getBrand() + " engine started.");
    }

    @Override
    public void stop() {
        System.out.println("Car " + getBrand() + " engine stopped.");
    }
}


// Motorcycle "is-a" Vehicle as well.
class Motorcycle extends Vehicle {

    // Encapsulated fields
    private int    engineCapacity;   // cc
    private double mileage;          // km per liter

    public Motorcycle(String brand, int engineCapacity, double mileage) {
        super(brand);
        System.out.println("Motorcycle constructor called");
        this.engineCapacity = engineCapacity;
        this.mileage = mileage;
    }

    // Getters and setters
    public int    getEngineCapacity()         { return engineCapacity; }
    public void   setEngineCapacity(int e)    { this.engineCapacity = e; }
    public double getMileage()                { return mileage; }
    public void   setMileage(double m)        { this.mileage = m; }

    @Override
    public double calculateFuelEfficiency() {
        return mileage;   // mileage is already km/l
    }

    @Override
    public String toString() {
        return "Motorcycle brand is " + getBrand()
                + " and fuel efficiency is: " + calculateFuelEfficiency() + " km/l";
    }

    @Override
    public void start() {
        System.out.println("Motorcycle " + getBrand() + " engine started.");
    }

    @Override
    public void stop() {
        System.out.println("Motorcycle " + getBrand() + " engine stopped.");
    }
}


// ---------- DRIVER CLASS ----------
public class VehicleTest {
    public static void main(String[] args) {

        // 1 & 2 -- POLYMORPHISM:
        // A Vehicle reference can hold any subclass object.
        // The actual overridden method is resolved at runtime.
        Vehicle v1 = new Car("Toyota", 500, 40);
        Vehicle v2 = new Motorcycle("Yamaha", 150, 45.5);

        System.out.println();

        // 3 -- Print using the overridden toString()
        System.out.println(v1.toString());
        System.out.println(v2.toString());

        System.out.println();

        // 4 -- Interface methods called polymorphically
        v1.start();
        v1.stop();
        v2.start();
        v2.stop();

        System.out.println();

        // 5 -- Default method from the Drivable interface
        v1.describe();
        v2.describe();

        System.out.println();

        // 6 -- ENCAPSULATION in action: modify private state via setter
        v1.setBrand("Honda");
        System.out.println("Updated -> " + v1.toString());
    }
}
