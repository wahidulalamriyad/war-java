package basic;

public class DebuggingTools {
    public static void main(String[] args) {
        System.out.println("Start");
        printNumbers(4);
        System.out.println("End");
    }

    public static void printNumbers(int n) {
        for (int i = 1; i <= n; i+=2) {
            System.out.println(i);
        }
    }
}
