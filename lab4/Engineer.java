public class Engineer extends Employee {
    private int totalProject;
    private double projectFee;

// Membuat konstruktor
    public Engineer(int employeeID, String name, int yearsOfWork, double baseSalary, double projectFee) {
        super(employeeID, name, yearsOfWork, baseSalary);
        this.projectFee = projectFee;
    }

// Membuat setter dan getter
    public void setTotalProject(int totalProject) {
        this.totalProject = totalProject;
    }

    public int getTotalProject() {
        return totalProject;
    }

    public void setProjectFee(double projectFee) {
        this.projectFee = projectFee;
    }

    public double getProjectFee() {
        return projectFee;
    }

    @Override
    public String getRole() {
        return "Engineer";
    }

// Membuat method CalculateSalary sesuai dengan ketentuan
    @Override
    double CalculateSalary() {
        if (getYearsOfWork() <= 5) 
            return getBaseSalary() + getProjectFee()*getTotalProject();
        else if (getYearsOfWork() <= 10)
            return (getBaseSalary() + getProjectFee()*getTotalProject())*1.5;
        else if (getYearsOfWork() > 10)
            return (getBaseSalary() + getProjectFee()*getTotalProject())*2;
        return -1;
    }

// Membuat method toString
    @Override
    public String toString() {
        System.out.println(super.toString());
        return String.format(
            "Banyak Project: %d\n" + //
            "Final Salary: %.1f IDR\n" + //
            "", getTotalProject(), getFinalSalary()
        );
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab4