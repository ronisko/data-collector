package org.warehouse.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryDto implements ObservableDto {

    private static final String[] COLUMNS = new String[]{"SHOP NAME", "CATEGORY NAME", "SALE"};

    private String shopName;

    private String categoryName;

    private double sale;

    public CategoryDto(String shopName, String categoryName, double sale) {
        this.shopName = shopName;
        this.categoryName = categoryName;
        this.sale = sale;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    @Override
    public ObservableList<String> toObservableList() {
        return FXCollections.observableArrayList(shopName, categoryName, Double.toString(sale));
    }

    @Override
    public String[] getColumns() {
        return COLUMNS;
    }
}
