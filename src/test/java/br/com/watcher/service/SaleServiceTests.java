package br.com.watcher.service;

import br.com.watcher.dto.DataProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class SaleServiceTests {

    @InjectMocks
    private SaleService saleService;

    @Test
    public void splitDefaultSuccessTest() {
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";

        List<String> result = this.saleService.splitLine(line, 4);

        assertAll(
                () -> assertEquals(result.size(), 4),
                () -> assertEquals(result.get(0), "003"),
                () -> assertEquals(result.get(1), "10"),
                () -> assertEquals(result.get(2), "[1-10-100,2-30-2.50,3-40-3.10]"),
                () -> assertEquals(result.get(3), "Pedro")
        );
    }

    @Test
    public void splitSuccessTest() {
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro Mendonça";

        List<String> result = this.saleService.splitLine(line, 4);

        assertAll(
                () -> assertEquals(result.size(), 4),
                () -> assertEquals(result.get(0), "003"),
                () -> assertEquals(result.get(1), "10"),
                () -> assertEquals(result.get(2), "[1-10-100,2-30-2.50,3-40-3.10]"),
                () -> assertEquals(result.get(3), "Pedro Mendonça")
        );
    }

    @Test
    public void splitFailedTest() {
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]";
        List<String> result = this.saleService.splitLine(line, 4);
        assertTrue(result.isEmpty());
    }

    @Test
    public void doWorkSuccessTest() {
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro Mendonça";
        DataProcess dataProcess = new DataProcess();

        this.saleService.doWork(line, dataProcess);
        assertFalse(dataProcess.getSaleList().isEmpty());
    }

    @Test
    public void doWorkFailedTest() {
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]";
        DataProcess dataProcess = new DataProcess();

        this.saleService.doWork(line, dataProcess);
        assertTrue(dataProcess.getSaleList().isEmpty());
    }

}
