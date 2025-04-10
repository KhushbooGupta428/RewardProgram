package com.customerreward.dto;


/**
 * Model representing reward points for a customer in a specific month.
 */


public class RewardPoints {

    private String month;
    private int points;

    public RewardPoints() {
    }
    public RewardPoints(String month, int points) {
        this.month = month;
        this.points = points;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
