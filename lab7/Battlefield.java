import java.util.Scanner;

public class Battlefield {
    //  TODO
    // inisiasi generics yang digunakan
    private Scanner scanner = new Scanner(System.in);

    public void runMenu() {

        while (true) {
            // Menu utama
            System.out.println("\nWelcome to the Battlefield Simulator!");
            System.out.println("1. Add Warrior");
            System.out.println("2. Display Warriors");
            System.out.println("3. Simulate Battle");
            System.out.println("4. Revive Warrior");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    // TODO
                    break;
                case 2:
                    // TODO
                    break;
                case 3:
                    // TODO
                    break;
                case 4:
                    // TODO
                    break;
                case 5:
                    System.out.println("--------Game Over--------");
                    System.out.println("░░░░░░░█▐▓▓░████▄▄▄█▀▄▓▓▓▌█");
                    System.out.println("░░░░░▄█▌▀▄▓▓▄▄▄▄▀▀▀▄▓▓▓▓▓▌█");
                    System.out.println("░░░▄█▀▀▄▓█▓▓▓▓▓▓▓▓▓▓▓▓▀░▓▌█");
                    System.out.println("░░█▀▄▓▓▓███▓▓▓███▓▓▓▄░░▄▓▐█");
                    System.out.println("░█▌▓▓▓▀▀▓▓▓▓███▓▓▓▓▓▓▓▄▀▓▓▐█");
                    System.out.println("▐█▐██▐░▄▓▓▓▓▓▀▄░▀▓▓▓▓▓▓▓▓▓▌█▌");
                    System.out.println("█▌███▓▓▓▓▓▓▓▓▐░░▄▓▓███▓▓▓▄▀▐█");
                    System.out.println("█▐█▓▀░░▀▓▓▓▓▓▓▓▓▓██████▓▓▓▓▐█");
                    System.out.println("▌▓▄▌▀░▀░▐▀█▄▓▓██████████▓▓▓▌█▌");
                    System.out.println("▌▓▓▓▄▄▀▀▓▓▓▀▓▓▓▓▓▓▓▓█▓█▓█▓▓▌█▌");
                    System.out.println("█▐▓▓▓▓▓▓▄▄▄▓▓▓▓▓▓█▓█▓█▓█▓▓▓▐█");
                    return;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, 3, 4, or 5.");
            }
        }
    }

    // Method untuk tambah warrior ke Arraylist
    private void addWarrior() {
        // Minta tipe warrior
        System.out.println();
        System.out.println("Select type of warrior:");
        System.out.println("1. Tank");
        System.out.println("2. Archer");
        System.out.println("3. Mage");
        int type = getValidInt("Choose an option: ", 1, 3);

        System.out.print("Enter Warrior name: ");
        String name = scanner.nextLine().trim();

        int health = getValidInt("Enter Warrior health (500 to 5000): ", 500, 5000);
        int attack = getValidInt("Enter Warrior attack (30 to 1000): ", 30, 1000);
        int defense = getValidInt("Enter Warrior defense (0 to 250): ", 0, 250);

        Warrior warrior = null;

        // Tambah validasi sesuai tipe warrior
        if (type == 1) {
            // TODO

        } else if (type == 2) {
            // TODO

        } else if (type == 3) {
            // TODO
        }

        // TODO
        // Tambah warrior ke List
    }

    // Method untuk validasi int
    private int getValidInt(String prompt, int min, int max) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid number!");
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextInt();
            scanner.nextLine();
            if (input < min || input > max) {
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            }
        } while (input < min || input > max);
        return input;
    }

    // Method untuk validasi double
    private double getValidDouble(String prompt, double min, double max) {
        double input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextDouble()) {
                System.out.println("That's not a valid number!");
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextDouble();
            scanner.nextLine();
            if (input < min || input > max) {
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            }
        } while (input < min || input > max);
        return input;
    }

    // Method untuk display semua warrior
    public void displayWarriors() {
        // TODO 
        // Sort menggunakan collections berdasarkan nama warrior
        System.out.println("\nCurrent warriors in the battlefield:");
        System.out.println(
                "+------------+-----------------+---------+---------+---------+---------+------------+------------+");
        System.out.printf("| %-10s | %-15s | %-7s | %-7s | %-7s | %-7s | %-10s | %-10s |\n", "Type", "Name", "Attack",
                "Defense", "Health", "Shield", "Crit Rate", "Crit Dmg");
        System.out.println(
                "+------------+-----------------+---------+---------+---------+---------+------------+------------+");
        // TODO
        // Print semua warrior di dalam List
    }

    // Method untuk simulasi attack
    public void simulateBattle() {
        // TODO
        // ambil List
        
        System.out.println("Select the attacking warrior:");
        // TODO
        // Print setiap warrior di List dan lakukan validasi index attacker
        System.out.println();
        int attackerIndex;

        System.out.println("Select the defending warrior:");
        // TODO
        // Print setiap defender di List dan lakukan validasi index defender
        // Pastikan index defender dan attacker berbeda
        System.out.println();
        int defenderIndex;

        // TODO
        // Simulasi attacking dan defending beserta outputnya
    }

    // Method untuk membangkitkan warrior
    public void revive() {
        // TODO: Implementasi cara membangkitkan warrior
    }

    public static void main(String[] args) {
        Battlefield battlefield = new Battlefield();
        battlefield.runMenu();
    }
}
