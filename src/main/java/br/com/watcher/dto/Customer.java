package br.com.watcher.dto;

public class Customer {

    private String code;
    private String document;
    private String name;
    private String businessArea;

    public static final class Builder {
        private Customer customer;

        private Builder() {
            customer = new Customer();
        }

        public static Builder of() {
            return new Builder();
        }

        public Builder code(String code) {
            customer.setCode(code);
            return this;
        }

        public Builder document(String document) {
            customer.setDocument(document);
            return this;
        }

        public Builder name(String name) {
            customer.setName(name);
            return this;
        }

        public Builder businessArea(String businessArea) {
            customer.setBusinessArea(businessArea);
            return this;
        }

        public Customer build() {
            return customer;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "code='" + code + '\'' +
                ", document='" + document + '\'' +
                ", name='" + name + '\'' +
                ", businessArea='" + businessArea + '\'' +
                '}';
    }
}
