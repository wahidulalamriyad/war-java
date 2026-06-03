package basic;

public class TernaryOperator {
    public static void main(String[] args) {
        int income = 120000;
        String className = (income > 100000) ? "First" : "Economy";
        System.out.println("Class: " + className);
    }
}
