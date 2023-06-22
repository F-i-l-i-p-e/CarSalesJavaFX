module com.example.f22midterm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Added for SQL


    opens com.example.f22midterm to javafx.fxml;
    exports com.example.f22midterm;
}