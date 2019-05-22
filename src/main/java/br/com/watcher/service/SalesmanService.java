package br.com.watcher.service;

import br.com.watcher.dto.DataProcess;
import br.com.watcher.dto.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class SalesmanService extends BaseService {

    private final Logger logger = LoggerFactory.getLogger(SalesmanService.class);

    @Override
    void doWork(String line, DataProcess dataProcess) {
        List<String> data = this.splitLine(line, 4);

        if (data.isEmpty()) {
            this.logger.error("Error while trying to split line.");
            return;
        }

        Salesman salesman = Salesman.Builder.of()
                .code(data.get(0))
                .document(data.get(1))
                .name(data.get(2))
                .salary(data.get(3)).build();

        this.logger.info(salesman.toString());
        dataProcess.getSalesmanList().add(salesman);
    }
}
