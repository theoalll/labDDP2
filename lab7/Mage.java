public class Mage extends Warrior {
    //Inisiasi
    public Mage(String name, int attack, int defense, int health) {
        super(name, attack, defense, health);
    }

    //Penetrating Attack untuk Mage
    @Override
    public void attack(Warrior target) {
        int damage = this.attack + target.getDefense();
        System.out.println(this.name + " casts a spell that ignores defense!");
        target.takeDamage(damage);
    }

    @Override
    public void revive() {
        super.revive();
        this.health = 500;
    }

    //Display stats
    public void displayStats() {
        System.out.printf("| %-10s | %-15s | %-7s | %-7s | %-7s | %-7s | %-10s | %-10s |\n", this.getClass().getName(), name, attack, defense, health, "", "", "");
    }
}
