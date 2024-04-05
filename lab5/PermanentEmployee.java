public class PermanentEmployee extends Employee implements RaiseSalary { //TODO: impelementasikan sesuai UML diagram
    public double raise = 0;
    // TODO: Lengkapi constructor berikut
    PermanentEmployee(String name, double salary) {
        super(name, salary);
    }

    // TODO: Lengkapi method ini
    @Override
    public double calculateSalary() {
        return (getSalary()+getRaise());
    }

    // TODO: Lengkapi method ini
    @Override
    public void askRaise(double raise) {
        this.raise += raise;
    }

    // TODO: Lengkapi method ini
    @Override
    public String toString() {
        return String.format("[%d] %s | Salary : %.0f | Kenaikan : %.0f\n", getEmployeeId(), getName(), calculateSalary(), getRaise());
    }

    public double getRaise() {
        return this.raise;
    }

    public void extendContract(int duration) {}
}