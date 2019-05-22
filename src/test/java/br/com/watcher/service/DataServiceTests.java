package br.com.watcher.service;

import br.com.watcher.dto.DataProcess;
import br.com.watcher.dto.OutputFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DataServiceTests {

    @InjectMocks
    private CustomerService customerService;

    @InjectMocks
    private SalesmanService salesmanService;

    @InjectMocks
    private SaleService saleService;

    @InjectMocks
    private DataService dataService;

    @Test
    public void outputTest() {
        DataProcess dataProcess = new DataProcess();

        salesmanService.doWork("001ç1234567891234çPedro Mendonçaç50000", dataProcess);
        salesmanService.doWork("001ç3245678865434çPaulo Kiefferç40000.99", dataProcess);

        customerService.doWork("002ç2345675434544345çJose da ÇilvaçRural", dataProcess);
        customerService.doWork("002ç2345675433444345çEduardo PereiraçRural", dataProcess);

        saleService.doWork("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro", dataProcess);
        saleService.doWork("003ç08ç[1-34-150,2-33-50.50,3-40-10.10]çPaulo Mendonça", dataProcess);


        OutputFile outputFile = dataService.output(dataProcess);

        assertAll(
                () -> assertEquals(outputFile.getTotalOfClient(),2),
                () -> assertEquals(outputFile.getTotalOfSalesman(),2),
                () -> assertEquals(outputFile.getMostExpensiveSale(),"08"),
                () -> assertEquals(outputFile.getWorstSalesman(),"Pedro")
        );
    }

}
