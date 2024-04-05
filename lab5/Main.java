import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Employee> employeeList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static Employee employee = null;


    public static void main(String[] args) {
        System.out.println("Selamat Datang di PacilRekrutment");
        while (true) {
            employee = null;
            printWelcomingMsg();
            System.out.print("Input: ");
            int actionCode = sc.nextInt();
            sc.nextLine();
            switch (actionCode) {
                case 1:
                    printEmployeeList();
                    break;
                case 2:
                    hireEmployee();
                    break;
                case 3:
                    askForRaise();
                    break;
                case 4:
                    extendContract();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan layanan PacilRekrutment ~ !");
                    sc.close();
                    return;
                default:
                    unknownActionMsg();
                    break;
            }
        }
    }

    public static void printEmployeeList() {
        // TODO: Implemntasikan untuk print daftar employee
        displayPermanentEmployee();
        System.out.println("");
        displayContractEmployee();
        System.out.println("");
        displayInternEmployee();
        System.out.println("");
    }

    public static void hireEmployee() {
        // TODO: Implementasikan untuk hire employee
        System.out.print("Nama: ");
        String name = sc.nextLine();
        for (Employee elem : employeeList) {
            if (elem.getName().equals(name)) {
                System.out.println("Nama sudah terdaftar!!!\n");
                return;
            }
        }
        System.out.print("Base Salary: ");
        double baseSalary = sc.nextDouble();
        sc.nextLine();
        System.out.print("Status Employee (Permanent/Contract/Intern): ");
        int contractPeriod = 0;
        String status = sc.nextLine().toLowerCase();
        if (status.equals("intern") || status.equals("contract")) {
            System.out.print("Lama Kontrak (Bulan): ");
            contractPeriod = sc.nextInt();
            sc.nextLine();
        }
        switch(status){
            case "permanent": {
                employee = (PermanentEmployee)employee;
                employee = new PermanentEmployee(name, baseSalary);
                break;
            }
            case "contract": {
                employee = (ContractEmployee)employee;
                employee = new ContractEmployee(name, baseSalary, contractPeriod);
                break;
            }
            case "intern": {
                employee = (InternEmployee)employee;
                employee = new InternEmployee(name, baseSalary, contractPeriod);
                break;
            }
        }
        System.out.printf("%s dengan ID %d bernama %s berhasil ditambahkan!\n\n", employee.getClass().getSimpleName(), employee.getEmployeeId(), employee.getName());
        employeeList.add(employee);
    }

    public static void askForRaise() {
        // TODO: Implementasikan untuk pengajuan kenaikan gaji
        boolean isThereAnyPermanentOrContractEmployee = false;
        for (Employee elem : employeeList) {
            if (elem instanceof PermanentEmployee || elem instanceof ContractEmployee) isThereAnyPermanentOrContractEmployee = true;
            if (isThereAnyPermanentOrContractEmployee == true) break;
        }
        if (isThereAnyPermanentOrContractEmployee == false) {
            System.out.println("Tidak Ada Permanent atau Contract Employee yang Terdaftar!!!\n");
            return;
        }

        displayPermanentEmployee();
        System.out.println("");
        displayContractEmployee();
        System.out.println("");

        System.out.print("Masukan Nama/ID Employee: ");
        String employeeId = sc.nextLine();

        employee = getEmployeeByNameOrId(employeeId);
        if (employee == null) {
            System.out.printf("Employee dengan Nama/ID %s Tidak Ditemukan!!!\n", employeeId);
            return;
        }
        if (employee instanceof PermanentEmployee || employee instanceof ContractEmployee) {
            if (employee instanceof PermanentEmployee) employee = (PermanentEmployee)employee;
            else employee = (ContractEmployee)employee;

            System.out.print("Masukan Jumlah Kenaikan: ");
            double raiseValue = sc.nextDouble();
            sc.nextLine();
            if (raiseValue >= 0) {
                employee.askRaise(raiseValue);
                System.out.printf("Employee dengan Nama/ID %s Berhasil Dinaikkan Gajinya Sebesar %.0f\n\n", employeeId, raiseValue);
            }
            else System.out.println("Kenaikan Gaji Tidak Boleh Negatif!!!\n");
        }
        else{
            System.out.println("Intern Employee Tidak Bisa Mendapatkan Raise!!!\n");
        }

    }

    public static void extendContract() {
        // TODO: Implementasikan untuk pengajuan perpanjangan masa kontrak
        boolean isThereAnyInternOrContractEmployee = false;
        for (Employee elem : employeeList) {
            if (elem instanceof InternEmployee || elem instanceof ContractEmployee) isThereAnyInternOrContractEmployee = true;
            if (isThereAnyInternOrContractEmployee == true) break;
        }
        if (isThereAnyInternOrContractEmployee == false) {
            System.out.println("Tidak Ada Contract atau Intern Employee yang Terdaftar!!!\n");
            return;
        }

        displayContractEmployee();
        System.out.println("");
        displayInternEmployee();
        System.out.println("");

        System.out.print("Masukan Nama/ID Employee: ");
        String employeeId = sc.nextLine();

        employee = getEmployeeByNameOrId(employeeId);
        if (employee == null) {
            System.out.printf("Employee dengan Nama/ID %s Tidak Ditemukan!!!\n\n", employeeId);
            return;
        }

        if (employee instanceof InternEmployee || employee instanceof ContractEmployee) {
            if (employee instanceof InternEmployee) employee = (InternEmployee)employee;
            else employee = (ContractEmployee)employee;

            System.out.print("Masukan Lama Extend Kontrak (Bulan): ");
            int duration = sc.nextInt();
            sc.nextLine();
            employee.extendContract(duration);
            System.out.printf("Employee dengan Nama/ID %s Berhasil Diperpanjang Kontraknya Selama %d Bulan\n\n", employeeId, duration);
        }
        else{
            System.out.println("PermanentEmployee Tidak Bisa Extend Kontrak!!!\n");
        }
    }

    // Kumpulan Helper Method

    public static Employee getEmployeeByNameOrId(String nameOrId) {
        // Return employee if exists, otherwise null
        for (Employee employee : employeeList) {
            if (employee.name.equals(nameOrId) || Integer.toString(employee.employeeId).equals(nameOrId)) {
                return employee;
            }
        }
        return null;
    }

    public static void displayPermanentEmployee() {
        boolean isThereAnyPermanentEmployee = false;
        for (Employee elem : employeeList) {
            if (elem instanceof PermanentEmployee) isThereAnyPermanentEmployee = true;
            if (isThereAnyPermanentEmployee == true) break;
        }
        if (isThereAnyPermanentEmployee == false) {
            return;
        }
        System.out.println("===== Pegawai Tetap =====");
        ArrayList<PermanentEmployee> permanentEmployees = getPermanentEmployee();
        for (PermanentEmployee employee : permanentEmployees) {
            System.out.println(employee);
        }
    }

    public static void displayContractEmployee() {
        boolean isThereAnyContractEmployee = false;
        for (Employee elem : employeeList) {
            if (elem instanceof ContractEmployee) isThereAnyContractEmployee = true;
            if (isThereAnyContractEmployee == true) break;
        }
        if (isThereAnyContractEmployee == false) {
            return;
        }
        System.out.println("===== Pegawai Kontrak =====");
        ArrayList<ContractEmployee> contractEmployees = getContractEmployee();
        for (ContractEmployee employee : contractEmployees) {
            System.out.println(employee);
        }
    }

    public static void displayInternEmployee() {
        boolean isThereAnyInternEmployee = false;
        for (Employee elem : employeeList) {
            if (elem instanceof InternEmployee) isThereAnyInternEmployee = true;
            if (isThereAnyInternEmployee == true) break;
        }
        if (isThereAnyInternEmployee == false) {
            return;
        }
        System.out.println("===== Pegawai Intern =====");
        ArrayList<InternEmployee> internEmployees = getInternEmployee();
        for (InternEmployee employee : internEmployees) {
            System.out.println(employee);
        };
    }

    // Penggunaan Generics dapat digunakan (akan dipelajari di week mendatang)
    // untuk mengurangi pengulangan 3 method ini
    public static ArrayList<InternEmployee> getInternEmployee() {
        ArrayList<InternEmployee> internEmployees = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee instanceof InternEmployee) {
                internEmployees.add((InternEmployee) employee);
            }
        }
        return internEmployees;
    }

    public static ArrayList<ContractEmployee> getContractEmployee() {
        ArrayList<ContractEmployee> contractEmployees = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee instanceof ContractEmployee) {
                contractEmployees.add((ContractEmployee) employee);
            }
        }
        return contractEmployees;
    }

    public static ArrayList<PermanentEmployee> getPermanentEmployee() {
        ArrayList<PermanentEmployee> permanentEmployees = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee instanceof PermanentEmployee) {
                permanentEmployees.add((PermanentEmployee) employee);
            }
        }
        return permanentEmployees;
    }

    // Printing Function
    public static void printWelcomingMsg() {
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("[1] Employee List");
        System.out.println("[2] Hire Employee");
        System.out.println("[3] Raise Salary");
        System.out.println("[4] Extend Contract");
        System.out.println("[5] Exit");
        System.out.println("=".repeat(64));
    }

    public static void unknownActionMsg() {
        System.out.println("Mohon masukkan opsi yang valid!\n");
    }
}