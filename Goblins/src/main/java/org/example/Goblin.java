package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Goblin {
    private int id;
    private String name;
    private int strength;
    private boolean isAlive;

    // List of goblins
    public ArrayList<Goblin> goblinList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Constructor
    public Goblin(int id, String name, int strength, boolean isAlive) {
        this.id = id;
        this.name = name;
        this.strength = strength;
        this.isAlive = isAlive;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getStrength() { return strength; }
    public void setStrength(int strength) { this.strength = strength; }
    public boolean isAlive() { return isAlive; }
    public void setAlive(boolean alive) { isAlive = alive; }

    @Override
    public String toString() {
        return "GOBLIN ID: " + id +
                " NAME: " + name +
                " STRENGTH: " + strength +
                " ALIVE: " + isAlive;
    }

    // ====== Helper Methods ======

    public Goblin searchGoblinById(int id) {
        for (Goblin g : goblinList) {
            if (g.getId() == id) return g;
        }
        return null;
    }

    public boolean updateGoblinById(int id, String newName, int newStrength, boolean newAlive) {
        Goblin g = searchGoblinById(id);
        if (g != null) {
            g.setName(newName);
            g.setStrength(newStrength);
            g.setAlive(newAlive);
            return true;
        }
        return false;
    }

    public boolean deleteGoblinById(int id) {
        Goblin g = searchGoblinById(id);
        if (g != null) {
            goblinList.remove(g);
            return true;
        }
        return false;
    }

    public boolean isValidStrength(int strength) {
        return strength >= 1 && strength <= 100;
    }

    // ====== Console App ======
    public void captureGoblin() {
        System.out.println("\nCAPTURE A NEW GOBLIN");
        System.out.print("Enter goblin id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter goblin name: ");
        String name = scanner.nextLine();

        int strength;
        while (true) {
            System.out.print("Enter goblin strength (1-100): ");
            strength = scanner.nextInt();
            scanner.nextLine();
            if (isValidStrength(strength)) break;
            else System.out.println("Invalid strength! Must be 1–100.");
        }

        System.out.print("Is the goblin alive? (true/false): ");
        boolean isAlive = scanner.nextBoolean();
        scanner.nextLine();

        goblinList.add(new Goblin(id, name, strength, isAlive));
        System.out.println("Goblin captured successfully!");
    }

    public void menu() {
        while (true) {
            System.out.println("\nGOBLIN SLAYER APP MENU");
            System.out.println("(1) Capture new goblin");
            System.out.println("(2) Search goblin by ID");
            System.out.println("(3) Update goblin");
            System.out.println("(4) Delete goblin");
            System.out.println("(5) Print goblin report");
            System.out.println("(6) Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: captureGoblin(); break;
                case 2:
                    System.out.print("Enter goblin id to search: ");
                    int sid = scanner.nextInt();
                    scanner.nextLine();
                    Goblin found = searchGoblinById(sid);
                    if (found != null) System.out.println(found);
                    else System.out.println("Goblin not found.");
                    break;
                case 3:
                    System.out.print("Enter goblin id to update: ");
                    int uid = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New name: ");
                    String newName = scanner.nextLine();
                    System.out.print("New strength (1–100): ");
                    int newStrength = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Alive? (true/false): ");
                    boolean newAlive = scanner.nextBoolean();
                    scanner.nextLine();
                    if (updateGoblinById(uid, newName, newStrength, newAlive))
                        System.out.println("Goblin updated successfully!");
                    else System.out.println("Goblin not found.");
                    break;
                case 4:
                    System.out.print("Enter goblin id to delete: ");
                    int did = scanner.nextInt();
                    scanner.nextLine();
                    if (deleteGoblinById(did)) System.out.println("Goblin deleted.");
                    else System.out.println("Goblin not found.");
                    break;
                case 5:
                    System.out.println("\nGoblin Report:");
                    int count = 1;
                    for (Goblin g : goblinList) {
                        System.out.println("Goblin " + count++);
                        System.out.println(g);
                    }
                    if (goblinList.isEmpty()) System.out.println("No goblins available.");
                    break;
                case 6:
                    System.out.println("Exiting Goblin Slayer App...");
                    System.exit(0);
                    break;
                default: System.out.println("Invalid choice.");
            }

            System.out.println("Press (1) to return to menu or any other key to exit");
            String again = scanner.nextLine();
            if (!again.equals("1")) System.exit(0);
        }
    }
}
