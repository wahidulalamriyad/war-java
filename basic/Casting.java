package basic;

public class Casting {
    public static void main(String[] args) {
        // Implicit Casting (Widening)
        // byte -> short -> char -> int -> long -> float -> double
        int intValue = 100;
        double doubleValue = intValue; // int to double
        System.out.println("Implicit Casting: int to double: " + doubleValue);

        // Explicit Casting (Narrowing)
        // double -> float -> long -> int -> char -> short -> byte
        double anotherDoubleValue = 9.78;
        int anotherIntValue = (int) anotherDoubleValue; // double to int
        System.out.println("Explicit Casting: double to int: " + anotherIntValue);

        // String to int conversion
        String numberString = "123";
        int parsedInt = Integer.parseInt(numberString);
        System.out.println("String to int: " + parsedInt);

        // int to String conversion
        int num = 456;
        String intToString = Integer.toString(num);
        System.out.println("int to String: " + intToString);

        // String to double conversion
        String doubleString = "45.67";
        double parsedDouble = Double.parseDouble(doubleString);
        System.out.println("String to double: " + parsedDouble);

        // double to String conversion
        double dblNum = 89.01;
        String doubleToString = Double.toString(dblNum);
        System.out.println("double to String: " + doubleToString);
    }
}
