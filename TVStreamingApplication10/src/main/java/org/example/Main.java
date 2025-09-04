package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("LATEST SERIES - 2025");
        System.out.println("");
        System.out.println("Enter (1) to launch menu or any other key to exit");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("1")) {
            Series seriesApp = new Series(0, "", 0, 0); // dummy object
            seriesApp.menu();
        } else {
            System.out.println("Goodbye!");
        }
    }
}
