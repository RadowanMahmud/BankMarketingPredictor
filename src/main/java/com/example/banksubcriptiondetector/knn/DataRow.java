package com.example.banksubcriptiondetector.knn;
import java.util.List;

public class DataRow {

    String cls;
    double age,job,martial,education,defaultV,housing,loan,day_of_week,previous,var_rate,price_idx,conf_idx,euribor3m,nr_employeed;

    public DataRow(String[] split)
    {
        this.age=(Double.parseDouble(split[0]))/10;
        this.job=Double.parseDouble(split[1]);
        this.martial=Double.parseDouble(split[2]);
        this.education=Double.parseDouble(split[3]);
        this.defaultV=(Double.parseDouble(split[4]));
        this.housing=Double.parseDouble(split[5]);
        this.loan=Double.parseDouble(split[6]);
        this.day_of_week=Double.parseDouble(split[7]);
        this.previous=Double.parseDouble(split[8]);
        //this.var_rate=Double.parseDouble(split[9]);
        this.price_idx=Double.parseDouble(split[9])/100;
        this.conf_idx=Double.parseDouble(split[10])*(-1);
        //this.euribor3m=Double.parseDouble(split[12]);
        //this.nr_employeed=Double.parseDouble(split[13])/1000;
        this.cls=split[split.length-1];
    }

    public double getAge() {
        return this.age;
    }

    public double getJob() {
        return this.job;
    }

    public double getMarital() {
        return this.martial;
    }

    public double getEducation() {
        return this.education;
    }

    public double getDefaultV() {return this.defaultV; }

    public double getHousing() {
        return this.housing;
    }

    public double getLoan() {
        return this.loan;
    }

    public double getDay_of_week() { return day_of_week; }

    public double getPrevious() { return previous; }

    // public double getVar_rate() { return var_rate; }

    public double getPrice_idx() { return price_idx; }

    public double getConf_idx() { return conf_idx; }

//    public double getEuribor3m() { return euribor3m; }
//
//    public double getNr_employeed() { return nr_employeed; }

    public String getCls(){
        return this.cls;
    }

}

