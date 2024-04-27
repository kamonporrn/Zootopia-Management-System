package User_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalViewer {

    private static Scanner scanner = new Scanner(System.in);

    public static void viewAnimalsInZone(String zoneName, Scanner scanner) {
        if (scanner == null) {
            System.out.println();
            return; // ออกจากเมทอดถ้า scanner เป็น null
        }
        
        System.out.println("==== Animals in " + zoneName + " ====");
        ArrayList<String> animals = getAnimalsInZone(zoneName);
        if (animals.isEmpty()) {
            System.out.println("No animals found in " + zoneName);
        } else {
            for (int i = 0; i < animals.size(); i++) {
                System.out.println((i + 1) + ". " + animals.get(i));
            }
        }
        System.out.print("Enter the number of the animal to view details (or enter 0 to go back to zone selection): ");
        int animalChoice = scanner.nextInt();
        if (animalChoice == 0) {
            // ถ้าผู้ใช้ป้อน 0 กลับไปที่เมนูเลือกโซน
            return;
        }
        if (animalChoice >= 1 && animalChoice <= animals.size()) {
            String selectedAnimal = animals.get(animalChoice - 1);
            AnimalInfoViewer.viewAnimalInfo(selectedAnimal, zoneName); // ส่งข้อมูลไปยัง AnimalInfoViewer
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static ArrayList<String> getAnimalsInZone(String zoneName) {
        ArrayList<String> animals = new ArrayList<>();
        try {
            File file = new File("animals.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");
                String animalName = data[0];
                String animalZone = data[1];
                if (animalZone.equalsIgnoreCase(zoneName)) {
                    animals.add(animalName);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
        return animals;
    }
}
