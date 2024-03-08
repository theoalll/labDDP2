public class Employee {
    private int employeeID;
    private String name;
    private int yearsOfWork;
    private double baseSalary;
    private double finalSalary;
    private String level;

// Membuat konstruktor
    public Employee() {
    }

    public Employee(int employeeID, String name, int yearsOfWork, double baseSalary) {
        this.employeeID = employeeID;
        this.name = name;
        this.yearsOfWork = yearsOfWork;
        this.baseSalary = baseSalary;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

// Membuat setter dan getter
    public void setYearsOfWork(int yearsOfWork) {
        this.yearsOfWork = yearsOfWork;
    }

    public int getYearsOfWork() {
        return yearsOfWork;
    }

    public void setBaseSalary(double baseSalary){
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setFinalSalary(double finalSalary) {
        this.finalSalary = finalSalary;
    }

    public double getFinalSalary () {
        return CalculateSalary();
    }

    public void setLevel(int yearsOfWork) {
        if (getYearsOfWork() <= 5) 
            this.level = "Junior";
        else if (getYearsOfWork() <= 10)
            this.level = "Senior";
        else
            this.level = "Expert";
    }

    public String getLevel() {
        return level;
    }
    
    public String getRole() {
        return "Employee";
    }

// Membuat method untuk menghitung gaji akhir (return 0 karena belum diimplementasikan di class Employee, akan diimplementasikan di class turunan)
    double CalculateSalary() {
        return 0;
    }

// Membuat method untuk menampilkan informasi employee
    @Override
    public String toString() {
        return String.format(
            "Nama: %s \n" + //
            "Pengalaman Kerja: %d tahun\n" + //
            "Jabatan: %s \n" + //
            "Role: %s", getName(), getYearsOfWork(), getLevel(), getRole()
        );
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab4