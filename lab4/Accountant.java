public class Accountant extends Employee {
    private int totalHoursWorked;
    private double hourlyRate;

// Membuat konstruktor
    public Accountant(int employeeID, String name, int yearsOfWork, double baseSalary, double hourlyRate) {
        super(employeeID, name, yearsOfWork, baseSalary);
        this.hourlyRate = hourlyRate;
    }

// Membuat setter dan getter
    public void setTotalHoursWorked(int totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }
    
    public int getTotalHoursWorked(){
        return totalHoursWorked;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public String getRole() {
        return "Accountant";
    }

// Membuat method CalculateSalary sesuai dengan ketentuan
    @Override
    double CalculateSalary() {
        if (getYearsOfWork() <= 5) 
            return getBaseSalary() + getTotalHoursWorked()*getHourlyRate();
        else if (getYearsOfWork() <= 10)
            return (getBaseSalary() + getTotalHoursWorked()*getHourlyRate())*1.5;
        else if (getYearsOfWork() > 10)
            return (getBaseSalary() + getTotalHoursWorked()*getHourlyRate())*2;
        return -1;
    }

// Membuat method toString
    @Override
    public String toString() {
        System.out.println(super.toString());
        return String.format(
            "Total Jam Kerja: %d\r\n" + //
            "Final Salary: %.1f IDR\r\n" + //
            "", getTotalHoursWorked(), getFinalSalary()
        );
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab4