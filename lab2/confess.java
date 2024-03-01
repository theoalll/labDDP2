import java.util.Scanner;

public class confess {
// Method reverseString untuk membalikkan string hasil confession
    public static String reverseString(String str) {
        if (str.length()==0)
            return "";
        else
            return str.charAt(str.length()-1) + reverseString(str.substring(0, str.length()-1));
    }
// Main method
    public static void main(String[] args) {
    // Menampung input user
        String inputString;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan confession dalam bentuk kode (ketik 'selesai' untuk keluar):");
        while (true){
            inputString = scanner.nextLine();
            if(inputString.equals("selesai")){
                break;
            }
    // Memproses input user dan menampilkan hasil confession
            String extractedString = reverseString(getConfess(getConfessWrapper(inputString), "", 0, 0));
            System.out.println(extractedString);
        }
        scanner.close();
    }

// Method getConfessWrapper untuk menambahkan karakter non-binary di awal dan akhir string
    public static  String getConfessWrapper(String confess){
        return " "+confess+" ";
    }

// Method getConfess untuk mengubah confession menjadi string
    public static String getConfess(String confess, String currentTranslation, int currentDecimal, int currentExponent){
    // Base case: jika confession kosong, kembalikan hasil translation
        if (confess.length() == 0)
            return currentTranslation;
    // Membaca karakter terakhir confession, validasi apakah confession merupakan karakter digit atau bukan
        else if (Character.isDigit(confess.charAt(confess.length()-1))){
        // Jika confession merupakan karakter *BINER*, tambahkan nilai desimal dan eksponennya, proses lagi tanpa karakter terakhir
            if (Character.getNumericValue(confess.charAt(confess.length()-1))<2)
                return getConfess(confess.substring(0,confess.length()-1), currentTranslation, currentDecimal+ Character.getNumericValue(confess.charAt(confess.length()-1))*(int)Math.pow(2,currentExponent), currentExponent+=1);
        // Jika confession merupakan karakter *NON-BINER*, buang karakter terakhir, proses lagi tanpa karakter terakhir
            else
                return getConfess(confess.substring(0,confess.length()-1), currentTranslation, currentDecimal, currentExponent);
        }
    // Jika confession merupakan karakter non-digit, tambahkan hasil translation dengan karakter ASCII, proses lagi tanpa karakter terakhir, reset nilai desimal dan eksponen menjadi 0
        else
            return getConfess(confess.substring(0,confess.length()-1), currentTranslation+asciiToString(currentDecimal), 0, 0);            
    }

// Method asciiToString untuk mengubah nilai ASCII menjadi karakter
    public static char asciiToString(int asciiValue) {
        if (asciiValue == 0) 
            return 0;
        else
            return (char)asciiValue;
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab2