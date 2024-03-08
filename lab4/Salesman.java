public class Salesman extends Employee {
    private double totalSales;
    private double commissionFee;

// Membuat konstruktor
    public Salesman(int employeeID, String name, int yearsOfWork, double baseSalary, double commissionFee) {
        super(employeeID, name, yearsOfWork, baseSalary);
        this.commissionFee = commissionFee;
    }

// Membuat setter dan getter
    public void setTotalSales(double totalSales){
        this.totalSales = totalSales;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setCommissionFee (double commissionFee){
        this.commissionFee = commissionFee;
    }

    public double getCommissionFee() {
        return commissionFee;
    }

    @Override
    public String getRole(){
        return "Salesman";
    }

// Membuat method CalculateSalary sesuai dengan ketentuan
    double CalculateSalary() {
        if (getYearsOfWork() <= 5) 
            return getBaseSalary() + getTotalSales()*(getCommissionFee()/100);
        else if (getYearsOfWork() <= 10)
            return (getBaseSalary() + getTotalSales()*(getCommissionFee()/100))*1.5;
        else if (getYearsOfWork() > 10)
            return (getBaseSalary() + getTotalSales()*(getCommissionFee()/100))*2;
        return -1;
    }

// Membuat method toString
    @Override
    public String toString() {
        System.out.println(super.toString());
        return String.format(
            "Banyak Sales: %.1f IDR\r\n" + //
            "Final Salary: %.1f IDR\r\n" + //
            "", getTotalSales(), getFinalSalary()
        );
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab4