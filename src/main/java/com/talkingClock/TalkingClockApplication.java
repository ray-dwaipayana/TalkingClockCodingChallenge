package com.talkingClock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TalkingClockApplication {

    public static void main(String[] args) {

        SpringApplicationBuilder app = new SpringApplicationBuilder(TalkingClockApplication.class);

        if (args.length > 0 && args[0].equalsIgnoreCase("CLI")) {
            app.web(WebApplicationType.NONE);
        } else {
            app.web(WebApplicationType.SERVLET);
        }
        app.run(args);

    }

}