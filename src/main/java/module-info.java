module za.co.marvinlamohr.thecubanalounge {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens za.co.marvinlamohr.thecubanalounge to javafx.fxml;
    exports za.co.marvinlamohr.thecubanalounge;

}