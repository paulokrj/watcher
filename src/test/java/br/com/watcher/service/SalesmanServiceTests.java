package br.com.watcher.service;

import br.com.watcher.dto.DataProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class SalesmanServiceTests {

    @InjectMocks
    private SalesmanService salesmanService;

    @Test
    public void splitDefaultSuccessTest() {
        String line = "001ç1234567891234çPedroç50000";

        List<String> result = this.salesmanService.splitLine(line, 4);

        assertAll(
                () -> assertEquals(result.size(), 4),
                () -> assertEquals(result.get(0), "001"),
                () -> assertEquals(result.get(1), "1234567891234"),
                () -> assertEquals(result.get(2), "Pedro"),
                () -> assertEquals(result.get(3), "50000")
        );
    }

    @Test
    public void splitSuccessTest() {
        String line = "001ç1234567891234çPedro Mendonçaç50000";

        List<String> result = this.salesmanService.splitLine(line, 4);

        assertAll(
                () -> assertEquals(result.size(), 4),
                () -> assertEquals(result.get(0), "001"),
                () -> assertEquals(result.get(1), "1234567891234"),
                () -> assertEquals(result.get(2), "Pedro Mendonça"),
                () -> assertEquals(result.get(3), "50000")
        );
    }

    @Test
    public void splitFailedTest() {
        String line = "001ç1234567891234çPedro";
        List<String> result = this.salesmanService.splitLine(line, 4);
        assertTrue(result.isEmpty());
    }

    @Test
    public void doWorkSuccessTest() {
        String line = "001ç1234567891234çPedro Mendonçaç50000";
        DataProcess dataProcess = new DataProcess();

        this.salesmanService.doWork(line, dataProcess);
        assertFalse(dataProcess.getSalesmanList().isEmpty());
    }

    @Test
    public void doWorkFailedTest() {
        String line = "001ç1234567891234çPedro";
        DataProcess dataProcess = new DataProcess();

        this.salesmanService.doWork(line, dataProcess);
        assertTrue(dataProcess.getSalesmanList().isEmpty());
    }

}
