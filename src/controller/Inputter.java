package controller;

import java.util.Scanner;

public class Inputter {

    public static final String ID_VALIDATE = "^E\\d{3}$";
    public static final String NAME_VALIDATE = "^.*\\S+.*$";
    public static final String ROLE_VALIDATE = "(?i)^(Developer|Tester|Manager|HR)$";
    public static final String STATUS_VALIDATE = "(?i)^(active|inactive)$";
    static Scanner sc = new Scanner(System.in);

    public static String input(String label) {
        System.out.print(label);
        String input = sc.nextLine();
        return input;
    }

    public static String inputRequired(String label, String regex) {
        String input;
        do {
            System.out.print(label);
            input = sc.nextLine();
        } while (!input.matches(regex));
        return input;
    }

    public static String inputOptional(String label, String regex) {
        String input;
        while (true) {
            System.out.print(label);
            input = sc.nextLine();
            if (input == null || input.isEmpty()) {
                return "";
            }
            if (input.matches(regex)) {
                return input;
            }
        }
    }
    public static int inputInt(String label, int min, int max) {
        int number;
        while (true) {
            try {
                System.out.print(label);
                number = Integer.parseInt(sc.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + "!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format! Please enter an integer.");
            }
        }
    }
    public static double inputDouble(String label, double min) {
        double number;
        while (true) {
            try {
                System.out.print(label);
                number = Double.parseDouble(sc.nextLine().trim());
                if (number > min) {
                    return number;
                } else {
                    System.out.println("Please enter a number greater than " + min + "!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format! Please enter a real number.");
            }
        }
    }
   public static int inputOptionalInt(String label, int min, int max) {
        String input;
        int number;
        while (true) {
            System.out.print(label);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return -1;
            }
            try {
                number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + " or leave it empty!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format! Please enter an integer or leave it empty.");
            }
        }
    }
    public static double inputOptionalDouble(String label, double min) {
        String input;
        double number;
        while (true) {
            System.out.print(label);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return -1.0;
            }
            try {
                number = Double.parseDouble(input);
                if (number >= min) {
                    return number;
                } else {
                    System.out.println("Please enter a number greater than or equal to " + min + " or leave it empty!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format! Please enter a real number or leave it empty.");
            }
        }
    }
    public static boolean inputYesNo(String label) {
    String input;
    while (true) {
        System.out.print(label);
        input = sc.nextLine().trim();
        if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
            return true;
        }
        if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("NO")) {
            return false;
        }
        System.out.println("Please enter Y (Yes) or N (No)!");
    }
}
}
