public class ContractEmployee extends Employee implements RaiseSalary, ExtendContractDuration { //TODO: impelementasikan sesuai UML diagram
    public int contractDuration = 0;
    public double salaryMultiplier = 1;
    public double raise = 0.0;

    // TODO: Lengkapi constructor berikut
    ContractEmployee(String name, double salary, int contractDuration) {
        super(name, salary);
        this.contractDuration = contractDuration;
    }

    // TODO: Lengkapi method ini
    @Override
    public void askRaise(double raise) {
        this.raise += raise;
        setSalary(getSalary() + raise);
    }

    // TODO: Lengkapi method ini
    @Override
    public double calculateSalary() {
        return (getSalary()+getRaise())*getSalaryMultiplier();
    }

    // TODO: Lengkapi method ini
    @Override
    public void extendContract(int duration) {
        this.contractDuration += duration;
    }

    // TODO: Lengkapi method ini
    @Override
    public String toString() {
        return String.format("[%d] %s | Salary : %.0f | Kenaikan : %.0f | Kontrak : %d\n", getEmployeeId(), getName(), getSalary(), getRaise(), getContractDuration());
    }

    // TODO: Lengkapi method helper ini
    public double getSalaryMultiplier() {
        if (this.contractDuration <= 6) this.salaryMultiplier = 1;
        else if (this.contractDuration <= 12) this.salaryMultiplier = 1.5;
        else if (this.contractDuration > 12) this.salaryMultiplier = 2;
        else this.salaryMultiplier = 0;
        return this.salaryMultiplier;
    }

    public int getContractDuration() {
        return this.contractDuration;
    }

    public double getRaise() {
        return this.raise;
    }
}
