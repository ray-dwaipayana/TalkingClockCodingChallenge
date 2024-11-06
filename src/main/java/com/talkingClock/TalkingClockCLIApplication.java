package com.talkingClock;

import com.talkingClock.exception.InvalidTimeException;
import com.talkingClock.service.TalkingClockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Scanner;


@Component
@ConditionalOnNotWebApplication
@ConditionalOnProperty(name = "talkingClock.cli.enabled", havingValue = "true", matchIfMissing = true)
public class TalkingClockCLIApplication  implements CommandLineRunner{


private  final TalkingClockService talkingClockService = TalkingClockService.getInstance();

    @Override
    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter time in HH:mm format or press enter to display the current time:");
            String inputTime = scanner.nextLine().trim();
            processInput(inputTime);

        }
    }

    public void processInput(String inputTime)
    {
            if (inputTime.isEmpty()) {
               String currentTime = String.valueOf(LocalTime.now());
                String[] timeParts = currentTime.split(":");
                currentTime = timeParts[0] + ":" + timeParts[1];
                System.out.println(talkingClockService.convertTimeToWords(currentTime));
            } else {
                try {
                    System.out.println(talkingClockService.convertTimeToWords(inputTime));
                } catch (InvalidTimeException e) {
                    System.out.println("Invalid time format entered. Please provide a valid time in HH:mm format(00:00 - 23:59).");
                }
            }
    }
}


