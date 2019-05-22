package br.com.watcher.dto;

import java.math.BigDecimal;

public class SaleItem {

    private String id;
    private int quantity;
    private BigDecimal price;

    public static final class Builder {
        private SaleItem saleItem;

        private Builder() {
            saleItem = new SaleItem();
        }

        public static Builder of() {
            return new Builder();
        }

        public Builder id(String id) {
            saleItem.setId(id);
            return this;
        }

        public Builder quantity(String quantity) {
            saleItem.setQuantity(Integer.parseInt(quantity.trim()));
            return this;
        }

        public Builder price(String price) {
            saleItem.setPrice(new BigDecimal(price.trim()));
            return this;
        }

        public SaleItem build() {
            return saleItem;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
