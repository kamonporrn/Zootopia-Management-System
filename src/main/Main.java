package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Person.Person;
import Person.User;
import Person.Zookeeper;
import User_system.AnimalSearchViewer;
import User_system.Feeding;
import Zookeeper_System.AnimalManager;
import Zookeeper_System.FeedingManager;
import Zookeeper_System.ZoneManager;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Polymorphism: Create Person object (either User or Zookeeper)
        Person currentUser = login(); // Assume login() returns either a User or Zookeeper object
        if(currentUser instanceof User) {
            userMenu(); // User-specific menu
        } else if(currentUser instanceof Zookeeper) {
            zookeeperMenu(); // Zookeeper-specific menu
        }
    }

    // Method for login
    private static Person login() {
        System.out.println("==== Zootopia ====");
        System.out.print("Username: ");
        String username = scanner.next(); // Use next() instead of nextLine()
        System.out.print("Password: ");
        String password = scanner.next(); // Use next() instead of nextLine()
        scanner.nextLine(); // to clear newline value
        System.out.println("==================");
        System.out.println();
        
        // Check username and password
        if (isUser(username, password)) { // Check if the entered username and password belong to a regular user
            return new User(username, password); // Create a new User object
        } else if (isZookeeper(username, password)) { // Check if the entered username and password belong to a zookeeper
            return new Zookeeper(username, password); // Create a new Zookeeper object
        } else {
            System.out.println("Invalid username or password. Please try again.");// If the username or password is incorrect
            return login(); // Recursively call login() until a correct username and password are entered
        }

    }

    public static boolean isUser(String username, String password) {
        // Read from file or database, check whether username and password match user information or not.
        try {
            File file = new File("users.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                if (data[0].equals(username) && data[1].equals(password)) {
                    fileScanner.close();
                    return true; // Return true if username and password match a user in the file
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
        return false; // Return false if the username and password don't match any user in the file
    }

    public static boolean isZookeeper(String username, String password) {
        // Read from a file or database. Check if the username and password match the Zookeeper information.
        try {
            File file = new File("zookeepers.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                if (data[0].equals(username) && data[1].equals(password)) {
                    fileScanner.close();
                    return true; // Return true if username and password match a zookeeper in the file
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
        return false; // Return false if the username and password don't match any zookeeper in the file
    }

        public static void userMenu() {
            int choice;
            do{
            System.out.println("==== Zootopia System ====");
            System.out.println("1. View animals in a zone");
            System.out.println("2. View animals by searching name");
            System.out.println("3. Feeding");
            System.out.println("4. Logout");
            System.out.println("===========================");
            System.out.print("Enter your choice (1 - 4): ");
            choice = scanner.nextInt();
            System.out.println();

            // Manage according to the menu selected by the user.
            switch (choice) {
                case 1:
                    Zone.viewZonesMenu(scanner); // Display zones for viewing animals
                    break;
                case 2:
                    AnimalSearchViewer.searchAnimalByName(); // Search for animals by name
                    break;
                case 3:
                    Feeding.selectZone(); // Select a zone for feeding animals
                    break;
                case 4:
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    userMenu();
                    break;
                } 
            }while (choice != 4);
        }

        public static void zookeeperMenu() {
            int choice;
            do {
                System.out.println("==== Zootopia Management System ====");
                System.out.println("1. Manage Zone");
                System.out.println("2. Manage Animal");
                System.out.println("3. Manage Feeding");
                System.out.println("4. Logout");
                System.out.println("====================================");
                System.out.print("Enter your choice (1 - 4): ");
                choice = scanner.nextInt();
                System.out.println();

                switch (choice) {
                    case 1:
                        ZoneManager zoneManager = new ZoneManager();
                        zoneManager.manageZone();
                        break;
                    case 2:
                        AnimalManager animalManager = new AnimalManager();
                        animalManager.manageAnimal();
                        break;
                    case 3:
                        FeedingManager feedingManager = new FeedingManager();
                        feedingManager.manageFeeding();
                        break;
                    case 4:
                        System.out.println("Logged out.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        zookeeperMenu();
                }
            } while (choice != 4);
        }
    
}
