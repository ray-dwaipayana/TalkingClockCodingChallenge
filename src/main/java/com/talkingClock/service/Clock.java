package com.talkingClock.service;

import com.talkingClock.exception.InvalidTimeException;

public interface Clock {

    String convertTimeToWords(String time) throws InvalidTimeException;

}
