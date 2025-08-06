package com.example.group15chainmeatproducer.Sojib.User2;

import java.io.Serializable;

public class PackageOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    private String customerId, customerName, fullAddress, orderDate, phoneNumber, packageCount, totalWeight;

    public PackageOrder(String customerId, String customerName, String fullAddress, String orderDate, String phoneNumber, String packageCount, String totalWeight) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.fullAddress = fullAddress;
        this.orderDate = orderDate;
        this.phoneNumber = phoneNumber;
        this.packageCount = packageCount;
        this.totalWeight = totalWeight;
    }

    public String getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getFullAddress() { return fullAddress; }
    public String getOrderDate() { return orderDate; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPackageCount() { return packageCount; }
    public String getTotalWeight() { return totalWeight; }
}