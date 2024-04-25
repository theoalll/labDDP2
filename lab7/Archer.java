public class Archer extends Warrior {
    private double criticalRate; 
    private double criticalDamage; 

    //Inisiasi
    public Archer(String name, int attack, int defense, int health, double criticalRate, double criticalDamage) {
        super(name, attack, defense, health);
        this.criticalRate = criticalRate;
        this.criticalDamage = criticalDamage;
    }

    //Attack + crit untuk archer
    @Override
    public void attack(Warrior target) {
        if (Math.random() < this.criticalRate) {
            int criticalHitDamage = (int)(this.attack * this.criticalDamage);
            System.out.println(this.name + " lands a CRITICAL HIT!");
            target.takeDamage(criticalHitDamage);
        } else {
            target.takeDamage(this.attack);
        }
    }

    @Override
    public void revive() {
        super.revive();
        this.health = 1000;
    }

    //Display stats
    public void displayStats() {
        System.out.printf("| %-10s | %-15s | %-7s | %-7s | %-7s | %-7s | %-10s | %-10s |\n", this.getClass().getName(), name, attack, defense, health, "", criticalRate, criticalDamage);
    }
}
