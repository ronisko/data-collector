package org.warehouse.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SalesId implements Serializable {

    private int transaction_id;

    private int product_id;

    private int shop_id;

    public SalesId() {
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesId salesId = (SalesId) o;
        return transaction_id == salesId.transaction_id &&
                product_id == salesId.product_id &&
                shop_id == salesId.shop_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaction_id, product_id, shop_id);
    }

    @Override
    public String toString() {
        return "SalesId{" +
                "transaction_id=" + transaction_id +
                ", product_id=" + product_id +
                ", shop_id=" + shop_id +
                '}';
    }
}
