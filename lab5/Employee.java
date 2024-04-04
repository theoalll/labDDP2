abstract class Employee { //TODO: impelementasikan sesuai UML diagram
    public int employeeId;
    public static int employeeCnt = 0;
    public String name;
    public double salary;

    public Employee(String name, double salary){
        // TODO: Lengkapi constructor berikut
        this.name = name;
        this.salary = salary;
        this.employeeId = employeeCnt;
        employeeCnt++;
    }

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

    abstract public double calculateSalary();
    abstract public String toString();
    abstract public void askRaise(double raise);
    abstract public void extendContract(int duration);
}
