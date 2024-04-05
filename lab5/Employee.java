abstract class Employee { 
    public static int employeeCnt = 0;
    public int employeeId;
    public String name;
    public double salary;

    // Constructor untuk Employee yang akan menerima nama dan gaji
    public Employee(String name, double salary){
        this.name = name;
        this.salary = salary;
        this.employeeId = employeeCnt;
        employeeCnt++;
    }

    // Getter dan Setter
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSalary (double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }

    public int getEmployeeCount() {
        return employeeCnt;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    // Method abstract yang akan diimplementasikan di subclass
    abstract public double calculateSalary();
    abstract public String toString();

    // Tambahan method supaya tidak terjadi error saat dynamic binding di subclass
    public void askRaise(double raise) {}
    public void extendContract(int duration) {}
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab5