package se.fpcs.elpris.onoff;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Log4j2
public class OnOffUpdaterApplication {

  public static void main(String[] args) {
    SpringApplication.run(OnOffUpdaterApplication.class, args);
  }

}
