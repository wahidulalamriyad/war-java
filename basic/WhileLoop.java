package basic;

import java.util.Scanner;

public class WhileLoop {
    public static void main(String[] args) {
        // Print numbers from 1 to 10 using a while loop
        int i = 1;
        while (i <= 10) {
            System.out.println(i);
            i++;
        }

        // Example of a while loop that continues until a certain condition is met
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("exit")) {
            System.out.print("Type 'exit' to quit: ");
            input = scanner.nextLine().toLowerCase().trim();
        }

        scanner.close();
    }
}
