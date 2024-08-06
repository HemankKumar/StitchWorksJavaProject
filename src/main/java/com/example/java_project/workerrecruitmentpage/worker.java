package com.example.java_project.workerrecruitmentpage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class worker {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField address;

    @FXML
    private TextField mobileno;

    @FXML
    private TextField selspz;

    @FXML
    private ListView<String> lstspz;

    @FXML
    private TextField workername;

    @FXML
    void addspzintextbox(MouseEvent event) {

        if(event.getClickCount()==2){
            String selecteditems=lstspz.getSelectionModel().getSelectedItem();
            if(selecteditems!=null){
                selspz.appendText(selecteditems + ",");
            }
        }
    }

    PreparedStatement stmt;
    @FXML
    void dosave(ActionEvent event) {
        try {
            stmt = con.prepareStatement("insert into workerspage values(?,?,?,?)");

            stmt.setString(1,workername.getText());

            stmt.setString(2,mobileno.getText());

            stmt.setString(3,address.getText());

            stmt.setString(4,selspz.getText());

            stmt.executeUpdate();

            showMyMsg("Record Saved!","Success",Alert.AlertType.INFORMATION);


        }
        catch(Exception ex){
            ex.printStackTrace();

            showMyMsg("Record Not Saved!","Error",Alert.AlertType.ERROR);
        }
    }

    @FXML
    void donew(ActionEvent event) {
        workername.clear();
        address.clear();
        mobileno.clear();
        selspz.clear();
        lstspz.getSelectionModel().clearSelection();
    }

    void showMyMsg(String msg,String title,Alert.AlertType alerttype)
    {
        Alert alert = new Alert(alerttype);

        alert.setTitle(title);

        alert.setContentText(msg);

        alert.showAndWait();
    }

    void addspzinlist(){

        ObservableList<String> items = FXCollections.observableArrayList(
                "Pent", "Shirt", "Coat" ,"Woolen clothes","summer clothes","trousers","shorts"
        );

        lstspz.setItems(items);
    }



    Connection con;
    @FXML
    void initialize() {
       con=mysqlconnection.doConnect();
       if(con==null){
           System.out.println("connection not established!");
       }
       else{
           System.out.println("Connection Established!");
       }

       addspzinlist();

    }

}
