package com.example.java_project.Measurement_Explorer;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class measurement_explo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cstatus;

    @FXML
    private ComboBox<String> cworker;

    @FXML
    private TableView<ExplorerBean> tblvieww;

    @FXML
    private TextField txtmobileno;



    PreparedStatement stmt;

    //This function is used to show all customer detail by using their mobile.no
    @FXML
    void doshow_by_mobileno(ActionEvent event) {

        tblvieww.getColumns().clear();

        TableColumn<ExplorerBean, String> a=new TableColumn<ExplorerBean, String>("order_Id");//kuch bhi
        a.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        a.setMinWidth(100);

        TableColumn<ExplorerBean, String> b=new TableColumn<ExplorerBean, String>("Dress");//kuch bhi
        b.setCellValueFactory(new PropertyValueFactory<>("dress"));
        b.setMinWidth(100);

        TableColumn<ExplorerBean, String> c=new TableColumn<ExplorerBean, String>("Worker Name");//kuch bhi
        c.setCellValueFactory(new PropertyValueFactory<>("availableworkers"));
        c.setMinWidth(100);


        TableColumn<ExplorerBean, String> d=new TableColumn<ExplorerBean, String>("Mobile.no");//kuch bhi
        d.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
        d.setMinWidth(100);

        TableColumn<ExplorerBean, String> e=new TableColumn<ExplorerBean, String>("Dilevery Date");//kuch bhi
        e.setCellValueFactory(new PropertyValueFactory<>("dod"));
        e.setMinWidth(100);

        TableColumn<ExplorerBean, String> p=new TableColumn<ExplorerBean, String>("Quantity");//kuch bhi
        p.setCellValueFactory(new PropertyValueFactory<>("qty"));
        p.setMinWidth(100);

        TableColumn<ExplorerBean, String> f=new TableColumn<ExplorerBean, String>("Price Per Quantity");//kuch bhi
        f.setCellValueFactory(new PropertyValueFactory<>("priceperqty"));
        f.setMinWidth(100);

        TableColumn<ExplorerBean, String> g=new TableColumn<ExplorerBean, String>("Pic Path");//kuch bhi
        g.setCellValueFactory(new PropertyValueFactory<>("picpath"));
        g.setMinWidth(100);

        TableColumn<ExplorerBean, String> h=new TableColumn<ExplorerBean, String>("Toal Bill");//kuch bhi
        h.setCellValueFactory(new PropertyValueFactory<>("bill"));
        h.setMinWidth(100);

        TableColumn<ExplorerBean, String> i=new TableColumn<ExplorerBean, String>("Measurements");//kuch bhi
        i.setCellValueFactory(new PropertyValueFactory<>("measurements"));
        i.setMinWidth(100);

        TableColumn<ExplorerBean, String> j=new TableColumn<ExplorerBean, String>("Order Date");//kuch bhi
        j.setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        j.setMinWidth(100);

        TableColumn<ExplorerBean, String> k=new TableColumn<ExplorerBean, String>("Status");//kuch bhi
        k.setCellValueFactory(new PropertyValueFactory<>("selstatus"));
        k.setMinWidth(100);

        tblvieww.getColumns().addAll(a,b,c,d,e,p,f,g,h,i,j,k);
        tblvieww.setItems(getrecords());
    }

    ObservableList<ExplorerBean>getrecords() {

        ObservableList<ExplorerBean> ary= FXCollections.observableArrayList();

        try{

            String customerPhone = txtmobileno.getText();

            if (customerPhone == null || customerPhone.isEmpty()) {
                System.out.println("Please enter a phone number.");
                return ary;
            }

            String query = "SELECT * FROM measurementpage WHERE mobileno = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, customerPhone);

            ResultSet allrecord= stmt.executeQuery();

            while(allrecord.next()){
                int order_idd=allrecord.getInt("order_id");
                String dresss=allrecord.getString("dress");
                String available=allrecord.getString("availableworkers");
                String mob=allrecord.getString("mobileno");
                Date dodd=allrecord.getDate("dod");
                int qtyy=allrecord.getInt("qty");
                int ppq=allrecord.getInt("priceperqty");
                String picpathh=allrecord.getString("picpath");
                int billl=allrecord.getInt("bill");
                String measurement=allrecord.getString("measurements");
                Date orderdatee=allrecord.getDate("orderdate");
                String status=allrecord.getString("selstatus");

                ary.add(new ExplorerBean(order_idd,dresss,available,mob,dodd,qtyy,ppq,picpathh,billl,measurement,orderdatee,status));



            }
        }
        catch (Exception ex){
            ex.printStackTrace();

        }

        return ary;

    }






    //This function will show all data when we select worker name from combo-box.
    @FXML
    void getWorkerRecord(ActionEvent event) {

        tblvieww.getColumns().clear();

        TableColumn<ExplorerBean, String> a=new TableColumn<ExplorerBean, String>("order_Id");//kuch bhi
        a.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        a.setMinWidth(100);

        TableColumn<ExplorerBean, String> b=new TableColumn<ExplorerBean, String>("Dress");//kuch bhi
        b.setCellValueFactory(new PropertyValueFactory<>("dress"));
        b.setMinWidth(100);

        TableColumn<ExplorerBean, String> c=new TableColumn<ExplorerBean, String>("Worker Name");//kuch bhi
        c.setCellValueFactory(new PropertyValueFactory<>("availableworkers"));
        c.setMinWidth(100);


        TableColumn<ExplorerBean, String> d=new TableColumn<ExplorerBean, String>("Mobile.no");//kuch bhi
        d.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
        d.setMinWidth(100);

        TableColumn<ExplorerBean, String> e=new TableColumn<ExplorerBean, String>("Dilevery Date");//kuch bhi
        e.setCellValueFactory(new PropertyValueFactory<>("dod"));
        e.setMinWidth(100);

        TableColumn<ExplorerBean, String> p=new TableColumn<ExplorerBean, String>("Quantity");//kuch bhi
        p.setCellValueFactory(new PropertyValueFactory<>("qty"));
        p.setMinWidth(100);

        TableColumn<ExplorerBean, String> f=new TableColumn<ExplorerBean, String>("Price Per Quantity");//kuch bhi
        f.setCellValueFactory(new PropertyValueFactory<>("priceperqty"));
        f.setMinWidth(100);

        TableColumn<ExplorerBean, String> g=new TableColumn<ExplorerBean, String>("Pic Path");//kuch bhi
        g.setCellValueFactory(new PropertyValueFactory<>("picpath"));
        g.setMinWidth(100);

        TableColumn<ExplorerBean, String> h=new TableColumn<ExplorerBean, String>("Toal Bill");//kuch bhi
        h.setCellValueFactory(new PropertyValueFactory<>("bill"));
        h.setMinWidth(100);

        TableColumn<ExplorerBean, String> i=new TableColumn<ExplorerBean, String>("Measurements");//kuch bhi
        i.setCellValueFactory(new PropertyValueFactory<>("measurements"));
        i.setMinWidth(100);

        TableColumn<ExplorerBean, String> j=new TableColumn<ExplorerBean, String>("Order Date");//kuch bhi
        j.setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        j.setMinWidth(100);

        TableColumn<ExplorerBean, String> k=new TableColumn<ExplorerBean, String>("Status");//kuch bhi
        k.setCellValueFactory(new PropertyValueFactory<>("selstatus"));
        k.setMinWidth(100);

        tblvieww.getColumns().addAll(a,b,c,d,e,p,f,g,h,i,j,k);
        tblvieww.setItems(getWorkerSpecificRecords());

    }

    ObservableList<ExplorerBean> getWorkerSpecificRecords() {
        ObservableList<ExplorerBean> ary = FXCollections.observableArrayList();

        try {
            String selectedWorker = cworker.getValue();
            String selectedStatus = cstatus.getValue();

            String query = "SELECT * FROM measurementpage WHERE availableworkers = ?";
            if (selectedStatus != null && !selectedStatus.isEmpty()) {
                query += " AND selstatus = ?";
            }

            stmt = con.prepareStatement(query);
            stmt.setString(1, selectedWorker);

            if (selectedStatus != null && !selectedStatus.isEmpty()) {
                int status = 0;
                switch (selectedStatus) {
                    case "Order Placed":
                        status = 1;
                        break;
                    case "Received From Worker":
                        status = 2;
                        break;
                    case "Orders Delivered":
                        status = 3;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid status: " + selectedStatus);
                }
                stmt.setInt(2,status);

            }

            ResultSet allrecord = stmt.executeQuery();

            while (allrecord.next()) {
                int order_idd=allrecord.getInt("order_id");
                String dresss=allrecord.getString("dress");
                String available=allrecord.getString("availableworkers");
                String mob=allrecord.getString("mobileno");
                Date dodd=allrecord.getDate("dod");
                int qtyy=allrecord.getInt("qty");
                int ppq=allrecord.getInt("priceperqty");
                String picpathh=allrecord.getString("picpath");
                int billl=allrecord.getInt("bill");
                String measurement=allrecord.getString("measurements");
                Date orderdatee=allrecord.getDate("orderdate");
                String status=allrecord.getString("selstatus");

                ary.add(new ExplorerBean(order_idd,dresss,available,mob,dodd,qtyy,ppq,picpathh,billl,measurement,orderdatee,status));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ary;
    }






    //This function will show all data  when we select status from combo-box.
    @FXML
    void get_record_by_status(ActionEvent event) {

        tblvieww.getColumns().clear();

        TableColumn<ExplorerBean, String> a=new TableColumn<ExplorerBean, String>("order_Id");//kuch bhi
        a.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        a.setMinWidth(100);

        TableColumn<ExplorerBean, String> b=new TableColumn<ExplorerBean, String>("Dress");//kuch bhi
        b.setCellValueFactory(new PropertyValueFactory<>("dress"));
        b.setMinWidth(100);

        TableColumn<ExplorerBean, String> c=new TableColumn<ExplorerBean, String>("Worker Name");//kuch bhi
        c.setCellValueFactory(new PropertyValueFactory<>("availableworkers"));
        c.setMinWidth(100);


        TableColumn<ExplorerBean, String> d=new TableColumn<ExplorerBean, String>("Mobile.no");//kuch bhi
        d.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
        d.setMinWidth(100);

        TableColumn<ExplorerBean, String> e=new TableColumn<ExplorerBean, String>("Dilevery Date");//kuch bhi
        e.setCellValueFactory(new PropertyValueFactory<>("dod"));
        e.setMinWidth(100);

        TableColumn<ExplorerBean, String> p=new TableColumn<ExplorerBean, String>("Quantity");//kuch bhi
        p.setCellValueFactory(new PropertyValueFactory<>("qty"));
        p.setMinWidth(100);

        TableColumn<ExplorerBean, String> f=new TableColumn<ExplorerBean, String>("Price Per Quantity");//kuch bhi
        f.setCellValueFactory(new PropertyValueFactory<>("priceperqty"));
        f.setMinWidth(100);

        TableColumn<ExplorerBean, String> g=new TableColumn<ExplorerBean, String>("Pic Path");//kuch bhi
        g.setCellValueFactory(new PropertyValueFactory<>("picpath"));
        g.setMinWidth(100);

        TableColumn<ExplorerBean, String> h=new TableColumn<ExplorerBean, String>("Toal Bill");//kuch bhi
        h.setCellValueFactory(new PropertyValueFactory<>("bill"));
        h.setMinWidth(100);

        TableColumn<ExplorerBean, String> i=new TableColumn<ExplorerBean, String>("Measurements");//kuch bhi
        i.setCellValueFactory(new PropertyValueFactory<>("measurements"));
        i.setMinWidth(100);

        TableColumn<ExplorerBean, String> j=new TableColumn<ExplorerBean, String>("Order Date");//kuch bhi
        j.setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        j.setMinWidth(100);

        TableColumn<ExplorerBean, String> k=new TableColumn<ExplorerBean, String>("Status");//kuch bhi
        k.setCellValueFactory(new PropertyValueFactory<>("selstatus"));
        k.setMinWidth(100);

        tblvieww.getColumns().addAll(a,b,c,d,e,p,f,g,h,i,j,k);
        tblvieww.setItems(getThisStatusRecords());


    }

    ObservableList<ExplorerBean> getThisStatusRecords() {
        ObservableList<ExplorerBean> ary = FXCollections.observableArrayList();

        try {
            String selectedWorker = cworker.getValue();
            String statusText = cstatus.getValue();

            int status = 0; // Initialize status to a default value

            switch (statusText) {
                case "Order Placed":
                    status = 1;
                    break;
                case "Received From Worker":
                    status = 2;
                    break;
                case "Orders Delivered":
                    status = 3;
                    break;
                default:
                    // Handle the case where statusText doesn't match any expected value
                    throw new IllegalArgumentException("Invalid status: " + statusText);
            }

            String query = "SELECT * FROM measurementpage WHERE selstatus = ?";
            if (selectedWorker != null && !selectedWorker.isEmpty()) {
                query += " AND workerass = ?";
            }

            stmt = con.prepareStatement(query);
            stmt.setInt(1, status);

            if (selectedWorker != null && !selectedWorker.isEmpty()) {
                stmt.setString(2, selectedWorker);
            }

            ResultSet allrecord = stmt.executeQuery();
            while (allrecord.next()) {
                int order_idd=allrecord.getInt("order_id");
                String dresss=allrecord.getString("dress");
                String available=allrecord.getString("availableworkers");
                String mob=allrecord.getString("mobileno");
                Date dodd=allrecord.getDate("dod");
                int qtyy=allrecord.getInt("qty");
                int ppq=allrecord.getInt("priceperqty");
                String picpathh=allrecord.getString("picpath");
                int billl=allrecord.getInt("bill");
                String measurement=allrecord.getString("measurements");
                Date orderdatee=allrecord.getDate("orderdate");
                String stat=allrecord.getString("selstatus");

                ary.add(new ExplorerBean(order_idd,dresss,available,mob,dodd,qtyy,ppq,picpathh,billl,measurement,orderdatee,stat));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Show a message to the user
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ary;
    }




    //This function will show all records of measurementpage table.
    @FXML
    void doshow_all_records(ActionEvent event) {

        tblvieww.getColumns().clear();

        TableColumn<ExplorerBean, String> a=new TableColumn<ExplorerBean, String>("order_Id");//kuch bhi
        a.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        a.setMinWidth(100);

        TableColumn<ExplorerBean, String> b=new TableColumn<ExplorerBean, String>("Dress");//kuch bhi
        b.setCellValueFactory(new PropertyValueFactory<>("dress"));
        b.setMinWidth(100);

        TableColumn<ExplorerBean, String> c=new TableColumn<ExplorerBean, String>("Worker Name");//kuch bhi
        c.setCellValueFactory(new PropertyValueFactory<>("availableworkers"));
        c.setMinWidth(100);


        TableColumn<ExplorerBean, String> d=new TableColumn<ExplorerBean, String>("Mobile.no");//kuch bhi
        d.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
        d.setMinWidth(100);

        TableColumn<ExplorerBean, String> e=new TableColumn<ExplorerBean, String>("Dilevery Date");//kuch bhi
        e.setCellValueFactory(new PropertyValueFactory<>("dod"));
        e.setMinWidth(100);

        TableColumn<ExplorerBean, String> p=new TableColumn<ExplorerBean, String>("Quantity");//kuch bhi
        p.setCellValueFactory(new PropertyValueFactory<>("qty"));
        p.setMinWidth(100);

        TableColumn<ExplorerBean, String> f=new TableColumn<ExplorerBean, String>("Price Per Quantity");//kuch bhi
        f.setCellValueFactory(new PropertyValueFactory<>("priceperqty"));
        f.setMinWidth(100);

        TableColumn<ExplorerBean, String> g=new TableColumn<ExplorerBean, String>("Pic Path");//kuch bhi
        g.setCellValueFactory(new PropertyValueFactory<>("picpath"));
        g.setMinWidth(100);

        TableColumn<ExplorerBean, String> h=new TableColumn<ExplorerBean, String>("Total Bill");//kuch bhi
        h.setCellValueFactory(new PropertyValueFactory<>("bill"));
        h.setMinWidth(100);

        TableColumn<ExplorerBean, String> i=new TableColumn<ExplorerBean, String>("Measurements");//kuch bhi
        i.setCellValueFactory(new PropertyValueFactory<>("measurements"));
        i.setMinWidth(100);

        TableColumn<ExplorerBean, String> j=new TableColumn<ExplorerBean, String>("Order Date");//kuch bhi
        j.setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        j.setMinWidth(100);

        TableColumn<ExplorerBean, String> k=new TableColumn<ExplorerBean, String>("Status");//kuch bhi
        k.setCellValueFactory(new PropertyValueFactory<>("selstatus"));
        k.setMinWidth(100);

        tblvieww.getColumns().addAll(a,b,c,d,e,p,f,g,h,i,j,k);
        tblvieww.setItems(getallRecords());

    }

    ObservableList<ExplorerBean> getallRecords() {
        ObservableList<ExplorerBean> ary= FXCollections.observableArrayList();

        try {

            stmt = con.prepareStatement("select * from measurementpage ");

            ResultSet allrecord= stmt.executeQuery();
            while(allrecord.next())
            {
                int order_idd=allrecord.getInt("order_id");
                String dresss=allrecord.getString("dress");
                String available=allrecord.getString("availableworkers");
                String mob=allrecord.getString("mobileno");
                Date dodd=allrecord.getDate("dod");
                int qtyy=allrecord.getInt("qty");
                int ppq=allrecord.getInt("priceperqty");
                String picpathh=allrecord.getString("picpath");
                int billl=allrecord.getInt("bill");
                String measurement=allrecord.getString("measurements");
                Date orderdatee=allrecord.getDate("orderdate");
                String stat=allrecord.getString("selstatus");

                ary.add(new ExplorerBean(order_idd,dresss,available,mob,dodd,qtyy,ppq,picpathh,billl,measurement,orderdatee,stat));

            }

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }

        return ary;
    }






    //This function is used to fill all workers name in worker combo-box.
    void FillWorkers() {
        try {

            stmt = con.prepareStatement("SELECT DISTINCT availableworkers FROM measurementpage ");


            ResultSet records = stmt.executeQuery();
            ObservableList<String> workersList = FXCollections.observableArrayList();
            while (records.next()) {

                String worker = records.getString("availableworkers");
                workersList.add(worker);

            }

            cworker.getItems().clear();
            cworker.getItems().addAll(workersList);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }



    public void writeExcel() throws Exception {
        Writer writer = null;
        try {
            File file = new File("measurements.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Order_ID,Dress,Availableworkers,Mobile.no,Date of Delivery,Quantity,Price Per Quantity,Pic Path,Bill , Measurements,Order Date,Status\n";
            writer.write(text);
            for (ExplorerBean p : tblvieww.getItems())
            {
                text = p.getOrder_id()+ "," + p.getDress()+ "," + p.getAvailableworkers() + p.getMobileno() + " , "+p.getDod()+"," + p.getQty()+"," + p.getPriceperqty()+"," + p.getPicpath()+","+p.getBill()+","+p.getMeasurements()+","+p.getOrderdate()+","+p.getSelstatus()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
            writer.close();
        }
    }

    @FXML
    void doexport(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Export to Excel");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to export the table to Excel?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                writeExcel();
                System.out.println("Data successfully exported to measurements.csv.");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An error occurred while exporting the data.");
            }
        }
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

        FillWorkers(); // step-1

        ObservableList<String> items = FXCollections.observableArrayList(
                "Order Placed", "Received From Worker", "Orders Delivered");
        cstatus.setItems(items); // step-2


        //step-3
        // Add a listener to clear the cPhone TextField when cStatus changes
        cstatus.valueProperty().addListener((observable, oldValue, newValue) -> {
            txtmobileno.clear();
        });
        cworker.valueProperty().addListener((observable, oldValue, newValue) -> {
            txtmobileno.clear();
        });



    }

}
