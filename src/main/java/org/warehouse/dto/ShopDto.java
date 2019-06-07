package org.warehouse.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShopDto implements ObservableDto {

    private static final String[] COL_NAMES = new String[]{"ID", "NAME", "LOCATION_ID", "MANAGER_ID"};

    private int id;

    private String name;

    private int locationId;

    private int managerId;

    public ShopDto(int id, String name, int locationId, int managerId) {
        this.id = id;
        this.name = name;
        this.locationId = locationId;
        this.managerId = managerId;
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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public ObservableList<String> toObservableList() {
        return FXCollections.observableArrayList(Integer.toString(id), name, Integer.toString(locationId), Integer.toString(managerId));
    }

    @Override
    public String[] getColumns() {
        return COL_NAMES;
    }
}
