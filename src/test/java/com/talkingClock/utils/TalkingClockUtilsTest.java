package com.talkingClock.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TalkingClockUtilsTest {

    @Test
    public void validateTimeTestValid() {
        boolean timeCheck = false;
        timeCheck = TalkingClockUtil.validateTime("23:59");
        assertTrue(timeCheck);
    }

    @Test
    public void validateTimeTestInvalid() {
        boolean timeCheck = false;
        timeCheck = TalkingClockUtil.validateTime("1:43");
        assertEquals(false, timeCheck);
    }


}