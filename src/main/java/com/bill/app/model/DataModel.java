package com.bill.app.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DataModel {
    private final StringProperty vendorName;
    private final StringProperty productName;
    private final StringProperty vendorAddress;
    private final StringProperty VATNumber;
    private final StringProperty amount;
    private final StringProperty price;
    private final StringProperty VATAmount;
    private final StringProperty totalPrice;

    public DataModel() {
        this.productName = new SimpleStringProperty();
        this.vendorName = new SimpleStringProperty();
        this.vendorAddress = new SimpleStringProperty();
        this.VATNumber = new SimpleStringProperty();
        this.amount = new SimpleStringProperty();
        this.price = new SimpleStringProperty();
        this.VATAmount = new SimpleStringProperty();
        this.totalPrice = new SimpleStringProperty();
    }

    // Getters and setters for each property
    public StringProperty vendorNameProperty() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName.set(vendorName);
    }


    public StringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }



    public StringProperty vendorAddressProperty() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress.set(vendorAddress);
    }

    public StringProperty VATNumberProperty() {
        return VATNumber;
    }

    public void setVATNumber(String VATNumber) {
        this.VATNumber.set(VATNumber);
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public StringProperty VATAmountProperty() {
        return VATAmount;
    }

    public void setVATAmount(String VATAmount) {
        this.VATAmount.set(VATAmount);
    }

    public StringProperty totalPriceProperty() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice.set(totalPrice);
    }
}
