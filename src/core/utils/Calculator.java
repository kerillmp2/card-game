package core.utils;

public class Calculator {
    public static int digits(int n) {
        int digits = 0;
        while (n > 0) {
            digits += 1;
            n /= 10;
        }
        return digits;
    }
}
