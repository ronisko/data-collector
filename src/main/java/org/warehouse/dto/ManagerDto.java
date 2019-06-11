package org.warehouse.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManagerDto implements ObservableDto {

    private static final String[] COLUMNS = new String[]{"ID", "FIRSTNAME", "LASTNAME", "SUM"};

    private int id;

    private String firstname;

    private String lastname;

    private int sum;

    public ManagerDto(int id, String firstname, String lastname, int sum) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public ObservableList<String> toObservableList() {
        return FXCollections.observableArrayList(Integer.toString(id), firstname, lastname, Integer.toString(sum));
    }

    @Override
    public String[] getColumns() {
        return COLUMNS;
    }
}
