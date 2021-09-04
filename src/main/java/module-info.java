module com.example.banksubcriptiondetector {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.banksubcriptiondetector to javafx.fxml;
    exports com.example.banksubcriptiondetector;
}