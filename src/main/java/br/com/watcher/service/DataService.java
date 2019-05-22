package br.com.watcher.service;

import br.com.watcher.dto.DataProcess;
import br.com.watcher.dto.OutputFile;
import br.com.watcher.dto.Sale;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataService {

    private SalesmanService salesmanService;
    private CustomerService customerService;
    private SaleService saleService;

    public DataService(SalesmanService salesmanService, CustomerService customerService, SaleService saleService) {
        this.salesmanService = salesmanService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    void factory(String line, DataProcess dataProcess) {
        if (line.startsWith("001"))
            salesmanService.doWork(line, dataProcess);
        else if (line.startsWith("002"))
            customerService.doWork(line, dataProcess);
        else if (line.startsWith("003"))
            saleService.doWork(line, dataProcess);
    }

    OutputFile output(DataProcess dataProcess) {
        int totalCustomer = dataProcess.getCustomerList().size();
        int totalSalesman = dataProcess.getSalesmanList().size();
        String mostExpensiveSale = this.mostExpensiveSale(dataProcess);
        String salesman = this.worstSalesman(dataProcess);
        return OutputFile.Builder.of().totalOfCustomer(totalCustomer).totalOfSalesman(totalSalesman).mostExpensiveSale(mostExpensiveSale).worstSalesman(salesman).build();

    }

    private String mostExpensiveSale(DataProcess dataProcess) {
        Optional<Sale> optSale = dataProcess.getSaleList().stream().max(Comparator.comparing(Sale::getTotalPrice));
        return optSale.isPresent() ? optSale.get().getSaleId() : "";
    }

    private String worstSalesman(DataProcess dataProcess) {
        Map<String, BigDecimal> result = dataProcess.getSaleList().stream().collect(Collectors.groupingBy(Sale::getSalesmanName, Collectors.reducing(BigDecimal.ZERO, Sale::getTotalPrice, BigDecimal::add)));
        Optional<Map.Entry<String, BigDecimal>> optWorstSalesman = result.entrySet().stream().min(Comparator.comparing(Map.Entry::getValue));
        return optWorstSalesman.isPresent() ? optWorstSalesman.get().getKey() : "";
    }


}
