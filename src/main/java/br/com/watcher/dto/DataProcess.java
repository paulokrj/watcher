package br.com.watcher.dto;

import java.util.ArrayList;
import java.util.List;

public class DataProcess {

    private List<Salesman> salesmanList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    private List<Sale> saleList = new ArrayList<>();

    public List<Salesman> getSalesmanList() {
        return salesmanList;
    }

    public DataProcess salesmanList(List<Salesman> salesmanList) {
        this.salesmanList = salesmanList;
        return this;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public DataProcess customerList(List<Customer> customerList) {
        this.customerList = customerList;
        return this;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public DataProcess saleList(List<Sale> saleList) {
        this.saleList = saleList;
        return this;
    }
}
