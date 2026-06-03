package polymorphism;

// Polymorphic Behavior of Objects occurs when a parent class reference variable refers to a child class object.
// It allows methods to be called based on the actual object type at runtime, enabling dynamic method resolution.

class NewShape {
    int size = 10; // common attribute
    void draw() {
        System.out.println("Drawing a shape");
    }
}

class NewCircle extends NewShape {
    int size = 20; // hides Shape's size
    void draw() {
        System.out.println("Drawing a circle");
    }
}

class NewRectangle extends NewShape {
    int size = 30; // hides Shape's size
    void draw() {
        System.out.println("Drawing a rectangle");
    }
}

public class PolymorphicBehaviorOfObjects {
    public static void main(String[] args) {
        NewShape s;
        s = new NewShape();
        s.draw();   // Drawing a shape
        System.out.println("Size: " + s.size);
        s = new NewCircle();
        s.draw();   // Drawing a circle
        System.out.println("Size: " + s.size);

        s = new NewRectangle();
        s.draw();   // Drawing a rectangle
        System.out.println("Size: " + s.size);
    }
}
