package Zookeeper_System;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ZoneManager {
    private Scanner scanner;

    public ZoneManager() {
        this.scanner = new Scanner(System.in);
    }

    public void manageZone() {
        int choice;
        do {
            System.out.println("==== Manage Zone ====");
            System.out.println("1. Add Zone");
            System.out.println("2. Remove Zone");
            System.out.println("3. Back to main menu");
            System.out.println("=====================");
            System.out.print("Enter your choice (1 - 3): ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addZone();
                    break;
                case 2:
                    removeZone();
                    break;
                case 3:
                    System.out.println("Back to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    private void addZone() {
        try {
            FileWriter writer = new FileWriter("zones.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            System.out.print("Enter new zone name: ");
            scanner.nextLine(); // Clear the buffer
            String zoneName = scanner.nextLine();
            System.out.print("Enter description for the zone: ");
            String description = scanner.nextLine();

            bufferedWriter.write(zoneName + "," + description);
            bufferedWriter.newLine();
            bufferedWriter.close();

            System.out.println("Zone '" + zoneName + "' added successfully.");

        } catch (IOException e) {
            System.out.println("Error occurred while adding zone: " + e.getMessage());
        }
    }

    private void removeZone() {
        try {
            System.out.print("Enter zone name to remove: ");
            scanner.nextLine(); // Clear the buffer
            String zoneName = scanner.nextLine();

            File file = new File("zones.txt");
            File tempFile = new File("temp.txt");
            Scanner fileScanner = new Scanner(file);
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.startsWith(zoneName + ",")) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            fileScanner.close();
            writer.close();

            if (!file.delete()) {
                System.out.println("Error: Failed to delete the zone.");
                return;
            }

            if (!tempFile.renameTo(file)) {
                System.out.println("Error: Failed to rename the file.");
            }

            System.out.println("Zone '" + zoneName + "' removed successfully.");

        } catch (IOException e) {
            System.out.println("Error occurred while removing zone: " + e.getMessage());
        }
    }
}
