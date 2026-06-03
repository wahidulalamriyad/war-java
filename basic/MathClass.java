package basic;

public class MathClass {
    public static void main(String[] args) {
        int roundResult = Math.round(1.1f);
        System.out.println(roundResult);

        double powerResult = Math.pow(2, 3);
        System.out.println(powerResult);

        double sqrtResult = Math.sqrt(16);
        System.out.println(sqrtResult);

        double randomValue = Math.random();
        System.out.println(randomValue);

        int ceilValue = (int) Math.ceil(4.3);
        System.out.println(ceilValue);

        int floorValue = (int) Math.floor(4.7);
        System.out.println(floorValue);

        double maxValue = Math.max(10, 20);
        System.out.println(maxValue);

        double minValue = Math.min(10, 20);
        System.out.println(minValue);
    }
}
