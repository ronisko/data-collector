package org.warehouse.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HalfSalesDto implements ObservableDto {

    private static final String[] COLUMNS = new String[]{"SHOP_NAME", "SALES FIRST HALF", "SALES SECOND HALF"};


    private String shopName;

    private double firstHalf;

    private double secondHalf;

    public HalfSalesDto(String shopName, double firstHalf, double secondHalf) {
        this.shopName = shopName;
        this.firstHalf = firstHalf;
        this.secondHalf = secondHalf;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getFirstHalf() {
        return firstHalf;
    }

    public void setFirstHalf(double firstHalf) {
        this.firstHalf = firstHalf;
    }

    public double getSecondHalf() {
        return secondHalf;
    }

    public void setSecondHalf(double secondHalf) {
        this.secondHalf = secondHalf;
    }

    @Override
    public ObservableList<String> toObservableList() {
        return FXCollections.observableArrayList(shopName, Double.toString(firstHalf), Double.toString(secondHalf));
    }

    @Override
    public String[] getColumns() {
        return COLUMNS;
    }
}
