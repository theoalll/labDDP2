import java.util.*;

public class WarriorList<T extends Warrior> {
    
    private List<Warrior> warriors = new ArrayList<>(); // TODO: Instansiasi dengan concrete class yang merupakan List
    private Queue<Warrior> fallenWarriors = new LinkedList<>(); // TODO: Instansiasi dengan concrete class yang merupakan Queue

    public void addWarrior(Warrior warrior) {
        warriors.add(warrior);
    }

    public void removeWarrior(Warrior warrior) {
        warriors.remove(warrior);
    }

    public List<Warrior> getWarriors() {
        return warriors;
    }

    public void addFallenWarrior(Warrior warrior) {
        fallenWarriors.add(warrior);
    }

    public Queue<Warrior> getFallenWarriors() {
        return fallenWarriors;
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab7