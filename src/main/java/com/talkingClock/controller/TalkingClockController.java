package com.talkingClock.controller;



import com.talkingClock.exception.InvalidTimeException;
import com.talkingClock.models.Response;
import com.talkingClock.service.TalkingClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TalkingClockController {


    @Autowired
    TalkingClockService talkingClockService;


    @GetMapping ("/talking-clock")
    public Response getTime(@RequestParam(name="time",required = false) String time) throws InvalidTimeException {
        if (time != null)
        {

            return Response.builder().value(talkingClockService.convertTimeToWords(time)).build();
        }
        else
        {
            return Response.builder().value(talkingClockService.convertTimeToWords("00:00")).build();
        }

    }

}