package org.warehouse.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "LOCATION_TAB")
public class Location {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    private String district;

    private String town;

    private String street;

    public Location() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id &&
                Objects.equals(district, location.district) &&
                Objects.equals(town, location.town) &&
                Objects.equals(street, location.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, district, town, street);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", town='" + town + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
