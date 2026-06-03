package basic;

import java.text.NumberFormat;

public class FormattedNumbers {
    public static void main(String[] args) {
        // NumberFormat currencyFormatter = new NumberFormat();
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        String formattedValue = currencyFormatter.format(123456.789);
        System.out.println(formattedValue);

        NumberFormat percentFormatter = NumberFormat.getPercentInstance();
        String percentValue = percentFormatter.format(0.75);
        System.out.println(percentValue);

        String intValue = NumberFormat.getIntegerInstance().format(12345.678);
        System.out.println(intValue);

        String methodChaining = NumberFormat.getCurrencyInstance().format(98765.4321);
        System.out.println(methodChaining);
    }
}
