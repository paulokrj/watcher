package br.com.watcher.service;

import br.com.watcher.dto.DataProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTests {

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void splitDefaultSuccessTest() {
        String line = "002ç2345675434544345çJose da SilvaçRural";

        List<String> result = this.customerService.splitLine(line, 4);

        assertAll(
                () -> assertEquals(result.size(), 4),
                () -> assertEquals(result.get(0), "002"),
                () -> assertEquals(result.get(1), "2345675434544345"),
                () -> assertEquals(result.get(2), "Jose da Silva"),
                () -> assertEquals(result.get(3), "Rural")
        );
    }

    @Test
    public void splitSuccessTest() {
        String line = "002ç2345675434544345çJose MendonçaçRural";

        List<String> result = this.customerService.splitLine(line, 4);

        assertAll(
                () -> assertEquals(result.size(), 4),
                () -> assertEquals(result.get(0), "002"),
                () -> assertEquals(result.get(1), "2345675434544345"),
                () -> assertEquals(result.get(2), "Jose Mendonça"),
                () -> assertEquals(result.get(3), "Rural")
        );
    }

    @Test
    public void splitFailedTest() {
        String line = "002ç2345675434544345çJose";
        List<String> result = this.customerService.splitLine(line, 4);
        assertTrue(result.isEmpty());
    }

    @Test
    public void doWorkSuccessTest() {
        String line = "002ç2345675434544345çJose MendonçaçRural";
        DataProcess dataProcess = new DataProcess();

        this.customerService.doWork(line, dataProcess);
        assertFalse(dataProcess.getCustomerList().isEmpty());
    }

    @Test
    public void doWorkFailedTest() {
        String line = "002ç2345675434544345çJose";
        DataProcess dataProcess = new DataProcess();

        this.customerService.doWork(line, dataProcess);
        assertTrue(dataProcess.getCustomerList().isEmpty());
    }

}
