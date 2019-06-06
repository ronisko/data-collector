package org.warehouse.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SALES_TAB")
public class Sales {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SalesId id = new SalesId();

    @ManyToOne
    @JoinColumn(nullable = false)
    @MapsId("transaction_id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(nullable = false)
    @MapsId("product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(nullable = false)
    @MapsId("shop_id")
    private Shop shop;

    private double sales;

    private double revenue;

    private int quantity;

    public Sales() {
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sales sales1 = (Sales) o;
        return Double.compare(sales1.sales, sales) == 0 &&
                Double.compare(sales1.revenue, revenue) == 0 &&
                quantity == sales1.quantity &&
                Objects.equals(transaction, sales1.transaction) &&
                Objects.equals(product, sales1.product) &&
                Objects.equals(shop, sales1.shop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaction, product, shop, sales, revenue, quantity);
    }

    @Override
    public String toString() {
        return "Sales{" +
                "transactionId=" + transaction.getId() +
                ", productId=" + product.getId() +
                ", shopId=" + shop.getId() +
                ", sales=" + sales +
                ", revenue=" + revenue +
                ", quantity=" + quantity +
                '}';
    }
}
