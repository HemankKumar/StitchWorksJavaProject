package com.example.java_project.Measurement_Explorer;

import java.util.Date;

public class ExplorerBean {

    //Fields we want to show in Table

    int order_id;
    String dress;
    String availableworkers;
    String mobileno;
    Date dod;
    int qty;
    int priceperqty;
    String picpath;
    int bill;
    String measurements;
    Date orderdate;
    String selstatus;



    //Getter and Setter corresponding to all fields

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getDress() {
        return dress;
    }

    public void setDress(String dress) {
        this.dress = dress;
    }

    public String getAvailableworkers() {
        return availableworkers;
    }

    public void setAvailableworkers(String dress) {
        this.availableworkers = availableworkers;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public Date getDod() {
        return dod;
    }

    public void setDod(Date dod) {
        this.dod = dod;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPriceperqty() {
        return priceperqty;
    }

    public void setPriceperqty(int priceperqty) {
        this.priceperqty = priceperqty;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public String getMeasurements() {
        return measurements;
    }

    public void setMeasurements(String measurements) {
        this.measurements = measurements;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getSelstatus() {
        return selstatus;
    }

    public void setSelstatus(String selstatus) {
        this.selstatus = selstatus;
    }

   //Parametrized Constructor
//
//    public ExplorerBean(int order_id, String dress, String mobileno, Date dod, int qty, int priceperqty, String picpath, int bill, String measurements, Date orderdate, String selstatus) {
//        this.order_id = order_id;
//        this.dress = dress;
//        this.mobileno = mobileno;
//        this.dod = dod;
//        this.qty = qty;
//        this.priceperqty = priceperqty;
//        this.picpath = picpath;
//        this.bill = bill;
//        this.measurements = measurements;
//        this.orderdate = orderdate;
//        this.selstatus = selstatus;
//    }


    public ExplorerBean(int order_id, String dress, String availableworkers, String mobileno, Date dod, int qty, int priceperqty, String picpath, int bill, String measurements, Date orderdate, String selstatus) {
        this.order_id = order_id;
        this.dress = dress;
        this.availableworkers = availableworkers;
        this.mobileno = mobileno;
        this.dod = dod;
        this.qty = qty;
        this.priceperqty = priceperqty;
        this.picpath = picpath;
        this.bill = bill;
        this.measurements = measurements;
        this.orderdate = orderdate;
        this.selstatus = selstatus;
    }

    // Default Constructor.
    public ExplorerBean() {
    }
}
