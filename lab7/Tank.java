public class Tank extends Warrior {
    private int shield;

    //Inisiasi
    public Tank(String name, int attack, int defense, int health, int shield) {
        super(name, attack, defense, health);
        this.shield = shield;
    }

    //override takeDamage Warrior
    @Override
    public void takeDamage(int damage) {
        int baseShield = this.shield;
        int effectiveDefense = this.defense + this.shield;
        int reducedDamage = damage - effectiveDefense;
        if (reducedDamage < 0) {
            reducedDamage = 0;
        }
        this.health -= reducedDamage;
        if (this.health < 0){
            this.health = 0;
        }
        this.shield = this.shield - (damage - this.defense);
        if (this.shield<0){
            this.shield = 0;
        }
        if (baseShield > 0){
            System.out.println(this.name + " uses shield! Takes " + reducedDamage + " damage, remaining health: " + this.health);
        }
        else if (baseShield <=0){
            System.out.println(this.name + " takes " + reducedDamage + " damage, remaining health: " + this.health);
        }
    }

    @Override
    public void revive() {
        super.revive();
        this.health = 1500;
    }

    //Display stats
    public void displayStats() {
        System.out.printf("| %-10s | %-15s | %-7s | %-7s | %-7s | %-7s | %-10s | %-10s |\n", this.getClass().getName(), name, attack, defense, health, shield, "", "");
    }
}
