package basic;

import java.util.Arrays;

public class ArrayType {
    public static void main(String[] args) {
        // Single Dimension Array
        int [] numbers = new int [10];
        numbers[0] = 1;
        numbers[1] = 2;
//        numbers[10] = 3; // Will throw Exception: ArrayOutOfIndex
        System.out.println(numbers);
        System.out.println("Array numbers: " + Arrays.toString(numbers));

        // Multi Dimension Array
        int[][] multiNumbers = new int[3][4];
        multiNumbers[0][0] = 1;
        multiNumbers[0][1] = 2;
        System.out.println(multiNumbers);
        System.out.println("Array multiNumbers: " + Arrays.toString(multiNumbers));
        System.out.println("Array multiNumbers: " + Arrays.deepToString(multiNumbers));
    }
}
