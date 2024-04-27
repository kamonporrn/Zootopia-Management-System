package User_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Feeding {
    private static Scanner scanner = new Scanner(System.in);

    public static void selectZone() {
        System.out.println("==== Select Zone ====");
        System.out.println("1. Savanna");
        System.out.println("2. Desert");
        System.out.println("3. Rain Forest");
        System.out.println("4. Mountain");
        System.out.println("5. Back to main menu");
        System.out.println("====================");
        System.out.print("Enter zone number (1 - 5): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        switch (choice) {
            case 1:
                feedInZone("Savanna");
                break;
            case 2:
                feedInZone("Desert");
                break;
            case 3:
                feedInZone("Rain Forest");
                break;
            case 4:
                feedInZone("Mountain");
                break;
            case 5:
                System.out.println("Back to main menu.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                selectZone();
                break;
        }
    }

    private static void feedInZone(String zoneName) {
        System.out.println("==== Feeding in " + zoneName + " ====");
        showAnimalsInZone(zoneName); // แสดงลิสต์ของสัตว์ในโซน
        // เมื่อแสดงลิสต์เสร็จแล้วให้เรียกเมทอด selectAnimal เพื่อให้ผู้ใช้เลือกสัตว์
        selectAnimal(zoneName);
    }

    private static void showAnimalsInZone(String zoneName) {
        System.out.println("==== Animals in " + zoneName + " ====");
        ArrayList<String> animals = getAnimalsInZone(zoneName);
        if (animals.isEmpty()) {
            System.out.println("No animals found in " + zoneName);
        } else {
            for (int i = 0; i < animals.size(); i++) {
                System.out.println((i + 1) + ". " + animals.get(i));
            }
        }
        System.out.println("=======================");
    }

    private static ArrayList<String> getAnimalsInZone(String zoneName) {
        ArrayList<String> animals = new ArrayList<>();
        try {
            File file = new File("animals.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                String animalName = data[0];
                String animalZone = data[1];
                if (animalZone.equalsIgnoreCase(zoneName)) {
                    animals.add(animalName);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
        return animals;
    }

    private static void selectAnimal(String zoneName) {
        System.out.print("Select animal to feed (Enter 0 to go back): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        if (choice == 0) {
            selectZone(); // กลับไปเลือกโซนอีกครั้ง
        } else {
            ArrayList<String> animals = getAnimalsInZone(zoneName);
            if (choice >= 1 && choice <= animals.size()) {
                String selectedAnimal = animals.get(choice - 1);
                feedAnimal(selectedAnimal, zoneName); // นำสัตว์ที่เลือกไปให้อาหาร
            } else {
                System.out.println("Invalid choice. Please try again.");
                selectAnimal(zoneName); // เรียกเมทอดนี้ใหม่หากผู้ใช้ใส่ตัวเลขที่ไม่ถูกต้อง
            }
        }
    }
    
    private static void feedAnimal(String animalName, String zoneName) {
        System.out.println("Feeding " + animalName + " in " + zoneName);
        // เมื่อผู้ใช้เลือกสัตว์แล้ว ให้แสดงลิสต์ประเภทอาหาร
        showFoodList();
        // และเรียกใช้เมทอด selectFoodType เพื่อให้ผู้ใช้เลือกประเภทอาหาร
        selectFood(animalName, zoneName);
    }

    private static void showFoodList() {
        try {
            File file = new File("food.txt");
            Scanner scanner = new Scanner(file);
            System.out.println("==== Food List ====");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            System.out.println("==================");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
    }

    private static void selectFood(String animalName, String zoneName) {
        System.out.print("Enter the food name to feed " + animalName + " (Enter 0 to go back): ");
        String foodName = scanner.nextLine();
        if (foodName.equals("0")) {
            selectAnimal(zoneName);
        } else {
            boolean foodFound = feedAnimalByName(animalName, foodName);
            if (!foodFound) {
                System.out.println("Food not found. Please try again.");
                selectFood(animalName, zoneName);
            }else {
                System.out.println("Thank you for feeding the animal in Zootopia.");
                selectAnimal(zoneName); // รีเทิร์นไปที่การเลือกสัตว์ในโซน
            }
        }
    }

    private static boolean feedAnimalByName(String animalName, String foodName) {
        try {
            File file = new File("food.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data[0].equalsIgnoreCase(foodName)) {
                    System.out.println("Feeding " + animalName + " with " + foodName);
                    // ทำตามความต้องการ เช่น เพิ่มการนับปริมาณ หรือบันทึกลงไฟล์
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
        return false;
    }
}
