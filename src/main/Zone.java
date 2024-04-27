package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import User_system.AnimalViewer;

public class Zone {
    public static void viewZonesMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("==== Zones in Zootopia ====");
            displayZones();
            System.out.println("5. Back to main menu");
            System.out.println("===========================");
            System.out.print("Enter zone number (1 - 5): ");
            choice = scanner.nextInt();
            System.out.println();
            
            switch (choice) {
                case 1:
                    viewAnimalsInZone("Desert", scanner);
                    break;
                case 2:
                    viewAnimalsInZone("Rain Forest", scanner);
                    break;
                case 3:
                    viewAnimalsInZone("Mountain", scanner);
                    break;
                case 4:
                    viewAnimalsInZone("Savanna", scanner);
                    break;
                case 5:
                    System.out.println("Back to main menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void displayZones() {
        try {
            File file = new File("zones.txt");
            Scanner fileScanner = new Scanner(file);
            int zoneNumber = 1;
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                System.out.println(zoneNumber + ". " + data[0]);
                System.out.println("Description: " + data[1]);
                System.out.println("===========================");
                zoneNumber++;
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
    }

    private static void viewAnimalsInZone(String zoneName, Scanner scanner) {
        AnimalViewer.viewAnimalsInZone(zoneName, scanner); // ส่ง scanner ไปด้วย
    }
}
