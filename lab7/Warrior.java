public abstract class Warrior implements Comparable<Warrior> {
    //Inisiasi class
    protected String name;
    protected int attack;
    protected int defense;
    protected int health;
    protected int numRevived;

    public Warrior(String name, int attack, int defense, int health) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
    }
    
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public int getNumRevived() {
        return numRevived;
    }
    
    public void attack(Warrior target) {
        System.out.println(this.name + " attacks " + target.name + " for " + this.attack + " damage.");
        target.takeDamage(this.attack);
    }

    public void takeDamage(int damage) {
        // Mengurangi damage dengan defense, jika hasilnya negatif, maka damage yang diterima adalah 0
        int reducedDamage = damage - this.defense;
        if (reducedDamage < 0) {
            reducedDamage = 0;
        }
        // Mengurangi health dengan reducedDamage, jika health kurang dari 0, maka health menjadi 0 (meninggal)
        this.health -= reducedDamage;
        if (this.health < 0) {
            this.health = 0;
        }
        // Menampilkan pesan berapa damage yang diterima dan sisa health
        System.out.println(this.name + " takes " + reducedDamage + " damage, remaining health: " + this.health);
    }

    public abstract void displayStats();

    public boolean isAlive() {
        return this.health > 0;
    }

    public void revive() {
        numRevived++;
        
    }

    @Override
    public int compareTo(Warrior other) {
        // Mengurutkan berdasarkan nama
        return this.getName().compareTo(other.getName());
    }

}

// DDP_D_2306165660_TheoAnandaLemuel_Lab7