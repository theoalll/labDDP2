public class ContractEmployee extends Employee implements RaiseSalary, ExtendContractDuration { //TODO: impelementasikan sesuai UML diagram
    public int contractDuration = 0;
    public double salaryMultiplier = 1;
    public double totalRaise = 0.0;

    // TODO: Lengkapi constructor berikut
    ContractEmployee(String name, double salary, int contractDuration) {
        super(name, salary);
        this.contractDuration = contractDuration;
    }

    // TODO: Lengkapi method ini
    @Override
    public void askRaise(double raise) {
        setSalary(getSalary() + raise);
        this.totalRaise += raise;
    }

    // TODO: Lengkapi method ini
    @Override
    public double calculateSalary() {
        return getSalary() + getSalary()*getSalaryMultiplier() + getTotalRaise();
    }

    // TODO: Lengkapi method ini
    @Override
    public void extendContract(int duration) {
        this.contractDuration += duration;
    }

    // TODO: Lengkapi method ini
    @Override
    public String toString() {
        return String.format("[%d], %s | Salary : %f | Kenaikan : %f | Kontrak : %d\n", getEmployeeId(), getName(), getSalary(), getTotalRaise(), getContractDuration());
    }

    // TODO: Lengkapi method helper ini
    public double getSalaryMultiplier() {
        return this.salaryMultiplier;
    }

    public void setSalaryMultiplier() {
        if (this.contractDuration <= 6) this.salaryMultiplier = 1;
        else if (this.contractDuration <= 12) this.salaryMultiplier = 1.5;
        else if (this.contractDuration > 12) this.salaryMultiplier = 2;
        else this.salaryMultiplier = 0;
    }

    public int getContractDuration() {
        return this.contractDuration;
    }

    public double getTotalRaise() {
        return this.totalRaise;
    }
}
