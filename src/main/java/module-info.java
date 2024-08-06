module com.example.java_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;
    requires java.mail;

    opens com.example.java_project to javafx.fxml;
    exports com.example.java_project;


    exports com.example.java_project.customer_profile_page;
    opens com.example.java_project.customer_profile_page to javafx.fxml;

    exports com.example.java_project.workerrecruitmentpage;
    opens com.example.java_project.workerrecruitmentpage to javafx.fxml;

    exports com.example.java_project.measurements;
    opens com.example.java_project.measurements to javafx.fxml;

    exports com.example.java_project.Tableview_of_workers;
    opens com.example.java_project.Tableview_of_workers to javafx.fxml;

    exports com.example.java_project.Dress_Manager;
    opens com.example.java_project.Dress_Manager to javafx.fxml;

    exports com.example.java_project.OrderDileveryPanel;
    opens com.example.java_project.OrderDileveryPanel to javafx.fxml;

    exports com.example.java_project.Measurement_Explorer;
    opens com.example.java_project.Measurement_Explorer to javafx.fxml;

    exports com.example.java_project.sendingemail;
    opens com.example.java_project.sendingemail to javafx.fxml;

    exports com.example.java_project.Owner_Dashboard;
    opens com.example.java_project.Owner_Dashboard to javafx.fxml;





}