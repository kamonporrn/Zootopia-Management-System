package User_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AnimalInfoViewer {

    public static void viewAnimalInfo(String animalName, String zoneName) {
        try {
            File file = new File("animals.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                String name = data[0];
                String zone = data[1];
                String type = data[2];
                String description = data[3];

                if (name.equalsIgnoreCase(animalName) && zone.equalsIgnoreCase(zoneName)) {
                    System.out.println("*************************");
                    System.out.println(name);
                    System.out.println("*************************");
                    System.out.println("Live in Zone: " + zone);
                    System.out.println("Animal type: " + type);
                    System.out.println("Description: " + description);
                    System.out.println("*************************");
                    break; // When the desired animal is found, the work is completed.
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
            e.printStackTrace();
        }

        // Return to the page showing the list of animals in the zone.
        AnimalViewer.viewAnimalsInZone(zoneName, null);
    }
}
