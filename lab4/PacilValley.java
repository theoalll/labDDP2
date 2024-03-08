import java.util.ArrayList;
import java.util.Scanner;

public class PacilValley {
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Employee> employees = new ArrayList<>();

    private static void printSeparator() {
        System.out.println("=".repeat(64));
    }

    public static void employeeList() {
        int totalEmployee = employees.size();

        if (totalEmployee == 0) {
            System.out.println("PacilValley belum memiliki karyawan :(\n");
            return;
        }

        printSeparator();
        System.out.println("PacilValley memiliki total " + totalEmployee + " karyawan:");
        // TODO: Cetak semua employee

        printSeparator();
    }

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
                // TODO: Meminta input dan instansiasi employee

                break;
            } else if (role.equalsIgnoreCase("Salesman")) {
                // TODO: Meminta input dan instansiasi employee

                break;
            } else if (role.equalsIgnoreCase("Accountant")) {
                // TODO: Meminta input dan instansiasi employee

                break;
            } else {
                System.out.println("\nRole employee tidak valid, silahkan input kembali dengan nilai yang benar!\n");
            }
        }
        // TODO: Menambahkan employee

    }

    public static void logEmployeeSalary() {
        if (employees.isEmpty()) {
            System.out.println("PacilValley belum memiliki karyawan :(\n");
            return;
        }
        // TODO: Meminta ID dan validasi ID
        // TODO: Meminta input data dan hitung gaji berdasarkan tipe employee

    }

    private static void printMenu() {
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("[1] Employee List");
        System.out.println("[2] Hire Employee");
        System.out.println("[3] Log Employee Salary");
        System.out.println("[4] Exit");
        System.out.println("=".repeat(64));
    }

    public static void main(String[] args) {
        System.out.println("Selamat datang di PacilValley!");
        while (true) {
            printMenu();
            System.out.print("Input: ");
            int pilihan = Integer.parseInt(in.nextLine());

            if (pilihan == 1) {
                employeeList();
            } else if (pilihan == 2) {
                hireEmployee();
            } else if (pilihan == 3) {
                logEmployeeSalary();
            } else {
                System.out.println("Terima kasih telah menggunakan layanan PacilValley ~ !");
                break;
            }
        }
    }
}