package com.example.java_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customer_profile_pages/profile_customer.fxml"));

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("workerrecruitmentpages/workers.fxml"));

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("measurementss/measurementss.fxml"));

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Tableview_of_customerss/tablevieww.fxml"));

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Dress_managerss/Dresss.fxml"));

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OrderDeliveryPanels/Orders.fxml"));

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Measurement_Explorerrr/measurement_explorerr.fxml"));

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sendingemails/mail.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Owner_dashboards/dash.fxml"));





        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}