package lastpencil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Objective 1
        int numberOfPencils = takeInitialPencil();

        String firstUser = firstUser();
        String name = "John";
        String bot = "Jack";
        boolean isBotMove = firstUser.equals("Jack");

        while (true) {
            if (numberOfPencils <= 0) break;

            System.out.println("|".repeat(numberOfPencils));
            if (isBotMove) System.out.println(bot + "'s turn!");
            else System.out.println(name + "'s turn!");
            String penStr;
            int pen;
            while (true) {
                if (isBotMove) {
                    if (numberOfPencils == 1) {
                        pen = 1;
                        System.out.println(pen);
                        break;
                    }
                    penStr = botStrategy(numberOfPencils);
                } else
                    penStr = scanner.next();
                try {
                    pen = Integer.parseInt(penStr);
                    if (pen == 1 || pen == 2 || pen == 3) {
                        if (numberOfPencils >= pen) {
                            if (isBotMove)
                                System.out.println(pen);
                            break;
                        } else System.out.println("Too many pencils were taken");
                    } else
                        System.out.println("Possible values: '1', '2' or '3'");

                } catch (NumberFormatException e) {
                    System.out.println("Possible values: '1', '2' or '3'");
                }
            }
            numberOfPencils -= pen;
            if (numberOfPencils == 0)
                break;
            isBotMove = !isBotMove;
            // name = changeName(name);
        }
        String winner = !isBotMove ? bot : name;
        System.out.println(winner + " won!");
    }

    public static String firstUser() {
        System.out.println("Who will be the first (John, Jack):");
        String name;
        while (true) {
            name = scanner.next();
            if (name.equals("John") || name.equals("Jack"))
                break;
            System.out.println("Choose between 'John' and 'Jack'");
        }
        return name;
    }

    public static String changeName(String name) {
        if (name.trim().equals("John")) return "Jack";
        return "John";
    }

    public static int takeInitialPencil() {
        int numberOfPencils;
        System.out.println("How many pencils would you like to use:");
        while (true) {
            try {
                numberOfPencils = Integer.parseInt(scanner.nextLine());

                if (numberOfPencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                } else break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }
        return numberOfPencils;
    }

    public static String botStrategy(int total) {
        if (total == 1) return "1";
        if (total % 4 == 0)
            return "3";
        if (total % 2 == 1)
            return "2";
        return "1";
    }
}
