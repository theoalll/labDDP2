package src.lab8;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BMICalculatorApp extends Application {
    Stage masterStage; // Master stage
    HBox master = addMainPane(); // Master pane

    // Input Fields
    TextField age;
    RadioButton male;
    RadioButton female;
    TextField height;
    TextField weight;
    
    // Output Fields
    Label errorMsg;
    Label masterHeaderOut;
    Label bmiIndexOut;
    Label idealWeightOut;
    Label statusOut;
    HBox mainPane;
    VBox inputPane;
    VBox outputPane;

    @Override
    public void start(Stage stage) {
        stage.setTitle("BMI Calculator by Theo");

        Scene scene = new Scene(master, 600, 300);
        stage.setScene(scene);
        stage.show();
        masterStage = stage;
    }

    // Membuat main pane 
    public HBox addMainPane() {
        // Konfigurasi child pane
        mainPane = new HBox();
        inputPane = new VBox();
        outputPane = new VBox();
        mainPane.setPadding(new Insets(15, 12, 15, 12));
        mainPane.setSpacing(10);
        inputPane.setPadding(new Insets(15, 12, 15, 12));
        inputPane.setSpacing(10);
        inputPane.setStyle("-fx-background-color: #d9d9d9;");
        outputPane.setPadding(new Insets(15, 12, 15, 12));
        outputPane.setSpacing(10);

        // Elemen (Node) inputPane
        // Label judul 
        Label masterHeaderIn = new Label("BMI Calculator");
        masterHeaderIn.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        inputPane.getChildren().add(masterHeaderIn);
        // Label error message
        errorMsg = new Label ("Welcome to BMI Calculator!");
        inputPane.getChildren().add(errorMsg);
        // Label input umur
        HBox agePane = new HBox();
        agePane.setSpacing(10);
        Label headAge = new Label("Age\t");
        agePane.getChildren().add(headAge);
        age = new TextField();
        agePane.getChildren().add(age);
        Label tailAge = new Label("ages: 2 - 120");
        agePane.getChildren().add(tailAge);
        inputPane.getChildren().add(agePane);
        // Label input gender
        HBox genderPane = new HBox();
        genderPane.setSpacing(10);
        Label headGender = new Label("Gender\t");
        genderPane.getChildren().add(headGender);
        ToggleGroup group = new ToggleGroup();
        male = new RadioButton("Male");
        female = new RadioButton("Female");
        male.setToggleGroup(group);
        female.setToggleGroup(group);
        genderPane.getChildren().add(male);
        genderPane.getChildren().add(female);
        inputPane.getChildren().add(genderPane);
        // Label input tinggi
        HBox heightPane = new HBox();
        Label headHeight = new Label("Height\t");
        heightPane.getChildren().add(headHeight);
        height = new TextField();
        heightPane.getChildren().add(height);
        Label tailHeight = new Label("  in cm");
        heightPane.getChildren().add(tailHeight);
        inputPane.getChildren().add(heightPane);
        // Label input berat
        HBox weightPane = new HBox();
        Label headWeight = new Label("Weight\t");
        weightPane.getChildren().add(headWeight);
        weight = new TextField();
        weightPane.getChildren().add(weight);
        Label tailWeight = new Label("  in kg");
        weightPane.getChildren().add(tailWeight);
        inputPane.getChildren().add(weightPane);
        // Button pane
        HBox buttonPane = new HBox();
        buttonPane.setSpacing(10);
        Button calculateBtn = new Button("Calculate!");
        calculateBtn.setStyle("-fx-background-color: #3cb043; -fx-font-size: 14; -fx-font-weight: BOLD");
        buttonPane.getChildren().add(calculateBtn);
        Button resetBtn = new Button("Reset");
        resetBtn.setStyle("-fx-font-size: 14; -fx-font-weight: BOLD");
        buttonPane.getChildren().add(resetBtn);
        Button exitBtn = new Button("Exit");
        buttonPane.getChildren().add(exitBtn);
        exitBtn.setStyle("-fx-background-color: #ff2c2c; -fx-font-size: 14; -fx-font-weight: BOLD");
        inputPane.getChildren().add(buttonPane);

        // Elemen (Node) output pane
        // Label judul output
        masterHeaderOut = new Label("Your Result:");
        masterHeaderOut.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        outputPane.getChildren().add(masterHeaderOut);
        // Label output bmi
        bmiIndexOut = new Label("BMI =");
        bmiIndexOut.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        outputPane.getChildren().add(bmiIndexOut);
        // Label output ideal weight
        idealWeightOut = new Label("Berat Badan Ideal =");
        idealWeightOut.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        outputPane.getChildren().add(idealWeightOut);
        // Label output status (kategori bmi)
        statusOut = new Label("You are ");
        statusOut.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        outputPane.getChildren().add(statusOut);
        // Label output deskripsi
        Label decsOut = new Label("• Healthy BMI range: 18.5 kg/m2 - 25 kg/m2\r\n" + //
                        "• Healthy weight for the height: 59.9 kg - 81 kg\r\n" + //
                        "• BMI Prime: 0.8\r\n" + //
                        "• Ponderal Index: 11.1 kg/m3\r\n" //
                        );
        outputPane.getChildren().add(decsOut);

        // Menggabungkan subpane ke main pane
        mainPane.getChildren().addAll(inputPane, outputPane);

        // Event Handler 
        resetBtn.setOnAction(new Reset());
        calculateBtn.setOnAction(new Process());
        exitBtn.setOnAction(new Exit());
        return mainPane;
    }

    /*
     * Event Handler untuk tombol reset (menghapus semua input dan output) 
     */
    class Reset implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            // Reset input
            age.setText("");
            male.setSelected(false);
            female.setSelected(false);
            height.setText("");
            weight.setText("");
            
            // Reset output
            errorMsg.setText("Welcome to BMI Calculator!");
            errorMsg.setStyle("-fx-font-size: 12;");
            masterHeaderOut.setText("Your Result:");
            masterHeaderOut.setStyle("-fx-font-size: 18; -fx-font-weight: BOLD");
            bmiIndexOut.setText("BMI =");
            bmiIndexOut.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            idealWeightOut.setText("Berat Badan Ideal =");
            idealWeightOut.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            masterHeaderOut.setStyle("-fx-font-size: 14; -fx-font-weight: BOLD");
            statusOut.setText("You are ");
            statusOut.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            masterHeaderOut.setStyle("-fx-font-size: 18; -fx-font-weight: BOLD");
            outputPane.setStyle("-fx-background-color: TRANSPARENT;");
        }
    }
    
    /*
     * Event Handler untuk tombol exit (menutup window/aplikasi)
     */
    class Exit implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            masterStage.close();
        }
    }

    /*
     * Event Handler untuk tombol calculate
     * 1. Mengambil input dari user
     * 2. Melakukan validasi input
     * 3. Menghitung BMI dan klasifikasinya
     * 4. Menampilkan hasil
     */
    class Process implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            int errorCode=0;
            try{
                // mengambil input
                int intHeight = Integer.parseInt(height.getText());
                int intWeight = Integer.parseInt(weight.getText());
                int intAge = Integer.parseInt(age.getText());
                double result;
                String bmi;

                // validasi input
                if (intHeight < 1 || intHeight > 199 ) {
                    errorCode=1;
                    throw (new Exception());
                }
                if (intWeight < 1 || intWeight > 199 ) {
                    errorCode=2;
                    throw (new Exception());
                }
                if (intAge < 2 || intAge > 120 ) {
                    errorCode=3;
                    throw (new Exception());
                }

                // menghitung bmi dan mencari klasifikasi
                result = ((intWeight*10000)/(double)(intHeight*intHeight));
                if (result < 18.5) {
                    bmi = "Underweight";
                    outputPane.setStyle("-fx-background-color: #a4c2f4;");
                }
                else if (result >= 18.5 && result < 25) {
                    bmi = "Normal";                    
                    outputPane.setStyle("-fx-background-color: #b6d7a8;");
                }
                else if (result >= 25 && result < 30){
                    bmi = "Overweight";
                    outputPane.setStyle("-fx-background-color: #ffe599;");
                }
                else{
                    bmi = "Obese";
                    outputPane.setStyle("-fx-background-color: #ea9999;");  
                }

                // Menghitung ideal weight
                double idealWeight;
                if (male.isSelected()) {
                    idealWeight = (intHeight-100) - ((intHeight-100)*0.1);
                }
                else{
                    idealWeight = (intHeight-100) - ((intHeight-100)*0.15);
                }

                // Menampilkan hasil
                errorMsg.setText("Welcome to BMI Calculator!");
                errorMsg.setStyle("-fx-font-size: 12;");
                masterHeaderOut.setText("Your Result:");
                masterHeaderOut.setStyle("-fx-font-size: 18; -fx-font-weight: BOLD");
                bmiIndexOut.setText(String.format("BMI = %.2f", result));
                bmiIndexOut.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                idealWeightOut.setText(String.format("Berat badan Ideal = %.2f", idealWeight));
                idealWeightOut.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                masterHeaderOut.setStyle("-fx-font-size: 14; -fx-font-weight: BOLD");
                statusOut.setText("You are " + bmi);
                statusOut.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                masterHeaderOut.setStyle("-fx-font-size: 18; -fx-font-weight: BOLD");
            }
            // Handle error jika input tidak valid
            catch (Exception e) {
                switch(errorCode) {
                    case 0 : errorMsg.setText("Masukkan semua input dengan valid!"); break;
                    case 1 : errorMsg.setText("Masukkan tinggi badan yang valid! (1-199 cm)"); break;
                    case 2 : errorMsg.setText("Masukkan berat badan yang valid! (1-199 kg)"); break;
                    case 3 : errorMsg.setText("Masukkan umur yang valid! (2-120 tahun)"); break;
                    default : break;
                }
                errorMsg.setStyle("-fx-background-color: #ff2c2c; -fx-font-weight: BOLD");
                masterHeaderOut.setText("Your Result: ERROR!");
                masterHeaderOut.setStyle("-fx-font-size: 18; -fx-font-weight: BOLD");
                bmiIndexOut.setText("BMI = ERROR!");
                bmiIndexOut.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                idealWeightOut.setText("Berat Badan Ideal = ERROR!");
                idealWeightOut.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                masterHeaderOut.setStyle("-fx-font-size: 14;  -fx-font-weight: BOLD");
                statusOut.setText("ERROR! ");
                statusOut.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                masterHeaderOut.setStyle("-fx-font-size: 18; -fx-font-weight: BOLD");
                outputPane.setStyle("-fx-background-color: TRANSPARENT;");
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab8