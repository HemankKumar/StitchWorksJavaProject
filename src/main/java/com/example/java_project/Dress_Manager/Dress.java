package com.example.java_project.Dress_Manager;

import java.net.ConnectException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Dress {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea emailMsg;

    @FXML
    private ListView<String> Orderid;

    @FXML
    private ListView<String> dateofdelivery;

    @FXML
    private ListView<String> dressid;

    @FXML
    private ComboBox<String> selectworker;

    @FXML
    private Button Recievedall;


    private void populateWorkersWithStatusOne() {
        selectworker.getItems().clear(); // Clear previous items
        String query = "SELECT DISTINCT availableworkers FROM measurementpage WHERE selstatus = '1'";
        try (PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                selectworker.getItems().add(rs.getString("availableworkers"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void populateOrderDetailsForSelectedWorker() {
        String selectedWorker = selectworker.getSelectionModel().getSelectedItem();
        if (selectedWorker != null) {
            Orderid.getItems().clear();
            dressid.getItems().clear();
            dateofdelivery.getItems().clear();

            String query = "SELECT order_id, dress, dod FROM measurementpage WHERE availableworkers = ? AND selstatus = '1'";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, selectedWorker);
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        Orderid.getItems().add(String.valueOf(rs.getInt("order_id")));
                        dressid.getItems().add(rs.getString("dress"));
                        dateofdelivery.getItems().add(rs.getDate("dod").toString());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    private void handleOrderIDDoubleClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            String selectedOrderID = Orderid.getSelectionModel().getSelectedItem();
            if (selectedOrderID != null) {
                updateOrderStatusTo2(Integer.parseInt(selectedOrderID));
                removeOrderFromListView(selectedOrderID);

            }
        }
    }

    private void updateOrderStatusTo2(int order_id) {
        String query = "UPDATE measurementpage SET selstatus = '2' WHERE order_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, order_id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeOrderFromListView(String orderId) {
        int index = Orderid.getItems().indexOf(orderId);
        if (index >= 0) {
            Orderid.getItems().remove(index);
            dressid.getItems().remove(index);
            dateofdelivery.getItems().remove(index);
        }
    }


    private void handleReceiveAll() {
        String selectedWorker = selectworker.getSelectionModel().getSelectedItem();
        if (selectedWorker != null) {
            updateAllOrdersStatusTo2ForWorker(selectedWorker);
            send_email_to_customer();
            clearListView();
        }
    }


    //This function is used to send email to the customer about his Clothes.
    private void send_email_to_customer(){

        String from = "hemankkumar92@gmail.com"; // sender's email
        final String username = "hemankkumar92@gmail.com"; // your Gmail address
        final String password = "ruob zghg dckl dpwr"; // your app password

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("hemankkumar221@gmail.com"));
            message.setSubject("Stitch Works Shop");
            String emailBody = emailMsg.getText();
            message.setText(emailBody);
            Transport.send(message);

            showMyMsg("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }



    private void updateAllOrdersStatusTo2ForWorker(String worker) {
        String query = "UPDATE measurementpage SET selstatus = '2' WHERE availableworkers = ? AND selstatus = '1'";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, worker);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void clearListView() {
        Orderid.getItems().clear();
        dressid.getItems().clear();
        dateofdelivery.getItems().clear();
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
            System.out.println("Connection not Established!");
        }
        else{
            System.out.println("Connection Established!");
        }

        // Add event listener to populate ComboBox when clicked
        selectworker.setOnMouseClicked(event -> populateWorkersWithStatusOne());

        // Add event listener to ComboBox selection change
        selectworker.setOnAction(event -> populateOrderDetailsForSelectedWorker());

        // Add double-click event listener for ListView items
        Orderid.setOnMouseClicked(event -> handleOrderIDDoubleClick(event));

        //Adding action on Recieved button click.
        Recievedall.setOnAction(event -> handleReceiveAll());
    }

}










