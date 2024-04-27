package User_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import main.Main;

public class AnimalSearchViewer {

    private static Scanner scanner = new Scanner(System.in);

    public static void searchAnimalByName() {
        do {
            System.out.println("===== Searching animal name =====");
            System.out.print("Enter the name of the animal you want to view (or enter 0 to go back): ");
            String userInput = scanner.next(); // ใช้ next() เพื่ออ่านคำอย่างเดียว
            if (userInput.equals("0")) {
                break; // ออกจากลูปเมื่อผู้ใช้ป้อน 0
            } else {
                scanner.nextLine(); // กล่าวถึงช่องว่างหลังจาก next() ถูกเรียกใช้
                String animalName = userInput.trim(); // ตัดช่องว่างด้านหน้าและด้านหลัง
                // เรียกเมทอดเพื่อแสดงข้อมูลสัตว์
                displayAnimalInfo(animalName);
            }
        } while (true);

        // เมื่อผู้ใช้ป้อน 0 กลับไปยังเมนูหลัก
        Main.userMenu();
    }
    
    private static void displayAnimalInfo(String animalName) {
        try {
            File file = new File("animals.txt");
            Scanner scanner = new Scanner(file);
            boolean found = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                String name = data[0];
                String zone = data[1];
                String type = data[2];
                String description = data[3];
                
                if (name.equalsIgnoreCase(animalName)) {
                    found = true;
                    System.out.println("*************************");
                    System.out.println(name);
                    System.out.println("*************************");
                    System.out.println("Live in Zone: " + zone);
                    System.out.println("Animal type: " + type);
                    System.out.println("Description: " + description);
                    System.out.println("*************************");
                    break;
                }
            }
            scanner.close();
            if (!found) {
                System.out.println("Animal not found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
    }
}
