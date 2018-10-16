/*
    Packag.java
    Corinne Mullan
    October 9, 2018

    Initial version created, based on the JPA Packag class in the RESTful web service.
    Note that "Package" is a reserved word, so the class has been named "Packag" instead.
 */

package com.example.a790232.travelexpertsandroid;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Packag implements Serializable {

    // Private member variables
    private int packageId;
    private BigDecimal pkgAgencyCommission;
    private BigDecimal pkgBasePrice;
    private String pkgDesc;
    private Date pkgEndDate;
    private String pkgName;
    private Date pkgStartDate;
    private String pkgImageFile;

    // Constructors
    public Packag() {
    }

    public Packag(int packageId, BigDecimal pkgAgencyCommission, BigDecimal pkgBasePrice, String pkgDesc, Date pkgEndDate, String pkgName, Date pkgStartDate, String pkgImageFile) {
        this.packageId = packageId;
        this.pkgAgencyCommission = pkgAgencyCommission;
        this.pkgBasePrice = pkgBasePrice;
        this.pkgDesc = pkgDesc;
        this.pkgEndDate = pkgEndDate;
        this.pkgName = pkgName;
        this.pkgStartDate = pkgStartDate;
        this.pkgImageFile = pkgImageFile;
    }

    // Getters and setters
    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public BigDecimal getPkgAgencyCommission() {
        return pkgAgencyCommission;
    }

    public void setPkgAgencyCommission(BigDecimal pkgAgencyCommission) {
        this.pkgAgencyCommission = pkgAgencyCommission;
    }

    public BigDecimal getPkgBasePrice() {
        return pkgBasePrice;
    }

    public void setPkgBasePrice(BigDecimal pkgBasePrice) {
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

    public String getPkgImageFile() {
        return pkgImageFile;
    }

    public void setPkgImageFile(String pkgImageFile) {
        this.pkgImageFile = pkgImageFile;
    }


    // The getDates() method returns the start and end dates of the package together
    // as a single string in the format "2018-01-15 to 2018-01-20"
    public String getDates() {
        DateFormat df = new SimpleDateFormat("MMM d, yyyy");
        return df.format(pkgStartDate) + " to " + df.format(pkgEndDate);
    }
}
