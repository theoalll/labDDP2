import java.io.*;
import java.util.*;

public class SofitaGrader {
    static Scanner sc = new Scanner(System.in);
    static File direktoriUtama = new File(".");

    public static void main(String[] args) throws IOException {
        boolean continueInput = true;
        do {
            try {
                System.out.println("Welcome to SOFITA GRADER!");
                while (true) {
                    printWelcomingMsg();
                    System.out.print("Input: ");
                    int actionCode = Integer.parseInt(sc.nextLine());
                    switch (actionCode) {
                        case 1:
                            buatQuiz();
                            continueInput = false;
                            break;
                        case 2:
                            jawabQuiz();
                            continueInput = false;
                            break;
                        case 3:
                            nilaiQuiz();
                            continueInput = false;
                            break;
                        case 10:
                            System.out.println("Terima kasih sudah memakai SOFITA GRADER!");
                            sc.close();
                            return;
                        default:
                            System.out.println("Incorrect input: an integer (1,2,3, or 10) is required \n");
                    }
                }
            } // TD: Implement the logic for handling exceptions
            catch (InputMismatchException | NumberFormatException ime) {
                System.out.println("Incorrect input: an integer is required \n");
            }
        } while (continueInput);
    }
    
    public static void printWelcomingMsg() {
        System.out.println("=".repeat(64));
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("[1] Buat Quiz baru");
        System.out.println("[2] Input Jawaban Quiz");
        System.out.println("[3] Nilai Jawaban Quiz");
        System.out.println("[10] Exit");
        System.out.println("=".repeat(64));
    }

    public static void buatQuiz() throws FileNotFoundException {
        System.out.println("\n---BUAT QUIZ---");
        // TD: Implement the logic for creating a new quiz
        File quizFolder;
        try {
            quizFolder = makeFile();
        } 
        catch (InvalidQuizException iqe) {
            return;
        }
        makeKJ(quizFolder);
    }

    public static void jawabQuiz() throws IOException {
        System.out.println("\n---JAWAB QUIZ---");
        // TD: Implement the logic for inputting quiz answers
        File quizFolder = null;
        printCurrentDirectory();

        int folderCount = 0;
        File contents[] = direktoriUtama.listFiles();
        for (File file : contents) {
            if (!file.getName().endsWith(".java")){
                folderCount++;
            }
        }
        if (folderCount == 0) return;

        boolean continueInput = true;
        do{
            try {
                quizFolder = aksesFolder();
                continueInput = false;
            } 
            catch (InvalidQuizException iqe) {
                System.out.println("Input tidak valid. Masukkan nama folder yang valid.");
            }
        } while (continueInput);
        makeJawaban(quizFolder);
    }

    public static void nilaiQuiz() throws IOException{
        System.out.print("\n---NILAI QUIZ---\n");
        printCurrentDirectory();
        
        int folderCount = 0;
        File contents[] = direktoriUtama.listFiles();
        for (File file : contents) {
            if (!file.getName().endsWith(".java")){
                folderCount++;
            }
        }
        if (folderCount == 0) return;

        File pilihFolder = null;
        boolean continueInput = true;
        do{
            try {
                pilihFolder = aksesFolder();
                continueInput = false;
            } 
            catch (InvalidQuizException iqe) {
                System.out.println("Input tidak valid. Masukkan nama folder yang valid.");
            }
        } while (continueInput);

        File rekapSebelumnya = findFile(pilihFolder, String.format("Nilai Rekap %s.txt", pilihFolder.getName()));
        if (rekapSebelumnya != null){
            rekapSebelumnya.delete();
            System.out.println("-------------------------------------");
            System.out.println("| ! Nilai Rekap akan di-overwrite ! |");
            System.out.println("-------------------------------------");
        }
    
        File[] files = pilihFolder.listFiles();
        if (files.length == 1) {
            System.out.println("\nBelum ada yang input jawaban");
            return;
        }
    
        File kjQuiz = findFile(pilihFolder, String.format("KJ %s.txt", pilihFolder.getName()));
    
        // BufferedWriter writer = null;
        PrintWriter out = new PrintWriter(pilihFolder.getAbsolutePath()+"\\Nilai Rekap "+pilihFolder.getName()+".txt"); 
        System.out.println("Isi Rekap Nilai "+pilihFolder.getName()+":");
        int totalQuestion = hitungSoal(kjQuiz);
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().startsWith("KJ ") || files[i].getName().startsWith("Nilai Rekap "));
            else{
                File currentFile = files[i];
                int matchedLine = countMatchingLines(kjQuiz, currentFile);
                System.out.printf("%s: %.2f%%\n", currentFile.getName().replace(".txt", ""), (matchedLine*100.0/totalQuestion));
                out.printf("%s: %.2f%%\n", currentFile.getName().replace(".txt", ""), (matchedLine*100.0/totalQuestion));
            }
        }
        System.out.println();
        out.close();
    }
    
///////////////////////////////////////////////////////////////////////////////

    public static File makeFile() throws InvalidQuizException{
        System.out.print("Masukkan nama folder baru: ");
        String inputNama = sc.nextLine();
        File contents[] = direktoriUtama.listFiles();

        for (File file : contents) {
            if (file.getName().equals(inputNama)){
                System.out.println("Nama sudah terambil!");
                throw new InvalidQuizException("Nama sudah terambil!");
                // return file;
            }
        }

        File folderBaru = new File(inputNama);
        folderBaru.mkdir();
        System.out.printf("Berhasil buat folder dengan nama %s\n\n", inputNama);
        return folderBaru;
    }

    public static void makeKJ(File folderQuiz) throws FileNotFoundException {
        // TD: Implement the logic for creating quiz answers
        // HINT: You might want to utilize File.createNewFile() and BufferedWriter to write the answers to a file
        boolean continueInput = true;
        int totalQuestion = 0;
        System.out.println("Silahkan input KJ untuk "+folderQuiz.getName());
        do {
            System.out.print("Jumlah soal: ");
            try {
                totalQuestion = Integer.parseInt(sc.nextLine());
                continueInput = false;
            } 
            catch (NumberFormatException nfe) {
                System.out.println("Invalid Input! Integer input required!");
            }
        } while (continueInput);
        PrintWriter out = new PrintWriter(direktoriUtama.getAbsolutePath()+"\\"+folderQuiz+"\\KJ "+folderQuiz+".txt"); //nanti tambahin throws
        for (int i = 1; i <= totalQuestion; i++) {
            continueInput = true;
            do {
                String answer;
                System.out.print(i+". ");
                answer = sc.nextLine().toUpperCase();
                if (("ABCD".contains(answer) && answer.length() == 1)) {
                    continueInput = false;
                    out.println(i+". "+answer);
                }
                else {
                    System.out.println("Input tidak valid. Masukkan A, B, C, atau D.");
                }
            } while (continueInput);
        }
        out.close();
        System.out.println("Berhasil buat file KJ "+folderQuiz.getName()+".txt\n");
    }

    public static void makeJawaban(File folderQuiz) throws IOException  {
        int totalQuestion;
        boolean continueInput;
        System.out.print("\nMasukkan nama murid: ");
        String studentName = sc.nextLine();
        System.out.println("Masukkan jawaban: ");
        // TD: Implement the logic for creating a new answer file
        // File kjFile = new File(folderQuiz.getAbsolutePath()+"\\KJ "+folderQuiz.getName()+".txt");
        File kjFile = findFile(folderQuiz, String.format("KJ %s.txt", folderQuiz.getName()));
        File studentFile = new File(folderQuiz.getAbsolutePath()+"\\"+studentName+".txt");
        totalQuestion = hitungSoal(kjFile); // nanti tambah throws
        
        if(studentFile.exists()){
            System.out.println("-------------------------------------");
            System.out.println("|  !  Jawaban akan di-overwrite  !  |");
            System.out.println("-------------------------------------");
        }
        PrintWriter out = new PrintWriter(studentFile);
        for (int i = 1; i <= totalQuestion; i++) {
            continueInput = true;
            do {
                String answer;
                System.out.print(i+". ");
                answer = sc.nextLine().toUpperCase();
                if (("ABCD".contains(answer) && answer.length()== 1)) {
                    continueInput = false;
                    out.println(i+". "+answer);
                }
                else {
                    System.out.println("Input tidak valid. Masukkan A, B, C, atau D.");
                }
            } while (continueInput);
        }
        System.out.println("Berhasil buat file "+studentName+".txt\n");
        out.close();
    }

    public static File aksesFolder() throws InvalidQuizException {
        // TD: Implement the logic for accessing a folder
        File fileAccessed = null;
        System.out.print("Pilih nama folder untuk diakses: ");
        String folderName = sc.nextLine();
        File contents[] = direktoriUtama.listFiles();
        for (File file : contents) {
            if (file.getName().equals(folderName)){
                fileAccessed = file;
                return file;
            }
        }
        if (fileAccessed == null) {
            throw new InvalidQuizException("File tidak ditemukan");
        }
        return null;
    }
    
    public static File findFile(File selectedFolder, String fileName){
        // TD: Implement the logic for finding a file in the given folder
        File contents[] = selectedFolder.listFiles();
        for (File file : contents) {
            if (file.getName().equals(fileName)){
                return file;
            }
        }
        return null;
    }
    
    public static int countMatchingLines(File file, File kjFile) throws IOException {
        // TD: Implement the logic for counting matching lines between two files
        Scanner fileReader = new Scanner(file);
        Scanner kjFileReader = new Scanner(kjFile);
        int matchingLines = 0;
        while (fileReader.hasNextLine()) {
            if(fileReader.nextLine().equals(kjFileReader.nextLine()))
                matchingLines++;
        }
        fileReader.close();
        kjFileReader.close();
        return matchingLines;
    }

    /**
     * Prints the names of all files in the given folder that do not have a ".java" extension.
     *
     */
    
    public static void printCurrentDirectory() {
        int folderCount = 0;
        File contents[] = direktoriUtama.listFiles();
        for (File file : contents) {
            if (!file.getName().endsWith(".java")){
                folderCount++;
            }
        }
        System.out.println("Berikut adalah daftar folder yang ada:");
        System.out.println("-----------------------------");
        if (folderCount == 0) 
            System.out.println("Belum ada folder yang dibuat!");
        else
            printFiles(direktoriUtama);
        System.out.println("-----------------------------");
    }

    /**
     * Prints the names of all files in the given folder that have a ".java" extension.
     *
     * @param folderName the folder to search for files
     */
    public static void printFiles(File folderName) {
        File contents[] = folderName.listFiles();
        for (File file : contents) {
            if (!file.getName().endsWith(".java")){
                System.out.printf("> %s\n",file.getName());
            }
        }
    }

    /**
     * Calculates the number of questions in a given file.
     * 
     * @param file the file containing the questions
     * @return the number of questions in the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public static int hitungSoal(File file) throws IOException {
        Scanner reader = new Scanner(file);
        int soalCount = 0;
        while (reader.hasNextLine()) {
            reader.nextLine();
            soalCount++;
        }
        reader.close();
        return soalCount;
    }
}
