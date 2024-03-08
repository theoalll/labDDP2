import java.util.ArrayList;
import java.util.Scanner;

public class PacilValley {
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Employee> employees = new ArrayList<>();

    private static void printSeparator() {
        System.out.println("=".repeat(64));
    }

/*
 * Fungsi employeeList() akan menampilkan daftar karyawan yang dimiliki oleh PacilValley. 
 * Jika tidak ada karyawan, maka akan menampilkan pesan "PacilValley belum memiliki karyawan :(". 
 * Jika ada karyawan, maka akan menampilkan jumlah karyawan yang dimiliki oleh PacilValley, 
 * diikuti dengan daftar karyawan yang dimiliki oleh PacilValley.
 */
    public static void employeeList() {
        int totalEmployee = employees.size();

        if (totalEmployee == 0) {
            System.out.println("PacilValley belum memiliki karyawan :(\n");
            return;
        }

        printSeparator();
        System.out.println("PacilValley memiliki total " + totalEmployee + " karyawan:");
        for (Object elem: employees){
            System.out.println(elem);
        }

        printSeparator();
    }

/*
 * Fungsi hireEmployee() akan meminta input nama, pengalaman kerja, dan base salary dari karyawan yang akan dihire.
 * Setelah itu, fungsi ini akan meminta input role dari karyawan yang akan dihire.
 * Jika role yang dimasukkan adalah "Engineer", maka fungsi ini akan meminta input project fee dari karyawan yang akan dihire.
 * Jika role yang dimasukkan adalah "Salesman", maka fungsi ini akan meminta input commission fee dari karyawan yang akan dihire.
 * Jika role yang dimasukkan adalah "Accountant", maka fungsi ini akan meminta input hourly rate dari karyawan yang akan dihire.
 * Setelah itu, fungsi ini akan menambahkan karyawan yang dihire ke dalam list karyawan yang dimiliki oleh PacilValley.
 * Fungsi ini juga akan menampilkan pesan "[Role] dengan ID [ID] bernama [nama] berhasil dihire!"
 */
    public static void hireEmployee() {
        Employee newEmployee;

        System.out.print("Nama: ");
        String nama = in.nextLine();

        System.out.print("Pengalaman Kerja (tahun): ");
        int pengalamanKerja = Integer.parseInt(in.nextLine());

        System.out.print("Base Salary (IDR): ");
        double baseSalary = Integer.parseInt(in.nextLine());

        String role;
        while (true) {
            System.out.print("Role Employee: ");
            role = in.nextLine();

            if (role.equalsIgnoreCase("Engineer")) {
                System.out.print("Project Fee (IDR): ");
                int projectFee = in.nextInt();
                in.nextLine();
                newEmployee = new Engineer(employees.size()+1, nama, pengalamanKerja, baseSalary, projectFee);
                employees.add(newEmployee);
                System.out.printf("\nEngineer dengan ID %d bernama %s berhasil dihire!\n\n", employees.size(), nama);
                newEmployee.setLevel(pengalamanKerja);
                break;
            } else if (role.equalsIgnoreCase("Salesman")) {
                System.out.print("Commission Fee (%): ");
                int commissionFee = in.nextInt();
                in.nextLine();
                newEmployee = new Salesman(employees.size()+1, nama, pengalamanKerja, baseSalary, commissionFee);
                employees.add(newEmployee);
                System.out.printf("\nSalesman dengan ID %d bernama %s berhasil dihire!\n\n", employees.size(), nama);
                newEmployee.setLevel(pengalamanKerja);
                break;
            } else if (role.equalsIgnoreCase("Accountant")) {
                System.out.print("Hourly Rate (IDR): ");
                int hourlyRate = in.nextInt();
                in.nextLine();
                newEmployee = new Accountant(employees.size()+1, nama, pengalamanKerja, baseSalary, hourlyRate);
                employees.add(newEmployee);
                System.out.printf("\nAccountant dengan ID %d bernama %s berhasil dihire!\n\n", employees.size(), nama);
                newEmployee.setLevel(pengalamanKerja);
                break;
            } else {
                System.out.println("\nRole employee tidak valid, silahkan input kembali dengan nilai yang benar!\n");
            }
        }
    }

/*
 * Fungsi logEmployeeSalary() akan meminta input employee ID dari user.
 * Jika employee ID yang dimasukkan tidak valid, maka fungsi ini akan menampilkan pesan "Employee dengan ID [ID] tidak ditemukan! Silahkan masukkan ID yang sesuai.".
 * Jika employee ID yang dimasukkan valid, maka fungsi ini akan menampilkan pesan "Employee bernama [nama] dengan role [role] berhasil dipilih!".
 * Setelah itu, fungsi ini akan meminta input properti terkait dari role karyawan yang dipilih.
 * Jika role dari karyawan yang dipilih adalah "Engineer", maka fungsi ini akan memanggil method setTotalProject() dari karyawan yang dipilih.
 * Jika role dari karyawan yang dipilih adalah "Accountant", maka fungsi ini akan memanggil method setTotalHoursWorked() dari karyawan yang dipilih.
 * Jika role dari karyawan yang dipilih adalah "Salesman", maka fungsi ini akan memanggil method setTotalSales() dari karyawan yang dipilih.
 * Setelah itu, fungsi ini akan menampilkan pesan "Gaji [nama] bulan ini adalah [gaji] IDR!".
 */
    public static void logEmployeeSalary() {
        if (employees.isEmpty()) {
            System.out.println("PacilValley belum memiliki karyawan :(\n");
        }
        // TODO: Meminta ID dan validasi ID
        else{
            String valid = "f";
            while (valid.equals("f")){
                System.out.print("Masukkan employee ID: ");
                int validateEmployeeID = in.nextInt();
                in.nextLine();
                int counter = 0;

                for (Employee elem: employees) {
                    if (elem.getEmployeeID() == validateEmployeeID){
                        valid = "t";
                        System.out.printf("Employee bernama %s dengan role %s berhasil dipilih!\n", elem.getName(), elem.getRole());
                        if (elem instanceof Engineer) {
                            System.out.print("Jumlah assigned project: ");
                            int assignedProject = in.nextInt();
                            in.nextLine();
                            ((Engineer)elem).setTotalProject(assignedProject);
                        }
                        else if (elem instanceof Accountant){
                            System.out.print("Jumlah jam bekerja: ");
                            int workHour = in.nextInt();
                            in.nextLine();
                            ((Accountant)elem).setTotalHoursWorked(workHour);
                        }
                        else{
                            System.out.print("Jumlah sales (IDR): ");
                            int totalSales = in.nextInt();
                            in.nextLine();
                            ((Salesman)elem).setTotalSales(totalSales);
                        }
                        System.out.printf("Gaji %s bulan ini adalah %.1f IDR!\n\n", elem.getName(), elem.getFinalSalary());
                        break;
                    }
                    else if (counter == employees.size()-1){
                        System.err.printf("\nEmployee dengan ID %d tidak ditemukan! Silahkan masukkan ID yang sesuai.\n\n", validateEmployeeID);
                        break;
                    }
                    else {}
                    counter += 1;
                }
            }
        }   

    }

// Fungsi printMenu() akan menampilkan menu yang dapat dipilih oleh user.
    private static void printMenu() {
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("[1] Employee List");
        System.out.println("[2] Hire Employee");
        System.out.println("[3] Log Employee Salary");
        System.out.println("[4] Exit");
        System.out.println("=".repeat(64));
    }

// Fungsi main()
    public static void main(String[] args) {
        System.out.println("Selamat datang di PacilValley!");
        while (true) {
            printMenu();
            System.out.print("Input: ");
            int pilihan = Integer.parseInt(in.nextLine());

            if (pilihan == 1) {
                employeeList();
            } 
            else if (pilihan == 2) {
                hireEmployee();
            } 
            else if (pilihan == 3) {
                logEmployeeSalary();
            } 
            else {
                System.out.println("Terima kasih telah menggunakan layanan PacilValley ~ !");
                break;
            }
        }
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab4