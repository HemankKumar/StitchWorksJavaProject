package com.example.java_project.customer_profile_page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class profile_page {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<String> city;

    @FXML
    private DatePicker dob;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField mobileno;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private ImageView picpath;

    @FXML
    private ComboBox<String> state;

    @FXML
    void dodelete(ActionEvent event) {

        try {
            stmt=con.prepareStatement("delete from customer_profile_page where email=?");
            stmt.setString(1,email.getText());
            int count=stmt.executeUpdate();
            if(count==1)
                System.out.println("Record Deleted Successsssfulllyyy");
            else
                System.out.println("Invalid email");

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }

        showMyMsg("Your Profile is Deleted!");

    }

    PreparedStatement stmt;
    @FXML
    void dosave(ActionEvent event) {

        try {
            stmt=con.prepareStatement("insert into customer_profile_page values(?,?,?,?,?,?,?,?,?)");

            stmt.setString(1,email.getText());

            stmt.setString(2,name.getText());

            stmt.setString(3,mobileno.getText());

            stmt.setString(4,gender.getSelectionModel().getSelectedItem());

            LocalDate dt=dob.getValue();
            java.sql.Date date=java.sql.Date.valueOf(dt);
            stmt.setDate(5, date);

            stmt.setString(6,address.getText());

            stmt.setString(7,state.getSelectionModel().getSelectedItem());

            stmt.setString(8,city.getSelectionModel().getSelectedItem());

            stmt.setString(9,filepath);
            stmt.executeUpdate();


        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }

        showMyMsg("Your Profile is Saved!");

    }

    @FXML
    void dosearch(ActionEvent event) {

        try{
            stmt=con.prepareStatement("select *from customer_profile_page where email=?");
            stmt.setString(1, email.getText());
            ResultSet records=stmt.executeQuery();
            while(records.next()){
                String emails=records.getString("email");
                String Namee=records.getString("name");
                String mno=records.getString("mobileno");
                String genderr=records.getString("gender");
                Date dt=records.getDate("dob");
                String addresss=records.getString("address");
                String statee=records.getString("state");
                String cityy=records.getString("city");
                String pathh=records.getString("picpath");

                System.out.println(emails+ " " +Namee+ "  " + mno+ "  " + genderr + "  " + dt + "  " + statee + "  " + cityy + " " + pathh);

                email.setText(emails);
                name.setText(Namee);
                mobileno.setText(mno);
                gender.getEditor().setText(genderr);
                dob.setValue(dt.toLocalDate());
                address.setText(addresss);
                state.getEditor().setText(statee);
                city.getEditor().setText(cityy);

                if(!pathh.equals("nopic.jpg"))
                {
                    filepath=pathh;
                    picpath.setImage(new Image(new FileInputStream(filepath)));
                }

            }


        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }

    @FXML
    void doupdate(ActionEvent event) {

        try {
            stmt=con.prepareStatement("update customer_profile_page set name=?,mobileno=?,gender=?,dob=?,address=?,state=?,city=?,picpath=? where email=?");

            stmt.setString(1,name.getText());

            stmt.setString(2,mobileno.getText());


            stmt.setString(3,gender.getSelectionModel().getSelectedItem());

            LocalDate dt=dob.getValue();
            java.sql.Date date=java.sql.Date.valueOf(dt);
            stmt.setDate(4, date);

            stmt.setString(5,address.getText());

            stmt.setString(6,state.getSelectionModel().getSelectedItem());

            stmt.setString(7,city.getSelectionModel().getSelectedItem());

            stmt.setString(8,filepath);

            stmt.setString(9,email.getText());

            stmt.executeUpdate();



        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }

        showMyMsg("Your Profile is Updated!");

    }

    String filepath="nopic.jpg";
    @FXML
    void douploadprofilepic(ActionEvent event) {

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
           System.out.println("Connect not established!");
       }
       else{
           System.out.println("Connection established!");
       }


       gender.getItems().addAll("Male","Female");
    }

}
