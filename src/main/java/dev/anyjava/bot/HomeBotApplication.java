package dev.anyjava.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class HomeBotApplication {

    static {
        //Add this line to initialize bots context
//        ApiContextInitializer.init();
    }

    public static void main(String[] args) {
        SpringApplication.run(HomeBotApplication.class, args);
    }
}
