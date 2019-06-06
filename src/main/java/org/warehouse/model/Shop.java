package org.warehouse.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SHOP_TAB")
public class Shop {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    @MapsId("locationId")
    private Location location;

    @ManyToOne
    @JoinColumn(nullable = false)
    @MapsId("managerId")
    private Manager manager;

    public Shop() {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return id == shop.id &&
                Objects.equals(name, shop.name) &&
                Objects.equals(location, shop.location) &&
                Objects.equals(manager, shop.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, manager);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", locationId=" + location.getId() +
                ", managerId=" + manager.getId() +
                '}';
    }
}
