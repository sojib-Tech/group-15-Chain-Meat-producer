package com.example.group15chainmeatproducer.SakibOme.LogisticsManager;

import java.time.LocalDate;

public class TransportationCostRecord {
    private String route;
    private String transportMode;
    private LocalDate costMonth;
    private double totalCost;
    private double costPerShipment;
    private double variancePercent;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public LocalDate getCostMonth() {
        return costMonth;
    }

    public void setCostMonth(LocalDate costMonth) {
        this.costMonth = costMonth;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getCostPerShipment() {
        return costPerShipment;
    }

    public void setCostPerShipment(double costPerShipment) {
        this.costPerShipment = costPerShipment;
    }

    public double getVariancePercent() {
        return variancePercent;
    }

    public void setVariancePercent(double variancePercent) {
        this.variancePercent = variancePercent;
    }
}
