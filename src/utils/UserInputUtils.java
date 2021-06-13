package utils;

import java.util.Scanner;

public class UserInputUtils {
    public static int getValidIntOptionInput(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int selectedOption;
        boolean hasSelectedValidOption;
        do {
            selectedOption = scanner.nextInt();
            hasSelectedValidOption = selectedOption <= min || selectedOption >= max;
            if (!hasSelectedValidOption) {
                System.out.println("Please select a valid option");
            }
        } while (hasSelectedValidOption);
        return selectedOption;
    }
}
