public class PermanentEmployee extends Employee implements RaiseSalary { //TODO: impelementasikan sesuai UML diagram
    public double raise = 0;
    // Constructor untuk PermanentEmployee yang akan menerima nama dan gaji
    PermanentEmployee(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double calculateSalary() {
        return getSalary();
    }

    // Method askRaise akan menambahkan raise ke salary
    @Override
    public void askRaise(double raise) {
        this.raise += raise;
        setSalary(getSalary()+getRaise());
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | Salary : %.0f | Kenaikan : %.0f", getEmployeeId(), getName(), calculateSalary(), getRaise());
    }

    // Setter dan Getter
    public double getRaise() {
        return this.raise;
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab5