package br.com.watcher.service;

import br.com.watcher.dto.DataProcess;
import br.com.watcher.dto.Sale;
import br.com.watcher.dto.SaleItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
class SaleService extends BaseService{

    private final Logger logger = LoggerFactory.getLogger(SaleService.class);

    @Override
    void doWork(String line, DataProcess dataProcess) {
        List<String> data = this.splitLine(line, 4);

        if (data.isEmpty()) {
            this.logger.error("Error when try to split line.");
            return;
        }

        List<String> items = Arrays.asList(data.get(2).replace("[","").replace("]", "").split(","));
        Sale sale = Sale.Builder.of()
                .code(data.get(0))
                .saleId(data.get(1))
                .salesmanName(data.get(3))
                .items(workItems(items))
                .build();

        this.logger.info(sale.toString());
        dataProcess.getSaleList().add(sale);
    }

    private List<SaleItem> workItems(List<String> items) {
        return items.stream().map(this::workItem).collect(Collectors.toList());
    }

    private SaleItem workItem(String line) {
        List<String> split = Arrays.asList(line.split("-"));
        return SaleItem.Builder.of().id(split.get(0)).quantity(split.get(1)).price(split.get(2)).build();
    }

}
