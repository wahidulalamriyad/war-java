package basic;

public class ForEachLoop {
    public static void main(String[] args) {
        // Create an array of integers
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }

        // Use a for-each loop to iterate through the array and print each number
        for (int number : numbers) {
            System.out.println(number);
        }

        for (int j = numbers.length - 1; j >= 0; j--) {
            System.out.println(numbers[j]);
        }
    }
}
