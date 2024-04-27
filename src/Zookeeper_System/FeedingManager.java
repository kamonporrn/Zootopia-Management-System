package Zookeeper_System;

import java.io.*;
import java.util.Scanner;

public class FeedingManager {
    private Scanner scanner;

    public FeedingManager() {
        this.scanner = new Scanner(System.in);
    }

    public void manageFeeding() {
        int choice;
        do {
            System.out.println("==== Manage Feeding ====");
            System.out.println("1. Add Food");
            System.out.println("2. Remove Food");
            System.out.println("3. Back to main menu");
            System.out.println("========================");
            System.out.print("Enter your choice (1 - 3): ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addFood();
                    break;
                case 2:
                    removeFood();
                    break;
                case 3:
                    System.out.println("Back to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    private void addFood() {
        try {
            FileWriter writer = new FileWriter("food.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            System.out.print("Enter food name: ");
            scanner.nextLine(); // Clear the buffer
            String foodName = scanner.nextLine();
            System.out.print("Enter food type: ");
            String foodType = scanner.nextLine();

            bufferedWriter.write(foodName + "," + foodType);
            bufferedWriter.newLine();
            bufferedWriter.close();

            System.out.println("Food '" + foodName + "' added successfully.");

        } catch (IOException e) {
            System.out.println("Error occurred while adding food: " + e.getMessage());
        }
    }

    private void removeFood() {
        System.out.print("Enter food name to remove: ");
        scanner.nextLine(); // ล้าง white space ด้านหน้าและด้านหลัง
        String foodName = scanner.nextLine();
        removeFoodFromFile(foodName);
    }
    
    
    private void removeFoodFromFile(String foodName) {
        try {
            File file = new File("food.txt");
            File tempFile = new File("temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    
            String lineToRemove = foodName + ",";
            String currentLine;
    
            while ((currentLine = reader.readLine()) != null) {
                // เช็คว่าบรรทัดปัจจุบันมีชื่ออาหารที่ต้องการลบหรือไม่
                if (!currentLine.contains(lineToRemove)) {
                    writer.write(currentLine + System.getProperty("line.separator")); // เขียนข้อมูลลงใน temp file
                }
            }
            writer.close();
            reader.close();
            
            // ลบไฟล์เดิมและเปลี่ยนชื่อไฟล์ temp เป็นชื่อไฟล์เดิม
            if (file.delete()) {
                tempFile.renameTo(file);
                System.out.println("Food '" + foodName + "' removed successfully.");
            } else {
                System.out.println("Error: Failed to remove food.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
}
