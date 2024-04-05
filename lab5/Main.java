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

    // Method untuk menampilkan list employee
    public static void printEmployeeList() {
        displayPermanentEmployee();
        displayContractEmployee();
        displayInternEmployee();
    }

    // Method hireEmployee untuk merekrut employee baru (Menambahkan object Employee ke dalam employeeList)
    public static void hireEmployee() {
        // Task #1: melakukan validasi apakah nama sudah terdaftar
        System.out.print("Nama: ");
        String name = sc.nextLine();
        for (Employee elem : employeeList) {
            if (elem.getName().equals(name)) {
                System.out.println("Nama sudah terdaftar!!!\n");
                return;
            }
        }

        // Task #2: menerima input base salary, status, dan lama durasi (jika status contract atau intern)
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

        // Task #3: membuat object employee sesuai dengan status
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

        //Task #4: menambahkan employee ke dalam employeeList dan memberikan output
        System.out.printf("%s dengan ID %d bernama %s berhasil ditambahkan!\n\n", employee.getClass().getSimpleName(), employee.getEmployeeId(), employee.getName());
        employeeList.add(employee);
    }

    // Method askForRaise untuk menaikkan gaji employee
    public static void askForRaise() {
        // Task #1: validasi apakah ada permanent atau contract employee
        boolean isThereAnyPermanentOrContractEmployee = false;
        for (Employee elem : employeeList) {
            if (elem instanceof PermanentEmployee || elem instanceof ContractEmployee) isThereAnyPermanentOrContractEmployee = true;
            if (isThereAnyPermanentOrContractEmployee == true) break;
        }
        if (isThereAnyPermanentOrContractEmployee == false) {
            System.out.println("Tidak Ada Permanent atau Contract Employee yang Terdaftar!!!\n");
            return;
        }

        // Task #2: menampilkan list permanent dan contract employee, meminta input nama/id employee
        displayPermanentEmployee();
        displayContractEmployee();
        System.out.print("Masukan Nama/ID Employee: ");
        String employeeId = sc.nextLine();

        // Task #3: validasi apakah employee ditemukan, jika ya maka lakukan raise
        employee = getEmployeeByNameOrId(employeeId);
        if (employee == null) {
            System.out.printf("Employee dengan Nama/ID %s Tidak Ditemukan!!!\n\n", employeeId);
            return;
        }
        if (employee instanceof PermanentEmployee || employee instanceof ContractEmployee) {
            // Melakukan casting agar bisa menggunakan method askRaise yang sesuai
            if (employee instanceof PermanentEmployee) employee = (PermanentEmployee)employee;
            else employee = (ContractEmployee)employee;
        
        // Task #4: meminta input raise, validasi raise tidak boleh negatif
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

    // Method extendContract untuk memperpanjang masa kontrak employee
    public static void extendContract() {
        // Task #1: validasi apakah ada intern atau contract employee
        boolean isThereAnyInternOrContractEmployee = false;
        for (Employee elem : employeeList) {
            if (elem instanceof InternEmployee || elem instanceof ContractEmployee) isThereAnyInternOrContractEmployee = true;
            if (isThereAnyInternOrContractEmployee == true) break;
        }
        if (isThereAnyInternOrContractEmployee == false) {
            System.out.println("Tidak Ada Contract atau Intern Employee yang Terdaftar!!!\n");
            return;
        }

        // Task #2: menampilkan list intern dan contract employee, meminta input nama/id employee
        displayContractEmployee();
        displayInternEmployee();

        System.out.print("Masukan Nama/ID Employee: ");
        String employeeId = sc.nextLine();

        // Task #3: validasi apakah employee ditemukan, jika ya maka lakukan extend
        employee = getEmployeeByNameOrId(employeeId);
        if (employee == null) {
            System.out.printf("Employee dengan Nama/ID %s Tidak Ditemukan!!!\n\n", employeeId);
            return;
        }
        if (employee instanceof InternEmployee || employee instanceof ContractEmployee) {
            // Melakukan casting agar bisa menggunakan method extendContract yang sesuai
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

    /* Kumpulan Helper Method */ 
    // Method getEmployeeByNameOrId untuk mendapatkan employee berdasarkan nama atau id
    public static Employee getEmployeeByNameOrId(String nameOrId) {
        // Return employee if exists, otherwise null
        for (Employee employee : employeeList) {
            if (employee.name.equals(nameOrId) || Integer.toString(employee.employeeId).equals(nameOrId)) {
                return employee;
            }
        }
        return null;
    }

    // Method display untuk menampilkan employee berdasarkan status
    public static void displayPermanentEmployee() {
        // Task #1: validasi apakah ada permanent employee
        boolean isThereAnyPermanentEmployee = false;
        for (Employee elem : employeeList) {
            if (elem instanceof PermanentEmployee) isThereAnyPermanentEmployee = true;
            if (isThereAnyPermanentEmployee == true) break;
        }
        if (isThereAnyPermanentEmployee == false) {
            return;
        }

        // Task #2: menampilkan list permanent employee
        System.out.println("===== Pegawai Tetap =====");
        ArrayList<PermanentEmployee> permanentEmployees = getPermanentEmployee();
        for (PermanentEmployee employee : permanentEmployees) {
            System.out.println(employee);
        }
        System.out.println("");
    }

    public static void displayContractEmployee() {
        // Task #1: validasi apakah ada contract employee
        boolean isThereAnyContractEmployee = false;
        for (Employee elem : employeeList) {
            if (elem instanceof ContractEmployee) isThereAnyContractEmployee = true;
            if (isThereAnyContractEmployee == true) break;
        }
        if (isThereAnyContractEmployee == false) {
            return;
        }

        // Task #2: menampilkan list contract employee
        System.out.println("===== Pegawai Kontrak =====");
        ArrayList<ContractEmployee> contractEmployees = getContractEmployee();
        for (ContractEmployee employee : contractEmployees) {
            System.out.println(employee);
        }
        System.out.println("");
    }

    public static void displayInternEmployee() {
        // Task #1: validasi apakah ada intern employee
        boolean isThereAnyInternEmployee = false;
        for (Employee elem : employeeList) {
            if (elem instanceof InternEmployee) isThereAnyInternEmployee = true;
            if (isThereAnyInternEmployee == true) break;
        }
        if (isThereAnyInternEmployee == false) {
            return;
        }

        // Task #2: menampilkan list intern employee
        System.out.println("===== Pegawai Intern =====");
        ArrayList<InternEmployee> internEmployees = getInternEmployee();
        for (InternEmployee employee : internEmployees) {
            System.out.println(employee);
        }
        System.out.println("");
    }

    // Method get employee berdasarkan status untuk mengembalikan list employee berdasarkan status
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

// DDP_D_2306165660_TheoAnandaLemuel_Lab5