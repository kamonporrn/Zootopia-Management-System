package User_system;

public class AnimalInfoDisplayer {
    
    public static void displayAnimalInfo(String name, String zone, String type, String description) {
        System.out.println("*************************");
        System.out.println(name);
        System.out.println("*************************");
        System.out.println("Live in Zone: " + zone);
        System.out.println("Animal type: " + type);
        System.out.println("Description: " + description);
        System.out.println("*************************");
    }
}
