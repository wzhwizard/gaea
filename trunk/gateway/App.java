package cn.etcp.datacollection;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
		SpringApplication app = new SpringApplication(App.class);
		app.setBannerMode(Mode.LOG);
		app.run(args);
	}
}
