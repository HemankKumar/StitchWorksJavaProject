package com.example.java_project.Tableview_of_workers;


public class ProfileBean {

    String workername;

    String mobileno;

    String selspz;

    public String getWorkername() {
        return workername;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getSelspz() {
        return selspz;
    }

    public void setWorkername(String workername) {
        this.workername = workername;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public void setSelspz(String selspz) {
        this.selspz = selspz;
    }

    public ProfileBean(String workername, String mobileno, String selspz) {
        this.workername = workername;
        this.mobileno = mobileno;
        this.selspz = selspz;
    }

    public ProfileBean() {
    }
}

/*
Q- what is Bean class?
Ans-A bean class is a class file which contain only those properties which we want to see in table and for every property
Getter ,setter , paramertized constructor and default constructor is present.
*/