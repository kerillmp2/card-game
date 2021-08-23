package core.utils;

import java.util.List;
import java.util.Scanner;

public class Selector {
    public static int selectWithDescription(List<? extends WithDescription> options) {
        int selectedNumber = -1;
        while (selectedNumber == -1) {
            int counter = 0;
            for (WithDescription option : options) {
                counter += 1;
                System.out.printf("%d. %s\n", counter, option.getNameWithDescription());
            }
            if (counter >= 1) {
                selectedNumber = readCommandNumber(1, counter);
                if (selectedNumber == -1) {
                    System.out.println("Введите число от 1 до " + counter);
                }
            } else {
                selectedNumber = 0;
            }
        }
        return selectedNumber;
    }

    public static int select(List<? extends Selectable> options) {
        int selectedNumber = -1;
        while (selectedNumber == -1) {
            int counter = 0;
            for (Selectable option : options) {
                counter += 1;
                System.out.printf("%d. %s\n", counter, option.getName());
            }
            if (counter >= 1) {
                selectedNumber = readCommandNumber(1, counter);
                if (selectedNumber == -1) {
                    System.out.println("Введите число от 1 до " + counter);
                }
            } else {
                selectedNumber = 0;
            }
        }
        return selectedNumber;
    }

    private static int readCommandNumber(int from, int to) {
        Scanner in = new Scanner(System.in);
        int input = 123123;
        try {
            input = in.nextInt();
            if (input >= from && input <= to) {
                return input;
            } else {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }
}