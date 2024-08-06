package com.example.java_project.Tableview_of_workers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class tableview {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo_spz;

    @FXML
    private TableView<ProfileBean> tablevieww;

    @FXML
    void do_export_to_Excel(ActionEvent event) {

        try {
            writeExcel();
            //txtPname.setText("Exported to excel..");
            showMyMsg("Exported");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void writeExcel() throws Exception {
        Writer writer = null;
        try {
            File file = new File("AllUsers.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="WorkerName,Mobileno,Specialization\n";
            writer.write(text);
            for (ProfileBean p : tablevieww.getItems())
            {
                text = p.getWorkername()+ "," + p.getMobileno()+ "," + p.getSelspz()+"\n";
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
    void do_fetch_all_records(ActionEvent event) {

        TableColumn<ProfileBean, String> uidC=new TableColumn<ProfileBean, String>("Worker Name");//kuch bhi
        uidC.setCellValueFactory(new PropertyValueFactory<>("workername"));
        uidC.setMinWidth(100);


        TableColumn<ProfileBean, String> Cdt=new TableColumn<ProfileBean, String>("Mobileno");//kuch bhi
        Cdt.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
        Cdt.setMinWidth(100);


        TableColumn<ProfileBean, String> Cage=new TableColumn<ProfileBean, String>("Specialization");//kuch bhi
        Cage.setCellValueFactory(new PropertyValueFactory<>("selspz"));
        Cage.setMinWidth(100);

        tablevieww.getColumns().addAll(uidC,Cage,Cdt);
        tablevieww.setItems(getRecords());

    }

    PreparedStatement stmt;
    ObservableList<ProfileBean> getRecords()
    {
        ObservableList<ProfileBean> ary= FXCollections.observableArrayList();

        try {
            stmt = con.prepareStatement("select * from workerspage");
            ResultSet records= stmt.executeQuery();
            while(records.next())
            {
                String workernames=records.getString("workername");//col name

                String mobilenos=records.getString("mobileno");//col name

                String selspzs=records.getString("selspz");//col name

                ary.add(new ProfileBean(workernames,mobilenos,selspzs) );
                System.out.println(workernames+"  "+mobilenos+"  "+selspzs);

            }

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        System.out.println(ary.size());
        return ary;
    }


    @FXML
    void do_change_tableview(ActionEvent event) {

        String selectedspz=combo_spz.getValue();

      ; ObservableList <ProfileBean> workerslist=FXCollections.observableArrayList();

        String query="Select * from workerspage where selspz LIKE ?";

        try(PreparedStatement stmt=con.prepareStatement(query)){

            stmt.setString(1,"%" +selectedspz+ "%");
            ResultSet rs= stmt.executeQuery();

            while(rs.next()){
                workerslist.add(new ProfileBean(

                        rs.getString("workername"),
                        rs.getString("mobileno"),
                        rs.getString("selspz")

                ));

            }

            tablevieww.setItems(workerslist);
            getRecords();

        }
        catch(Exception ex){
            ex.printStackTrace();
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

        //getRecords();

        combo_spz.getItems().addAll("Pent", "Shirt", "Coat" ,"Woolen clothes","summer clothes","trousers","shorts");

    }

}
