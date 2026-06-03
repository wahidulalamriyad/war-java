package interfaceoop;
// =========================================================
// Single Java file demonstrating all OOP concepts:
// 1. Encapsulation  -> private fields + public getters/setters
// 2. Inheritance    -> Circle & Rectangle extend Shape
// 3. Polymorphism   -> overriding + Shape reference to subclass objects
// 4. Abstraction    -> abstract class Shape with abstract methods
// 5. Interface      -> Drawable interface implemented by Shape
// =========================================================


// ---------- INTERFACE ----------
// An interface defines a contract. Any class that implements it
// must provide implementations for its abstract methods.
interface Drawable {

    // abstract method (implicitly public and abstract)
    void draw();

    // default method — has a body, can be inherited as-is
    default void displayInfo() {
        System.out.println("This object is drawable.");
    }
}


// ---------- ABSTRACTION ----------
// Abstract class: cannot be instantiated. Used as a base class.
// Notice it also implements the Drawable interface.
abstract class Shape implements Drawable {

    // ---------- ENCAPSULATION ----------
    // Field is private — outside classes cannot access it directly.
    // Access is controlled via getter/setter below.
    private String color;

    // Abstract class can still have a constructor
    public Shape(String color) {
        System.out.println("Shape constructor called");
        this.color = color;
    }

    // Abstract methods — must be overridden by concrete subclasses
    abstract double area();

    @Override
    public abstract String toString();

    // Concrete method: getter (encapsulation)
    public String getColor() {
        return color;
    }

    // Concrete method: setter (encapsulation)
    public void setColor(String color) {
        this.color = color;
    }
}


// ---------- INHERITANCE ----------
// Circle "is-a" Shape, so it extends Shape.
class Circle extends Shape {

    private double radius;   // encapsulated field

    public Circle(String color, double radius) {
        super(color);        // call parent constructor
        System.out.println("Circle constructor called");
        this.radius = radius;
    }

    // Encapsulation: getter / setter
    public double getRadius()              { return radius; }
    public void   setRadius(double radius) { this.radius = radius; }

    // ---------- POLYMORPHISM (method overriding) ----------
    @Override
    double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        return "Circle color is " + getColor()
                + " and area is : " + area();
    }

    // Implementing the Drawable interface method
    @Override
    public void draw() {
        System.out.println("Drawing a Circle with radius " + radius);
    }
}


// Another subclass — also demonstrates inheritance & polymorphism
class Rectangle extends Shape {

    private double length;
    private double width;

    public Rectangle(String color, double length, double width) {
        super(color);
        System.out.println("Rectangle constructor called");
        this.length = length;
        this.width  = width;
    }

    // Encapsulation: getters / setters
    public double getLength()              { return length; }
    public void   setLength(double length) { this.length = length; }
    public double getWidth()               { return width; }
    public void   setWidth(double width)   { this.width = width; }

    @Override
    double area() {
        return length * width;
    }

    @Override
    public String toString() {
        return "Rectangle color is " + getColor()
                + " and area is : " + area();
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle "
                + length + " x " + width);
    }
}


// ---------- DRIVER CLASS ----------
public class InterfaceDemo {
    public static void main(String[] args) {

        // POLYMORPHISM: a Shape reference points to a Circle / Rectangle object.
        // The correct overridden method is chosen at runtime (dynamic dispatch).
        Shape s1 = new Circle("Red", 2.2);
        Shape s2 = new Rectangle("Yellow", 2, 4);

        System.out.println(s1.toString());
        System.out.println(s2.toString());

        // Interface methods called polymorphically
        s1.draw();
        s2.draw();

        // Default method inherited from the Drawable interface
        s1.displayInfo();
        s2.displayInfo();

        // Encapsulation in action — change color via setter, read via getter
        s1.setColor("Blue");
        System.out.println("Updated " + s1.toString());
    }
}
