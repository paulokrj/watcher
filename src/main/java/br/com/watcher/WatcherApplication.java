package br.com.watcher;

import br.com.watcher.service.FileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class WatcherApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WatcherApplication.class, args);
		FileService fileProcess = context.getBean(FileService.class);
		fileProcess.watcher();
	}
}
