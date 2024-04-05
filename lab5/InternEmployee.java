public class InternEmployee extends Employee implements ExtendContractDuration { //TODO: impelementasikan sesuai UML diagram
    public int contractDuration = 0;
    public double salaryMultiplier = 1;

    // Constructor untuk InternEmployee yang akan menerima nama, gaji, dan durasi kontrak
    InternEmployee(String name, double salary, int contractDuration) {
        super(name, salary);
        this.contractDuration = contractDuration;
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
        return String.format("[%d] %s | Salary : %.0f | Kontrak : %d Bulan", getEmployeeId(), getName(), calculateSalary(), getContractDuration());
    }

    // Method getSalaryMultiplier akan mengembalikan salaryMultiplier sesuai dengan durasi kontrak
    public double getSalaryMultiplier() {
        if (this.contractDuration <= 6) this.salaryMultiplier = 1;
        else if (this.contractDuration <= 12) this.salaryMultiplier = 1.25;
        else if (this.contractDuration > 12) this.salaryMultiplier = 1.5;
        else this.salaryMultiplier = 0;
        return this.salaryMultiplier;
    }

    // Getter dan Setter
    public int getContractDuration() {
        return this.contractDuration;
    }
}   

// DDP_D_2306165660_TheoAnandaLemuel_Lab5