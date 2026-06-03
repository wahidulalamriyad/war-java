package polymorphism;

// Shape.java
class Shape {
    public double area() {
        return 0;
    }
}

// Circle.java
class Circle extends Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

// Triangle.java
class Triangle extends Shape {
    double base, height;

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return 0.5 * base * height;
    }
}

// Rectangle.java
class Rectangle extends Shape {
    double length, width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }
}

// Polymorphism.java
public class Polymorphism {
    public static void main(String[] args) {

        // Polymorphic object array of Shape
        Shape[] shapes = new Shape[3];

        // Child class objects stored via Shape reference (Polymorphic behavior)
        shapes[0] = new Circle(5.0);
        shapes[1] = new Triangle(6.0, 4.0);
        shapes[2] = new Rectangle(8.0, 3.0);

        // Shape reference calls child class area() at runtime (Dynamic Dispatch)
        for (Shape shape : shapes) {
            System.out.println(shape.getClass().getSimpleName() + " Area = " + shape.area());
        }
    }
}
