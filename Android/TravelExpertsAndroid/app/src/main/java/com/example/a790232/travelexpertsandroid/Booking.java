/*
Author: Sunghyun Lee
Created: 2018-10-16

 */

package com.example.a790232.travelexpertsandroid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Booking
{
    private int customerId;

    private String classId;

    private int packageId;

    private String tripTypeId;

    private int travelerCount;

    private String feeId;

    private String destination;

    private String description;

    private Date bookingDate;
    private Packag packag;

    public Booking(int customerId, String classId,  String tripTypeId, int travelerCount,
                   String feeId, String destination, String description) {
        super();
        this.customerId = customerId;
        this.classId = classId;
        this.tripTypeId = tripTypeId;
        this.travelerCount = travelerCount;
        this.feeId = feeId;
        this.destination = destination;
        this.description = description;

    }


    public int getCustomerId() {
        return customerId;
    }


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Packag getPackag()
    {
        return packag;
    }

    public void setPackag(Packag packag)
    {
        this.packag = packag;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    public int getTravelerCount() {
        return travelerCount;
    }

    public void setTravelerCount(int travelerCount) {
        this.travelerCount = travelerCount;
    }

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBookingDate()
    {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate)
    {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //to convert Date to String, use format method of SimpleDateFormat class.
        String strDate = dateFormat.format(getBookingDate());


        return getPackag().getPkgName() + " booked on "+strDate +" for "+travelerCount+" people.";

    }
}
