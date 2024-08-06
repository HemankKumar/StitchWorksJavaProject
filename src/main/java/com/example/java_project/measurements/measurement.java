package com.example.java_project.measurements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class measurement {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private ImageView picpath;

    @FXML
    private Label lblprice;

    @FXML
    private ComboBox<String> availableworkers;


    @FXML
    private ComboBox<String> selstatus;

    @FXML
    private TextField bill;

    @FXML
    private DatePicker dod;

    @FXML
    private ComboBox<String> dress;

    @FXML
    private TextField mobileno;

    @FXML
    private TextField priceperqty;

    @FXML
    private ComboBox<String> qty;

    @FXML
    private TextArea measurements;

    @FXML
    void doclose(ActionEvent event) {

    }

    @FXML
    void donew(ActionEvent event) {

        // we are clearing all the fields.
        dress.getSelectionModel().clearSelection();
        availableworkers.getSelectionModel().clearSelection();
        mobileno.clear();
        dod.setValue(null);
        qty.getSelectionModel().clearSelection();
        priceperqty.clear();
        measurements.clear();
        bill.clear();
        picpath.setImage(null);
        selstatus.getSelectionModel().clearSelection();

    }


    PreparedStatement stmt;
    @FXML
    void dosave(ActionEvent event) {
        String orderdate = String.valueOf(java.time.LocalDate.now());
        int order_id=0;

        try{
            stmt = con.prepareStatement("insert into measurementpage(order_id,dress,availableworkers,mobileno,dod,qty,priceperqty,picpath,bill,measurements,orderdate,selstatus) values(?,?,?,?,?,?,?,?,?,?,?,?)");

            stmt.setInt(1,order_id);

            stmt.setString(2,dress.getSelectionModel().getSelectedItem());

            stmt.setString(3,availableworkers.getSelectionModel().getSelectedItem());

            stmt.setString(4,mobileno.getText());

            LocalDate dt=dod.getValue();
            java.sql.Date dtt=java.sql.Date.valueOf(dt);
            stmt.setDate(5, dtt);

            stmt.setString(6,qty.getSelectionModel().getSelectedItem());

            stmt.setString(7,priceperqty.getText());

            stmt.setString(8,filepath);

            stmt.setString(9,bill.getText());

            stmt.setString(10,measurements.getText());

            stmt.setString(11,orderdate);

            stmt.setString(12, String.valueOf(selstatus.getSelectionModel().getSelectedItem()));

            stmt.executeUpdate();

            showMyMsg("Record saved successfully!!");



        }
        catch (Exception ex){
            ex.printStackTrace();
        }



    }

    @FXML
    void doupdate(ActionEvent event) {

    }

    String filepath="nopic.jpg";
    @FXML
    void doupload(ActionEvent event) {

        FileChooser chooser=new FileChooser();
        chooser.setTitle("Select Profile Pic:");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("*.*", "*.*")
        );
        File file=chooser.showOpenDialog(null);
        filepath=file.getAbsolutePath();


        try {
            picpath.setImage(new Image(new FileInputStream(file)));
        }
        catch (FileNotFoundException e)
        {	e.printStackTrace();
        }
    }


    private  void fill_workers_combo() {

        availableworkers.getItems().clear();

        String selected_dress=dress.getSelectionModel().getSelectedItem();

        if(selected_dress!=null){
            String query="Select workername from workerspage where selspz LIKE ?";

            try(PreparedStatement stmt = con.prepareStatement(query)){

                stmt.setString(1, "%" + selected_dress + "%");
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        availableworkers.getItems().add(rs.getString("workername"));
                    }
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void calulate_total_bill(ActionEvent event) {

        try {
            int quantity = Integer.parseInt(qty.getSelectionModel().getSelectedItem());
            int price = Integer.parseInt(priceperqty.getText());
            int total = quantity * price;
            bill.setText(String.valueOf(total));
        }
        catch(NumberFormatException ex){
            lblprice.setText("(Enter Integer Only)");
            bill.setText(null);
        }
    }


    // Alert Box Messages..
    @FXML
    void showMyMsg(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");

        alert.setHeaderText("Its Header");
        alert.setContentText(msg);

        alert.showAndWait();
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


        //Direct method of filling an array with objects into combo-box.

        selstatus.getItems().addAll("1","2","3");//filling status values in status combo-box.

        dress.getItems().addAll("Pent","Shirt","Coat","Woolen clothes","summer clothes","trousers","shorts"); // filling dresses in dress combo-box.

        for(int i=1;i<=50;i++){
            qty.getItems().addAll(String.valueOf(i));//filling qty values in qty combo-box.
        }

        dress.setOnAction(event->fill_workers_combo()); // New method to make onAction function.

    }

}
