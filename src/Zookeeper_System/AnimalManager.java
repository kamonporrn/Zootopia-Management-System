package Zookeeper_System;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalManager {
    private Scanner scanner;

    public AnimalManager() {
        this.scanner = new Scanner(System.in);
    }

    public void manageAnimal() {
        int choice;
        do {
            System.out.println("==== Manage Animal ====");
            System.out.println("1. Add Animal");
            System.out.println("2. Remove Animal");
            System.out.println("3. Back to main menu");
            System.out.println("=======================");
            System.out.print("Enter your choice (1 - 3): ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    removeAnimal();
                    break;
                case 3:
                    System.out.println("Back to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    private void addAnimal() {
        try {
            System.out.print("Enter animal name: ");
            String animalName = scanner.next();
            System.out.print("Enter zone: ");
            String zone = scanner.next();
            System.out.print("Enter animal type: ");
            String animalType = scanner.next();
            scanner.nextLine(); // Consume newline left-over
            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            // Add animal to the file
            FileWriter fw = new FileWriter("animals.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(animalName + "," + zone + "," + animalType + "," + description);
            bw.newLine();
            bw.close();

            System.out.println("Animal added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding the animal.");
            e.printStackTrace();
        }
    }

    private void removeAnimal() {
        try {
            System.out.print("Enter animal name to delete: ");
            String animalNameToDelete = scanner.next();

            // Read all animals from the file
            ArrayList<String> animals = new ArrayList<>();
            File file = new File("animals.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                animals.add(fileScanner.nextLine());
            }
            fileScanner.close();

            // Remove animal based on the given name
            boolean found = false;
            FileWriter fw = new FileWriter("temp_animals.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (String animal : animals) {
                String[] parts = animal.split(",");
                if (!parts[0].equals(animalNameToDelete)) {
                    bw.write(animal);
                    bw.newLine();
                } else {
                    found = true;
                }
            }
            bw.close();

            // Delete the old file and rename the temporary file to the original name
            File oldFile = new File("animals.txt");
            File newFile = new File("temp_animals.txt");
            oldFile.delete();
            newFile.renameTo(oldFile);

            if (found) {
                System.out.println("Animal removed successfully.");
            } else {
                System.out.println("Animal not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while removing the animal.");
            e.printStackTrace();
        }
    }
}
