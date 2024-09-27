package com.bill.app.model;

import javafx.beans.property.*;

public class DataModel {
    private final StringProperty vendorName;
    private final StringProperty productName;
    private final StringProperty vendorAddress;
    private final FloatProperty VATNumber;
    private final FloatProperty amount;
    private final FloatProperty price;
    private final FloatProperty VATAmount;
    private final FloatProperty totalPrice;

    public DataModel() {
        this.productName = new SimpleStringProperty();
        this.vendorName = new SimpleStringProperty();
        this.vendorAddress = new SimpleStringProperty();
        this.VATNumber = new SimpleFloatProperty();
        this.amount = new SimpleFloatProperty();
        this.price = new SimpleFloatProperty();
        this.VATAmount = new SimpleFloatProperty();
        this.totalPrice = new SimpleFloatProperty();
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

    public FloatProperty VATNumberProperty() {
        return VATNumber;
    }

    public void setVATNumber(float VATNumber) {
        this.VATNumber.set(VATNumber);
    }

    public FloatProperty amountProperty() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount.set(amount);
    }

    public FloatProperty priceProperty() {
        return price;
    }

    public void setPrice(float price) {
        this.price.set(price);
    }

    public FloatProperty VATAmountProperty() {
        return VATAmount;
    }

    public void setVATAmount(float VATAmount) {
        this.VATAmount.set(VATAmount);
    }

    public FloatProperty totalPriceProperty() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice.set(totalPrice);
    }
}
