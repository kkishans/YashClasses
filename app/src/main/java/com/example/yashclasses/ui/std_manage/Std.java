package com.example.yashclasses.ui.std_manage;



public class Std {

    private int stdid;

    private  String Std;

    private  String medium;

    private  double fees;

    public Std(String std, String medium, double fees) {
        Std = std;
        this.medium = medium;
        this.fees = fees;
    }

    public void setStdid(int stdid) {
        this.stdid = stdid;
    }

    public int getStdid() {
        return stdid;
    }

    public String getStd() {
        return Std;
    }

    public String getMedium() {
        return medium;
    }

    public double getFees() {
        return fees;
    }
}
