import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Battlefield {
    // inisiasi generics yang digunakan untuk menyimpan warrior dan fallen warrior
    WarriorList<Warrior> warriorList = new WarriorList<>();
    private Scanner scanner = new Scanner(System.in);

    public void runMenu() {

        while (true) {
            // Menu utama game
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
                    addWarrior();
                    break;
                case 2:
                    displayWarriors();
                    break;
                case 3:
                    simulateBattle();
                    break;
                case 4:
                    revive();
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
        // Minta tipe warrior yang ingin ditambahkan
        System.out.println();
        System.out.println("Select type of warrior:");
        System.out.println("1. Tank");
        System.out.println("2. Archer");
        System.out.println("3. Mage");
        int type = getValidInt("Choose an option: ", 1, 3);

        // Minta input nama, health, attack, dan defense
        System.out.print("Enter Warrior name: ");
        String name = scanner.nextLine().trim();
        
        int health = getValidInt("Enter Warrior health (500 to 5000): ", 500, 5000);
        int attack = getValidInt("Enter Warrior attack (30 to 1000): ", 30, 1000);
        int defense = getValidInt("Enter Warrior defense (0 to 250): ", 0, 250);
        
        Warrior warrior = null;
        
        // Tambah validasi sesuai tipe warrior
        if (type == 1) {
            int shield = getValidInt("Enter shield strength (0 to 500): ", 0, 500);
            warrior = new Tank(name, attack, defense, health, shield);
        } else if (type == 2) {
            double criticalRate = getValidDouble("Enter critical rate (0.0 to 1.0): ", 0, 1);
            double criticalDamage = getValidDouble("Enter critical damage multiplier (1.0 to 5.0): ", 0, 5);
            warrior = new Archer(name, attack, defense, health, criticalRate, criticalDamage);
        } else if (type == 3) {
            warrior = new Mage(name, attack, defense, health);
        }
        
        // Tambahkan warrior ke List
        warriorList.addWarrior(warrior);
        System.out.printf("\n%s has been added to the battle.\n\n", name);
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
        // Sort menggunakan collections berdasarkan nama warrior
        Collections.sort(warriorList.getWarriors());
        List<Warrior> warriors = warriorList.getWarriors();
        
        System.out.println("\nCurrent warriors in the battlefield:");
        System.out.println(
                "+------------+-----------------+---------+---------+---------+---------+------------+------------+");
        System.out.printf("| %-10s | %-15s | %-7s | %-7s | %-7s | %-7s | %-10s | %-10s |\n", "Type", "Name", "Attack",
                "Defense", "Health", "Shield", "Crit Rate", "Crit Dmg");
        System.out.println(
                "+------------+-----------------+---------+---------+---------+---------+------------+------------+");
        // Print semua warrior di dalam List
        for (Warrior warrior : warriors) {
            warrior.displayStats();
            System.out.println("+------------+-----------------+---------+---------+---------+---------+------------+------------+");
        }
    }

    // Method untuk simulasi attack
    public void simulateBattle() {
        // Validasi jumlah warrior minimal 2 (1 attacker, 1 defender)
        if (warriorList.getWarriors().size() < 2) {
            System.out.println("Not enough warriors for a battle. Please add more warriors.");
            return;
        }

        // Pilih attacker dan print semua warrior
        System.out.println("Select the attacking warrior:");
        Collections.sort(warriorList.getWarriors());
        for(int i = 0; i < warriorList.getWarriors().size(); i++) {
            System.out.println(i+1 + ". " + warriorList.getWarriors().get(i).getName());
        }

        System.out.println();
        int attackerIndex = getValidInt("Choose a warrior: ", 1, warriorList.getWarriors().size())-1;
        
        // Pilih defender dan validasi index defender
        System.out.println("Select the defending warrior:");
        for(int i = 0; i < warriorList.getWarriors().size(); i++) {
            if (i == attackerIndex) {
                continue;
            }
            System.out.println(i+1 + ". " + warriorList.getWarriors().get(i).getName());
        }
        System.out.println();
        int defenderIndex;
        do{
            defenderIndex = getValidInt("Choose a warrior: ", 1, warriorList.getWarriors().size())-1;
            if (defenderIndex == attackerIndex)
                System.out.println("Defender could not be the same as the attacker");
        } while (defenderIndex == attackerIndex);

        // Simulasi attacking dan defending beserta outputnya
        Warrior attacker = warriorList.getWarriors().get(attackerIndex);
        Warrior defender = warriorList.getWarriors().get(defenderIndex);
        System.out.println(attacker.getName() + " is attacking " + defender.getName());
        attacker.attack(defender);
        // Validasi apakah defender masih hidup, jika tidak remove dari List dan tambahkan ke fallen warrior 
        if (!defender.isAlive()) {
            System.out.println(defender.getName() + " has fallen in battle!");
            System.out.println(defender.getName()+ " has been removed fro the battle.");
            warriorList.addFallenWarrior(defender);
            warriorList.removeWarrior(defender);
        }
        // Print output jika defender masih hidup
        else{
            System.out.println(defender.getName() + " survived the attack with " + defender.getHealth() + " health remaining.");
        
        }
    }

    // Method untuk membangkitkan warrior
    public void revive() {
        // Validasi apakah fallen warrior kosong, jika tidak print warrior yang bisa di revive
        if (warriorList.getFallenWarriors().isEmpty()) {
            System.out.println("There are currently no warriors to revive.");
            return;
        }
        else{
            // Jika warrior belum di revive sebanyak 3 kali, maka warrior dihapus dari 
            // fallen warrior dan dimasukkan ke List warrior dengan health yang sesuai
            Warrior revived = warriorList.getFallenWarriors().peek();
            if(revived.getNumRevived() < 3){
                System.out.println("Reviving " + revived.getName() + "...");
                revived.revive();
                warriorList.getFallenWarriors().poll();
                warriorList.getWarriors().add(revived);
                System.out.println("Successfully revived " + revived.getName() + "!");
                return;
            }
            // Jika warrior sudah di revive sebanyak 3 kali, maka warrior dihapus dari fallen warrior
            else{
                System.out.println(revived.getName() + " cannot be revived anymore.");
                warriorList.getFallenWarriors().poll();
                return;
            }
        }
    }

    public static void main(String[] args) {
        Battlefield battlefield = new Battlefield();
        battlefield.runMenu();
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab7