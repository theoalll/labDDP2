import java.io.*;
import java.util.*;

public class SofitaGrader {
    static Scanner sc = new Scanner(System.in);
    static File direktoriUtama = new File(".");

    /**
     * Main method for running the Sofita Grader program.
     * Handles user input and executes corresponding actions.
     *
     * @param args command-line arguments
     * @throws IOException if an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        boolean continueInput = true;
        do {
            try {
                System.out.println("Welcome to SOFITA GRADER!");
                while (true) {
                    // Task #1: Print the welcoming message and prompt the user to select an action
                    printWelcomingMsg();
                    System.out.print("Input: ");
                    int actionCode = Integer.parseInt(sc.nextLine());
                    // Task #2: Execute the selected action
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
                        // Task #3: Handle incorrect input
                        default:
                            System.out.println("Incorrect input: an integer (1,2,3, or 10) is required \n");
                    }
                }
            } 
            // Task #4: Handle the InputMismatchException and NumberFormatException
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

    /**
     * Creates a new quiz by creating a new folder.
     * Prompts user to enter a new folder name.
     *
     * @throws FileNotFoundException if the specified file is not found
     */
    public static void buatQuiz() throws FileNotFoundException {
        System.out.println("\n---BUAT QUIZ---");
        // Task #1: Create a new folder for the quiz, and handle the InvalidQuizException
        File quizFolder;
        try {
            quizFolder = makeFile();
        } 
        catch (InvalidQuizException iqe) {
            return;
        }
        // Task #2: Create a key (KJ) file for the quiz
        makeKJ(quizFolder);
    }

    /**
     * Allows the user to input quiz answers by selecting a folder.
     * Prompts the user to select a folder and then inputs answers.
     *
     * @throws IOException if an I/O error occurs
     */
    public static void jawabQuiz() throws IOException {
        System.out.println("\n---JAWAB QUIZ---");
        File quizFolder = null;
        // Task #1: Print the names of all folders in the current directory
        printCurrentDirectory();

        // Task #2: count the number of folders in the current directory, and return if there are none
        int folderCount = 0;
        File contents[] = direktoriUtama.listFiles();
        for (File file : contents) {
            if (!file.getName().endsWith(".java")){
                folderCount++;
            }
        }
        if (folderCount == 0) return;

        // Task #3: Access the folder selected by the user
        boolean continueInput = true;
        do{
            try {
                quizFolder = aksesFolder();
                continueInput = false;
            } 
            // Task #4: Handle the InvalidQuizException if the folder is not found
            catch (InvalidQuizException iqe) {
                System.out.println("Input tidak valid. Masukkan nama folder yang valid.");
            }
        } while (continueInput);
        // Task #5: Create a file for the student's answers
        makeJawaban(quizFolder);
    }

    /**
     * Calculates and displays the grades for each student in the selected folder.
     * Prompts the user to select a folder and then calculates the grades based on answers.
     *
     * @throws IOException if an I/O error occurs
     */
    public static void nilaiQuiz() throws IOException{
        System.out.print("\n---NILAI QUIZ---\n");
        // Task #1: Print the names of all folders in the current directory
        printCurrentDirectory();
        
        // Task #2: count the number of folders in the current directory, and return if there are none
        int folderCount = 0;
        File contents[] = direktoriUtama.listFiles();
        for (File file : contents) {
            if (!file.getName().endsWith(".java")){
                folderCount++;
            }
        }
        if (folderCount == 0) return;

        // Task #3: Access the folder selected by the user
        File pilihFolder = null;
        boolean continueInput = true;
        do{
            try {
                pilihFolder = aksesFolder();
                continueInput = false;
            } 
            // Task #4: Handle the InvalidQuizException if the folder is not found
            catch (InvalidQuizException iqe) {
                System.out.println("Input tidak valid. Masukkan nama folder yang valid.");
            }
        } while (continueInput);

        // Task #5: Validate that the file exists, warn the user if it does
        File rekapSebelumnya = findFile(pilihFolder, String.format("Nilai Rekap %s.txt", pilihFolder.getName()));
        if (rekapSebelumnya != null){
            rekapSebelumnya.delete();
            System.out.println("-------------------------------------");
            System.out.println("| ! Nilai Rekap akan di-overwrite ! |");
            System.out.println("-------------------------------------");
        }
        
        // Task #6: Validate that there are files in the folder, and return if there are none
        File[] files = pilihFolder.listFiles();
        if (files.length == 1) {
            System.out.println("\nBelum ada yang input jawaban");
            return;
        }
        
        // Task #7: Calculate the grades for each student and write them to a file
        File kjQuiz = findFile(pilihFolder, String.format("KJ %s.txt", pilihFolder.getName()));
    
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
        // Task #8: Close the PrintWriter
        System.out.println();
        out.close();
    }
    
///////////////////////////////////////////////////////////////////////////////
    /**
     * Creates a new folder for a quiz based on user input.
     * Prompts the user to enter a new folder name and creates the folder.
     *
     * @return the newly created folder
     * @throws InvalidQuizException if the folder name is already taken
     */
    public static File makeFile() throws InvalidQuizException{
        // Task #1: Receive the folder name from the user
        System.out.print("Masukkan nama folder baru: ");
        String inputNama = sc.nextLine();

        // Task #2: Check if the folder name is already taken, and throw an exception if it is
        File contents[] = direktoriUtama.listFiles();
        for (File file : contents) {
            if (file.getName().equals(inputNama)){
                System.out.println("Nama sudah terambil!");
                throw new InvalidQuizException("Nama sudah terambil!");
            }
        }

        // Task #3: Create the folder and print a success message
        File folderBaru = new File(inputNama);
        folderBaru.mkdir();
        System.out.printf("Berhasil buat folder dengan nama %s\n\n", inputNama);
        return folderBaru;
    }

