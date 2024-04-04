abstract class Employee { //TODO: impelementasikan sesuai UML diagram
    int employeeId;
    static int employeeCnt = 0;
    String name;
    double salary;

    protected Employee(String name, double salary){
        // TODO: Lengkapi constructor berikut
    }
    abstract double calculateSalary();
    abstract public String toString();
}
