package com.example.java_project.OrderDileveryPanel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.java_project.measurements.mysqlconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OrderDilevery {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Integer> qtyids;

    @FXML
    private ListView<String> statusids;

    @FXML
    private ListView<Integer> Billids;

    @FXML
    private ListView<Integer> Orderids;

    @FXML
    private ListView<String> dressids;

    @FXML
    private TextField mobilenoids;

    @FXML
    private TextField showbillids;

    PreparedStatement stmt;
    @FXML
    void Find_all_orders(ActionEvent event) {

        statusids.getItems().clear();
        Orderids.getItems().clear();
        Billids.getItems().clear();
        dressids.getItems().clear();
        qtyids.getItems().clear();


        int totalBill=0;
        showbillids.setText(String.valueOf(totalBill));

        try {
            String mno = mobilenoids.getText();
            if (mno == null || mno.isEmpty()) {
                throw new IllegalArgumentException("Enter Mobile number");
            }



            String query = "SELECT * FROM measurementpage WHERE mobileno = ? AND (selstatus = 1 OR selstatus = 2)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, mno);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("order_id");

                String d = rs.getString("dress");

                int s = rs.getInt("selstatus");

                int b = rs.getInt("bill");

                int q = rs.getInt("qty");
                if(s==1)
                {
                    statusids.getItems().add("In progress");
                }
                else{
                    statusids.getItems().add("Ready");
                    totalBill+=b; // Bill of only those items will be calculated who have status == 2.
                }
                Orderids.getItems().add(id);

                dressids.getItems().add(d);

                qtyids.getItems().add(q);

                Billids.getItems().add(b);

                System.out.println("Order ID: " + id + ", Stat: " + s + ", Bill: " + b + ", Dress: " + d + ", Quantity: " + q);
            }

            showbillids.setText(String.valueOf(totalBill));

        } catch (SQLException e) {
            e.printStackTrace();
            showMyMsg("SQL Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showMyMsg("Invalid Mno: " + mobilenoids.getText());
        } catch (Exception e) {
            e.printStackTrace();
            showMyMsg("Error: " + e.getMessage());
        }

    }



    @FXML
    void do_Deliver_dresses(ActionEvent event) {

        try {

            String mno = mobilenoids.getText();


            if (mno == null || mno.isEmpty()) {
                throw new IllegalArgumentException("Enter Mobile number");
            }

            String query = "update measurementpage set selstatus=3 where mobileno = ? AND selstatus = 2";
            stmt = con.prepareStatement(query);
            stmt.setString(1, mno);

            stmt.executeUpdate();
            showMyMsg(" All Ready Dresses are Delivered to customer");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    void showMyMsg(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Its Header");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    Connection con;
    @FXML
    void initialize() {
        con= mysqlconnection.doConnect();
        if(con==null){
            System.out.println("connection not established!");
        }
        else{
            System.out.println("Connection Established!");
        }


    }

}
