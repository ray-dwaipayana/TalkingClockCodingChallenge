package com.talkingClock.controller;



import com.talkingClock.exception.InvalidTimeException;
import com.talkingClock.models.Response;
import com.talkingClock.service.TalkingClockCacheService;
import com.talkingClock.service.TalkingClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@RestController
public class TalkingClockController {


   // TalkingClockService talkingClockService;
    private final TalkingClockCacheService talkingClockCacheService;

    public TalkingClockController(TalkingClockCacheService talkingClockCacheService) {
        this.talkingClockCacheService = talkingClockCacheService;
    }
    String currentTime;

    @GetMapping ("/talking-clock")
    public Response getTime(@RequestParam(name="time",required = false) String time)  {

        try {
            if (time != null) {
                return Response.builder().value(talkingClockCacheService.convertTimeToWords(time)).build();
            } else {
                currentTime = String.valueOf(LocalTime.now());
                String[] timeParts = currentTime.split(":");
                String hours = timeParts[0];
                String min = timeParts[1];
                currentTime = hours + ":" + min;
                return Response.builder().value(talkingClockCacheService.convertTimeToWords(currentTime)).build();
            }
        } catch (DateTimeException dateTimeException) {
            throw new InvalidTimeException("Invalid time format entered. Please provide a valid time in HH:mm format(00:00 - 23:59).");
        }
    }



}