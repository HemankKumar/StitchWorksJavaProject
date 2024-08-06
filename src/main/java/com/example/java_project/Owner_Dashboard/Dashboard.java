package com.example.java_project.Owner_Dashboard;

import com.example.java_project.HelloApplication;
import com.example.java_project.customer_profile_page.profile_page;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class Dashboard {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button allenrollcustomers;

    @FXML
    private Button allenrollworkers;

    @FXML
    private Button allorders;

    @FXML
    private Button deliverproducts;

    @FXML
    private Button logoutid;

    @FXML
    private Button neworder;

    @FXML
    private Button workerinfo;

    @FXML
    private TextField userid;

    @FXML
    private TextField userpwd;

    @FXML
    private ImageView emaillogo;

    @FXML
    private ImageView pwd_logo;

    @FXML
    private Ellipse loginCircle;

    @FXML
    private Label loginEnterUid;

    @FXML
    private Button login_id;

    @FXML
    private Label loginenterpwd;

    @FXML
    private PieChart statusPieChart;

    PreparedStatement stmt;


    @FXML
    void doLogout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Logout Confirmation");
        alert.setContentText("Are you sure you want to log out?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                workerinfo.setDisable(true);
                deliverproducts.setDisable(true);
                allenrollcustomers.setDisable(true);
                allenrollworkers.setDisable(true);
                //getReadyItmsButton.setDisable(false);
                allorders.setDisable(true);
                neworder.setDisable(true);
                logoutid.setDisable(true);

                loginCircle.setVisible(true);
                emaillogo.setVisible(true);
                pwd_logo.setVisible(true);
                login_id.setVisible(true);
                loginEnterUid.setVisible(true);
                loginenterpwd.setVisible(true);
                userid.setVisible(true);
                userid.setDisable(false);
                userpwd.setVisible(true);
                userpwd.setDisable(false);

                statusPieChart.setVisible(false);
                statusPieChart.setDisable(true);
                /*
                orderstatuslabel.setVisible(false);
                refreshButton.setVisible(false);
                refreshButton.setDisable(true);

                 */


            }
        });
        userid.clear();
        userpwd.clear();

    }


    @FXML
    void doLogin(ActionEvent event) {

        try {
            String uId = userid.getText();
            String uPwd = userpwd.getText();

            if (uId == null || uId.isEmpty()) {
                showMyMsg("Please Fill User ID");
                return;
            }
            if (uPwd == null || uPwd.isEmpty()) {
                showMyMsg("Please Enter the Password");
                return;
            }

            String query = "SELECT * FROM owner WHERE ownerId = ? AND ownerPwd = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, uId);
            stmt.setString(2, uPwd);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                workerinfo.setDisable(false);
                deliverproducts.setDisable(false);
                allenrollcustomers.setDisable(false);
                allenrollworkers.setDisable(false);
                //getReadyItmsButton.setDisable(false);
                allorders.setDisable(false);
                neworder.setDisable(false);
                logoutid.setDisable(false);

                loginCircle.setVisible(false);
                emaillogo.setVisible(false);
                pwd_logo.setVisible(false);
                loginEnterUid.setVisible(false);
                loginenterpwd.setVisible(false);
                userid.setVisible(false);
                userid.setDisable(true);
                userpwd.setVisible(false);
                userpwd.setDisable(true);
                login_id.setVisible(false);

                statusPieChart.setVisible(true);
                statusPieChart.setDisable(false);
                /*
                orderstatuslabel.setVisible(true);
                refreshButton.setVisible(true);
                refreshButton.setDisable(false);
                */




                showMyMsg("Login Successful!");
            } else {
                showMyMsg("Invalid User ID or Password");
            }


            rs.close();
            stmt.close();
        } catch (Exception exp) {
            exp.printStackTrace();
            showMyMsg("Error occurred during login");
        }

    }


    int getRecords(int s) {
        try {

            String query = "SELECT COUNT(*) FROM measurementpage WHERE selstatus = ?";
            PreparedStatement stmt1 = con.prepareStatement(query);
            stmt1.setInt(1, s); // Use oi from the doSearch function
            ResultSet rs1 = stmt1.executeQuery();

            if(rs1.next())
            {
                int i=rs1.getInt(1);
                return i;
            }
            else return 0;
        }catch (Exception exp)
        {
            exp.printStackTrace();
        }
        return 0;
    }


    //Routing.

    @FXML
    void go_enroll_customers(ActionEvent event) {
        openNewScene("customer_profile_pages/profile_customer.fxml", "Enroll Customers");
    }

    @FXML
    void go_Worker_info(ActionEvent event) {
        openNewScene("Tableview_of_customerss/tablevieww.fxml", "Customers Information");


    }

    @FXML
    void go_all_orders(ActionEvent event) {
        openNewScene("Dress_managerss/Dresss.fxml", "All Orders");

    }

    @FXML
    void go_deliver_products(ActionEvent event) {
        openNewScene("OrderDeliveryPanels/Orders.fxml", "Orders Delivery");
    }


    @FXML
    void go_enroll_workers(ActionEvent event) {
        openNewScene("workerrecruitmentpages/workers.fxml", "Enroll Workers");
    }

    @FXML
    void go_new_orders(ActionEvent event) {
        openNewScene("measurementss/measurementss.fxml", "Place Order");


    }


    private void openNewScene(String fxmlPath, String title) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.resizableProperty().setValue(false);
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showMyMsg("Error loading " + title + " view: " + e.getMessage());
        }
    }

    Connection con;
    @FXML
    void initialize() {
        con=mysqlconnection.doConnect();
        if(con==null){
            System.out.println("Connection Not Established!");
        }
        else{
            System.out.println("Connection Established!");
        }

        int inPg=getRecords(1);
        int Deliver=getRecords(3);
        int Recieved=getRecords(2);

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(new PieChart.Data("In process "+ inPg,inPg),new PieChart.Data("Deliverd "+Deliver,Deliver),new PieChart.Data("Received "+Recieved,Recieved));
        statusPieChart.setData(data);



    }


    void showMyMsg(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
