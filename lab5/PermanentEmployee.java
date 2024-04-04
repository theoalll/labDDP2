public class PermanentEmployee extends Employee implements RaiseSalary { //TODO: impelementasikan sesuai UML diagram
    public double totalRaise = 0;
    // TODO: Lengkapi constructor berikut
    PermanentEmployee(String name, double salary) {
        super(name, salary);
    }

    // TODO: Lengkapi method ini
    @Override
    public double calculateSalary() {
        return getSalary() + this.totalRaise;
    }

    // TODO: Lengkapi method ini
    @Override
    public void askRaise(double raise) {
        setSalary(getSalary() + raise);
        this.totalRaise += raise;
    }

    // TODO: Lengkapi method ini
    @Override
    public String toString() {
        return String.format("[%d], %s | Salary : %f | Kenaikan : %f\n", getEmployeeId(), getName(), getSalary(), getTotalRaise());
    }

    public double getTotalRaise() {
        return this.totalRaise;
    }

    public void extendContract(int duration) {}
}