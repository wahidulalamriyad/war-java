package basic;

import java.util.Scanner;

public class BreakAndContinue {
    public static void main(String[] args) {
        // Demonstrate break and continue in a loop
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (true) {
            System.out.print("Type 'exit' to quit: ");
            input = scanner.nextLine().toLowerCase().trim();
            if (input.equals("skip")) {
                System.out.println("Skipping this iteration.");
                continue;
            }
            if (input.equals("exit")) {
                System.out.println("Exiting the loop.");
                break;
            }
            System.out.println("You typed: " + input);
        }

        scanner.close();
    }
}