    /**
     * Creates a key (KJ) file for a quiz.
     * Prompts the user to enter the number of questions and the correct answers.
     *
     * @param folderQuiz the folder for the quiz
     * @throws FileNotFoundException if the specified file is not found
     */
    public static void makeKJ(File folderQuiz) throws FileNotFoundException {
        boolean continueInput = true;
        int totalQuestion = 0;
        // Task #1: Receive the number of questions and the correct answers
        System.out.println("Silahkan input KJ untuk "+folderQuiz.getName());
        do {
            System.out.print("Jumlah soal: ");
            try {
                totalQuestion = Integer.parseInt(sc.nextLine());
                continueInput = false;
            } 
            // Task #2: Handle the NumberFormatException
            catch (NumberFormatException nfe) {
                System.out.println("Invalid Input! Integer input required!");
            }
        } while (continueInput);

        // Task #3: Write the correct answers to the file
        PrintWriter out = new PrintWriter(direktoriUtama.getAbsolutePath()+"\\"+folderQuiz+"\\KJ "+folderQuiz+".txt"); //nanti tambahin throws
        for (int i = 1; i <= totalQuestion; i++) {
            continueInput = true;
            do {
                String answer;
                System.out.print(i+". ");
                answer = sc.nextLine().toUpperCase();
                // Task #4: Validate the input (A, B, C, or D only and one character only)
                if (("ABCD".contains(answer) && answer.length() == 1)) {
                    continueInput = false;
                    out.println(i+". "+answer);
                }
                else {
                    System.out.println("Input tidak valid. Masukkan A, B, C, atau D.");
                }
            } while (continueInput);
        }
        // Task #5: Close the PrintWriter
        out.close();
        System.out.println("Berhasil buat file KJ "+folderQuiz.getName()+".txt\n");
    }

    /**
     * Creates an answer file for a student in a quiz folder.
     * Prompts the user to enter the student's name and their answers.
     *
     * @param folderQuiz the folder for the quiz
     * @throws IOException if an I/O error occurs
     */
    public static void makeJawaban(File folderQuiz) throws IOException  {
        int totalQuestion;
        boolean continueInput;

        // Task #1: Receive the student's name and making the file for the student's answers
        System.out.print("\nMasukkan nama murid: ");
        String studentName = sc.nextLine();
        System.out.println("Masukkan jawaban: ");
        File kjFile = findFile(folderQuiz, String.format("KJ %s.txt", folderQuiz.getName()));
        File studentFile = new File(folderQuiz.getAbsolutePath()+"\\"+studentName+".txt");
        totalQuestion = hitungSoal(kjFile); // nanti tambah throws
        
        // Task #2: Warn the user if the student's file already exists
        if(studentFile.exists()){
            System.out.println("-------------------------------------");
            System.out.println("|  !  Jawaban akan di-overwrite  !  |");
            System.out.println("-------------------------------------");
        }

        // Task #3: Write the student's answers to the file
        PrintWriter out = new PrintWriter(studentFile);
        for (int i = 1; i <= totalQuestion; i++) {
            continueInput = true;
            do {
                String answer;
                System.out.print(i+". ");
                answer = sc.nextLine().toUpperCase();
                // Task #4: Validate the input (A, B, C, or D only and one character only)
                if (("ABCD".contains(answer) && answer.length()== 1)) {
                    continueInput = false;
                    out.println(i+". "+answer);
                }
                else {
                    System.out.println("Input tidak valid. Masukkan A, B, C, atau D.");
                }
            } while (continueInput);
        }
        // Task #5: Close the PrintWriter
        System.out.println("Berhasil buat file "+studentName+".txt\n");
        out.close();
    }

    /**
     * Allows the user to access a specific folder by providing its name.
     *
     * @return the accessed folder
     * @throws InvalidQuizException if the specified folder is not found
     */
    public static File aksesFolder() throws InvalidQuizException {
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
    
    /**
     * Finds a file with the specified name in the given folder.
     *
     * @param selectedFolder the folder to search in
     * @param fileName the name of the file to find
     * @return the found file, or null if not found
     */
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
    
    /**
     * Counts the number of matching lines between two files.
     *
     * @param file the first file
     * @param kjFile the second file (key)
     * @return the number of matching lines
     * @throws IOException if an I/O error occurs
     */
    public static int countMatchingLines(File file, File kjFile) throws IOException {
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

// DDP_D_2306165660_TheoAnandaLemuel_Lab6