public class InternEmployee extends Employee implements ExtendContractDuration { //TODO: impelementasikan sesuai UML diagram
    public int contractDuration = 0;
    public double salaryMultiplier = 1;

    // TODO: Lengkapi constructor berikut
    InternEmployee(String name, double salary, int contractDuration) {
        super(name, salary);
        this.contractDuration = contractDuration;
    }

    // TODO: Lengkapi method ini
    @Override
    public double calculateSalary() {
        return getSalary()*getSalaryMultiplier();
    }

    // TODO: Lengkapi method ini
    @Override
    public void extendContract(int duration) {
        this.contractDuration += duration;
    }

    // TODO: Lengkapi method ini
    @Override
    public String toString() {
        return String.format("[%d] %s | Salary : %.0f | Kontrak : %d\n", getEmployeeId(), getName(), getSalary(), getContractDuration());
    }

    // TODO: Lengkapi method helper ini
    public double getSalaryMultiplier() {
        if (this.contractDuration <= 6) this.salaryMultiplier = 1;
        else if (this.contractDuration <= 12) this.salaryMultiplier = 1.25;
        else if (this.contractDuration > 12) this.salaryMultiplier = 1.5;
        else this.salaryMultiplier = 0;
        return this.salaryMultiplier;
    }

    public int getContractDuration() {
        return this.contractDuration;
    }

    public void askRaise(double raise) {}
}   
