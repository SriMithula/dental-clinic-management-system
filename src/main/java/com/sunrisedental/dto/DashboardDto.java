package com.sunrisedental.dto;

public class DashboardDto {

    private int totalAppointments;
    private int totalPatients;
    private int todayVisits;
    private double revenue;

    public int getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(int totalAppointments) {
        this.totalAppointments = totalAppointments;
    }

    public int getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(int totalPatients) {
        this.totalPatients = totalPatients;
    }

    public int getTodayVisits() {
        return todayVisits;
    }

    public void setTodayVisits(int todayVisits) {
        this.todayVisits = todayVisits;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}