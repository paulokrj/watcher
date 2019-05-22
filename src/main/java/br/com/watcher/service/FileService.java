package br.com.watcher.service;

import br.com.watcher.dto.DataProcess;
import br.com.watcher.dto.OutputFile;
import br.com.watcher.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

@Service
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(FileService.class);
    private DataService dataService;

    private static final String folderIn = System.getProperty("user.home") + "/data/in/";
    private static final String folderOut = System.getProperty("user.home") + "/data/out/";

    public FileService(DataService dataService) {
        this.dataService = dataService;
        FileUtil.createDirectory(folderIn);
        FileUtil.createDirectory(folderOut);
    }

    public void watcher() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(folderIn);
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                key.pollEvents().forEach(watchEvent ->  this.validateFile(watchEvent.context().toString()));
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            this.logger.error("Watcher error", e);
        }
    }

    private void validateFile(String fileName) {
        if (FileUtil.validExtension(fileName, ".dat"))
            this.processFile(fileName);
        else
            this.logger.info("Invalid extension: {}", fileName);
    }

    private void processFile(String filePath) {
        this.logger.info("Started data analysis from file: {}", filePath);
        DataProcess dataProcess = new DataProcess();

        this.readFile(filePath, dataProcess);
        this.outputFile(filePath, this.dataService.output(dataProcess));
        FileUtil.deleteFile(folderIn.concat(filePath));

        this.logger.info("Data analysis ended from file: {}", filePath);
    }

    private void outputFile(String fileName, OutputFile outputFile) {
        try {
            FileOutputStream outputStream = new FileOutputStream(folderOut.concat(FileUtil.generateOutputFileName(fileName)));
            byte[] strToBytes = outputFile.toString().getBytes();
            outputStream.write(strToBytes);
            outputStream.close();

        } catch (IOException e) {
            this.logger.error("Error writing file", e);
        }
    }

    private void readFile(String filePath, DataProcess dataProcess) {
        FileInputStream inputStream = null;
        Scanner sc = null;

        try {
            inputStream = new FileInputStream(folderIn.concat(filePath));
            sc = new Scanner(inputStream, "UTF-8");

            while (sc.hasNextLine()) {
                this.dataService.factory(sc.nextLine(), dataProcess);
            }

            if (sc.ioException() != null)
                throw sc.ioException();

        } catch (IOException e) {
            this.logger.error("File error", e);
        } finally {
            this.closeStream(inputStream, sc);
        }
    }

    private void closeStream(FileInputStream inputStream, Scanner scanner) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                this.logger.error("Error", e);
            }
        }
        if (scanner != null)
            scanner.close();
    }
}
