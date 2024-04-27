package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import User_system.AnimalSearchViewer;
import User_system.Feeding;
import Zookeeper_System.AnimalManager;
import Zookeeper_System.FeedingManager;
import Zookeeper_System.ZoneManager;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        login();
    }

    public static void login() {
        System.out.println("==== Zootopia ====");
        System.out.print("Username: ");
        String username = scanner.next(); // ใช้ next() แทน nextLine()
        System.out.print("Password: ");
        String password = scanner.next(); // ใช้ next() แทน nextLine()
        scanner.nextLine(); // เพื่อล้างค่า newline
        System.out.println("==================");
        System.out.println();
        
        // เช็ค username และ password
        if (isUser(username, password)) {
            userMenu();
        } else if (isZookeeper(username, password)) {
            zookeeperMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
            login();
        }
    }

    public static boolean isUser(String username, String password) {
        // อ่านจากไฟล์หรือฐานข้อมูล เช็คว่า username และ password ตรงกับข้อมูลผู้ใช้หรือไม่
        try {
            File file = new File("users.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                if (data[0].equals(username) && data[1].equals(password)) {
                    return true;
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
        return false; // แก้โค้ดตรงนี้ให้ตรวจสอบจากข้อมูลที่เก็บ
    }

    public static boolean isZookeeper(String username, String password) {
        // อ่านจากไฟล์หรือฐานข้อมูล เช็คว่า username และ password ตรงกับข้อมูล Zookeeper หรือไม่
        try {
            File file = new File("zookeepers.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                if (data[0].equals(username) && data[1].equals(password)) {
                    return true;
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
        return false; // แก้โค้ดตรงนี้ให้ตรวจสอบจากข้อมูลที่เก็บ
    }

    public static void userMenu() {
        System.out.println("==== Zootopia System ====");
        System.out.println("1. View animals in a zone");
        System.out.println("2. View animals by searching name");
        System.out.println("3. Feeding");
        System.out.println("4. Logout");
        System.out.println("===========================");
        System.out.print("Enter your choice (1 - 4): ");
        int choice = scanner.nextInt();
        System.out.println();

        // จัดการตามเมนูที่ผู้ใช้เลือก
        switch (choice) {
            case 1:
                Zone.viewZonesMenu(scanner);
                break;
            case 2:
                AnimalSearchViewer.searchAnimalByName();
                break;
            case 3:
                Feeding.selectZone();
                break;
            case 4:
                System.out.println("Logged out.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                userMenu();
        }
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
            }
        } while (choice != 4);
    }
}
