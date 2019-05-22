package br.com.watcher.dto;

import java.math.BigDecimal;
import java.util.List;

public class Sale {

    private String code;
    private String saleId;
    private String salesmanName;
    private List<SaleItem> items;

    public static final class Builder {
        private Sale sale;

        private Builder() {
            sale = new Sale();
        }

        public static Builder of() {
            return new Builder();
        }

        public Builder code(String code) {
            sale.setCode(code);
            return this;
        }

        public Builder saleId(String saleId) {
            sale.setSaleId(saleId);
            return this;
        }

        public Builder salesmanName(String salesmanName) {
            sale.setSalesmanName(salesmanName);
            return this;
        }

        public Builder items(List<SaleItem> items) {
            sale.setItems(items);
            return this;
        }

        public Sale build() {
            return sale;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        if (items.isEmpty())
            return BigDecimal.valueOf(0);

        return items.stream().map(SaleItem::getPrice).reduce(BigDecimal::add).get();
    }

    @Override
    public String toString() {
        return "Sale{" +
                "code='" + code + '\'' +
                ", saleId='" + saleId + '\'' +
                ", salesmanName='" + salesmanName + '\'' +
                ", items=" + items +
                '}';
    }
}
