package com.talkingClock.utils;

import com.talkingClock.enums.TalkingClockEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TalkingClockUtil {

    private static final String TIME24HOURS_PATTERN = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";

    public static boolean validateTime(String time)
    {
        Pattern pattern = Pattern.compile(TIME24HOURS_PATTERN);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    public static String toWord(int digit) {

            return TalkingClockEnum.toText(digit);

    }


}
