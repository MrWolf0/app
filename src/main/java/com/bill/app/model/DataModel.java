package com.bill.app.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class DataModel {
    private final StringProperty vendorName;
    private final StringProperty productName;
    private final StringProperty vendorAddress;
    private final FloatProperty VATNumber;
    private final FloatProperty amount;
    private final FloatProperty price;
    private final StringProperty VATAmount;
    private final FloatProperty totalPriceWithoutVat;
    private final FloatProperty VATPrice;
    private final FloatProperty totalPriceWithVat;
    private final FloatProperty finalPrice;
    private final FloatProperty totalVat;
    private String date;

    public DataModel() {
        this.productName = new SimpleStringProperty();
        this.vendorName = new SimpleStringProperty();
        this.vendorAddress = new SimpleStringProperty();
        this.VATNumber = new SimpleFloatProperty();
        this.amount = new SimpleFloatProperty();
        this.price = new SimpleFloatProperty();
        this.VATAmount = new SimpleStringProperty();
        this.totalPriceWithoutVat = new SimpleFloatProperty();
        this.VATPrice = new SimpleFloatProperty();
        this.totalPriceWithVat = new SimpleFloatProperty();
        this.finalPrice = new SimpleFloatProperty();
        this.totalVat = new SimpleFloatProperty();
        VATPrice.bind(this.price.multiply(0.15f));
        totalPriceWithVat.bind(this.price.add(VATPrice));
        finalPrice.bind(this.totalPriceWithVat.multiply(amount));
        totalVat.bind(this.VATPrice.multiply(amount));
        this.date = getDate();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    public StringProperty VATAmountProperty() {
        return VATAmount;
    }

    public void setVATAmount(String VATAmount) {
        this.VATAmount.set(VATAmount);
    }

    public FloatProperty totalPriceWithoutVat() {
        return totalPriceWithoutVat;
    }

    public void setTotalPriceWithoutVat(float totalPriceWithoutVat) {
        this.totalPriceWithoutVat.set(totalPriceWithoutVat);
    }

    public FloatProperty VATPriceProperty() {
        return totalVat;
    }

    public FloatProperty getTotalPriceWithVatProperty() {
        return finalPrice;
    }

    public FloatProperty oneVATPriceProperty() {
        return VATPrice;
    }

}