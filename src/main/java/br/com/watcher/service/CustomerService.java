package br.com.watcher.service;

import br.com.watcher.dto.Customer;
import br.com.watcher.dto.DataProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
class CustomerService extends BaseService {

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Override
    void doWork(String line, DataProcess dataProcess) {
        List<String> data = this.splitLine(line, 4);

        if (data.isEmpty()) {
            this.logger.error("Error when try to split line.");
            return;
        }

        Customer customer = Customer.Builder.of()
                .code(data.get(0))
                .document(data.get(1))
                .name(data.get(2))
                .businessArea(data.get(3)).build();

        this.logger.info(customer.toString());
        dataProcess.getCustomerList().add(customer);
    }

}
