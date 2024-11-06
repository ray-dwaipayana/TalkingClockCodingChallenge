package com.talkingClock.service;

import com.talkingClock.enums.TalkingClockEnum;
import com.talkingClock.exception.InvalidTimeException;
import com.talkingClock.utils.TalkingClockUtil;
import org.springframework.stereotype.Service;


@Service
public class TalkingClockService implements Clock{

    private static final String SPACE = " ";
    private static final String OCLOCK = "o'Clock";
    private static final String PAST = "past";
    private static final String TO = "to";

    private TalkingClockService() {

    }
    private static final TalkingClockService INSTANCE = new TalkingClockService();

    public static TalkingClockService getInstance() {
        return INSTANCE;
    }



    @Override
    public String convertTimeToWords(String time) throws InvalidTimeException {
        if(!TalkingClockUtil.validateTime(time)) {
            throw new InvalidTimeException("Invalid time format entered. Please provide a valid time in HH:mm format(00:00 - 23:59).");
        }

        String[] timeParts=time.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int min = Integer.parseInt(timeParts[1]);

        StringBuilder finalOutput=new StringBuilder();
        StringBuilder hourInWords=new StringBuilder();
        StringBuilder timeInWords=new StringBuilder();

        try {
            if ((hour < 25 && hour > -1) && (min < 60 && min > -1)) {
                //hour conversion
                if(hour > 12)
                {
                    hour = hour-12;
                } else if (hour == 0) {

                    hour = hour + 12;

                }
                // Human Friendly Text creation
                if (min == 0) {
                    timeInWords.append(OCLOCK);
                    hourInWords.append(TalkingClockEnum.toText(hour));
                    hourInWords.append(SPACE);
                    finalOutput = hourInWords.append(timeInWords);
                } else if (min < 31) {
                    timeInWords.append(TalkingClockEnum.toText(min));
                    hourInWords.append(TalkingClockEnum.toText(hour));
                    finalOutput.append(timeInWords);
                    finalOutput.append(SPACE);
                    finalOutput.append(PAST);
                    finalOutput.append(SPACE);
                    finalOutput.append(hourInWords);
                } else  {
                    min = 60 - min;
                    if(hour == 12)
                    {
                        hour = 0;
                    }
                    hourInWords.append(TalkingClockEnum.toText(hour+1));
                    timeInWords.append(TalkingClockEnum.toText(min));
                    finalOutput.append(timeInWords);
                    finalOutput.append(SPACE);
                    finalOutput.append(TO);
                    finalOutput.append(SPACE);
                    finalOutput.append(hourInWords);

                }
            }
            else
            {
                throw new InvalidTimeException("Invalid time format entered. Please provide a valid time in HH:mm format(00:00 - 23:59).");
            }
        } catch (InvalidTimeException e) {
            throw new InvalidTimeException("Invalid time format entered. Please provide a valid time in HH:mm format(00:00 - 23:59).");
        }
        return  finalOutput.toString();

    }

}
