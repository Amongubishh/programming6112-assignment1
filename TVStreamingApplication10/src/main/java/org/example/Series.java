package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Series {
    // Data fields
    private int id;
    private String name;
    private int ageRestriction;
    private int numberOfEpisodes;

    // List to hold all series
    public ArrayList<Series> seriesList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Constructor for individual series objects
    public Series(int id, String name, int ageRestriction, int numberOfEpisodes) {
        this.id = id;
        this.name = name;
        this.ageRestriction = ageRestriction;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    @Override
    public String toString() {
        return "SERIES ID: " + id +
                " SERIES NAME: " + name +
                " SERIES AGE RESTRICTION: " + ageRestriction +
                " NUMBER OF EPISODES: " + numberOfEpisodes;
    }

    // ======= Getters & Setters for testing =======
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAgeRestriction() { return ageRestriction; }
    public void setAgeRestriction(int age) { this.ageRestriction = age; }
    public int getNumberOfEpisodes() { return numberOfEpisodes; }
    public void setNumberOfEpisodes(int episodes) { this.numberOfEpisodes = episodes; }

    // ======= Helper methods for JUnit testing =======
    public Series searchSeriesById(int id) {
        for (Series s : seriesList) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public boolean updateSeriesById(int id, String newName, int newAge, int newEpisodes) {
        Series s = searchSeriesById(id);
        if (s != null) {
            s.setName(newName);
            s.setAgeRestriction(newAge);
            s.setNumberOfEpisodes(newEpisodes);
            return true;
        }
        return false;
    }

    public boolean deleteSeriesById(int id) {
        Series s = searchSeriesById(id);
        if (s != null) {
            seriesList.remove(s);
            return true;
        }
        return false;
    }

    public boolean isValidAgeRestriction(int age) {
        return age >= 2 && age <= 18;
    }

    // ======= Original console interaction methods =======
    public void captureSeries() {
        System.out.println("\nCAPTURE A NEW SERIES");
        System.out.println("--------------------");
        System.out.print("Enter the series id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the series name: ");
        String name = scanner.nextLine();

        int ageRestriction;
        while (true) {
            System.out.print("Enter the series age restriction: ");
            String ageInput = scanner.nextLine();
            try {
                ageRestriction = Integer.parseInt(ageInput);
                if (isValidAgeRestriction(ageRestriction)) break;
                else System.out.println("You have entered an incorrect series age!!!");
            } catch (NumberFormatException e) {
                System.out.println("You have entered an incorrect series age!!!");
            }
        }

        System.out.print("Enter the number of episodes for " + name + ": ");
        int episodes = scanner.nextInt();
        scanner.nextLine();

        seriesList.add(new Series(id, name, ageRestriction, episodes));
        System.out.println("Series processed successfully!!!");
    }

    public void menu() {
        while (true) {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series age restriction.");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025.");
            System.out.println("(6) Exit Application");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: captureSeries(); break;
                case 2:
                    System.out.print("\nEnter the series id to search: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();
                    Series found = searchSeriesById(searchId);
                    if (found != null) System.out.println("\n" + found);
                    else System.out.println("No series data could be found for id: " + searchId);
                    break;
                case 3:
                    System.out.print("\nEnter the series id to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the new series name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter the new age restriction: ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the new number of episodes: ");
                    int newEpisodes = scanner.nextInt();
                    scanner.nextLine();
                    if (updateSeriesById(updateId, newName, newAge, newEpisodes))
                        System.out.println("Series updated successfully!!!");
                    else System.out.println("Series with id " + updateId + " not found.");
                    break;
                case 4:
                    System.out.print("\nEnter the series id to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    if (deleteSeriesById(deleteId)) System.out.println("Series deleted successfully!!!");
                    else System.out.println("Series with id " + deleteId + " not found.");
                    break;
                case 5:
                    System.out.println("\nSeries Report - 2025");
                    int count = 1;
                    for (Series s : seriesList) {
                        System.out.println("Series " + count++);
                        System.out.println(s);
                    }
                    if (seriesList.isEmpty()) System.out.println("No series available.");
                    break;
                case 6:
                    System.out.println("Exiting application. Goodbye!");
                    System.exit(0);
                    break;
                default: System.out.println("Invalid choice. Try again."); break;
            }

            System.out.println("\nEnter (1) to launch menu or any other key to exit");
            String again = scanner.nextLine();
            if (!again.equals("1")) System.exit(0);
        }
    }
}
