package com.example.a790232.travelexpertsandroid;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


// ***** TO DO *****
// How closely does this class have to match the one in the REST service?

public class Packag implements Serializable {

    // Private member variables
    private int packageId;
    private double pkgAgencyCommission;
    private double pkgBasePrice;
    private String pkgDesc;
    private Date pkgEndDate;
    private String pkgName;
    private Date pkgStartDate;

    // Constructors
    public Packag() {
    }

    public Packag(int packageId, double pkgAgencyCommission, double pkgBasePrice, String pkgDesc, Date pkgEndDate, String pkgName, Date pkgStartDate) {
        this.packageId = packageId;
        this.pkgAgencyCommission = pkgAgencyCommission;
        this.pkgBasePrice = pkgBasePrice;
        this.pkgDesc = pkgDesc;
        this.pkgEndDate = pkgEndDate;
        this.pkgName = pkgName;
        this.pkgStartDate = pkgStartDate;
    }

    // Getters and setters
    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public double getPkgAgencyCommission() {
        return pkgAgencyCommission;
    }

    public void setPkgAgencyCommission(double pkgAgencyCommission) {
        this.pkgAgencyCommission = pkgAgencyCommission;
    }

    public double getPkgBasePrice() {
        return pkgBasePrice;
    }

    public void setPkgBasePrice(double pkgBasePrice) {
        this.pkgBasePrice = pkgBasePrice;
    }

    public String getPkgDesc() {
        return pkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
    }

    public Date getPkgEndDate() {
        return pkgEndDate;
    }

    public void setPkgEndDate(Date pkgEndDate) {
        this.pkgEndDate = pkgEndDate;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public Date getPkgStartDate() {
        return pkgStartDate;
    }

    // The getDates() method returns the start and end dates of the package together
    // as a single string in the format "2018-01-15 to 2018-01-20"
    public String getDates() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(pkgStartDate) + " to " + df.format(pkgEndDate);
    }
}
