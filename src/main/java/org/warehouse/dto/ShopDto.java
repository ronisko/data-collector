package org.warehouse.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class ShopDto {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopDto shopDto = (ShopDto) o;
        return id == shopDto.id &&
                locationId == shopDto.locationId &&
                managerId == shopDto.managerId &&
                Objects.equals(name, shopDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, locationId, managerId);
    }

    @Override
    public String toString() {
        return "ShopDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", locationId=" + locationId +
                ", managerId=" + managerId +
                '}';
    }

    public ObservableList<String> toObservableList() {
        return FXCollections.observableArrayList(Integer.toString(id), name, Integer.toString(locationId), Integer.toString(managerId));
    }
}
