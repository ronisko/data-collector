package org.warehouse.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductDto implements ObservableDto {

    private int id;

    private String name;

    private String categoryName;

    private int quantitySum;

    public ProductDto(int id, String name, String categoryName, int quantitySum) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
        this.quantitySum = quantitySum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQuantitySum() {
        return quantitySum;
    }

    public void setQuantitySum(int quantitySum) {
        this.quantitySum = quantitySum;
    }

    public ObservableList<String> toObservableList() {
        return FXCollections.observableArrayList(Integer.toString(id), name, categoryName, Integer.toString(quantitySum));
    }
}
