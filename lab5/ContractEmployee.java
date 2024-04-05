public class ContractEmployee extends Employee implements RaiseSalary, ExtendContractDuration { //TODO: impelementasikan sesuai UML diagram
    public int contractDuration = 0;
    public double salaryMultiplier = 1;
    public double raise = 0.0;

    // Constructor untuk ContractEmployee yang akan menerima nama, gaji, dan durasi kontrak
    ContractEmployee(String name, double salary, int contractDuration) {
        super(name, salary);
        this.contractDuration = contractDuration;
    }

    // Method askRaise akan menambahkan raise ke salary
    @Override
    public void askRaise(double raise) {
        this.raise += raise;
        setSalary(getSalary()+getRaise());
    }

    // Method calculateSalary akan mengembalikan gaji yang sudah dikalikan dengan salaryMultiplier
    @Override
    public double calculateSalary() {
        return getSalary()*getSalaryMultiplier();
    }

    @Override
    public void extendContract(int duration) {
        this.contractDuration += duration;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | Salary : %.0f | Kenaikan : %.0f | Kontrak : %d", getEmployeeId(), getName(), calculateSalary(), getRaise(), getContractDuration());
    }

    // Method getSalaryMultiplier akan mengembalikan salaryMultiplier sesuai dengan durasi kontrak
    public double getSalaryMultiplier() {
        if (this.contractDuration <= 6) this.salaryMultiplier = 1;
        else if (this.contractDuration <= 12) this.salaryMultiplier = 1.5;
        else if (this.contractDuration > 12) this.salaryMultiplier = 2;
        else this.salaryMultiplier = 0;
        return this.salaryMultiplier;
    }

    // Getter dan Setter
    public int getContractDuration() {
        return this.contractDuration;
    }

    public double getRaise() {
        return this.raise;
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab5