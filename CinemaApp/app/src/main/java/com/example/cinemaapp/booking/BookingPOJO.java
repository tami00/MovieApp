package com.example.cinemaapp.booking;

import java.util.UUID;

public class BookingPOJO {
//    private String quantity;
//    private int totalCost;
    private String title;
    private String videoId;
    private String userID;
    private String image;
    private String bookingID = UUID.randomUUID().toString();
    private boolean confirmed;
//  date stuff


    public BookingPOJO() {
    }

    public BookingPOJO(String title, String videoId, String userID, String image,String bookingID, boolean confirmed) {
        this.title = title;
        this.videoId = videoId;
        this.userID = userID;
        this.image = image;
        this.bookingID = bookingID;
        this.confirmed = confirmed;
    }

//    public String getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(String quantity) {
//        this.quantity = quantity;
//    }

//    public int getTotalCost() {
//        return totalCost;
//    }
//
//    public void setTotalCost(int totalCost) {
//        this.totalCost = totalCost;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
