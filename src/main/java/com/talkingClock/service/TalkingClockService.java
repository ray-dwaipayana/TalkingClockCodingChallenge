package com.talkingClock.service;

import com.talkingClock.constants.TalkingClockConstants;
import com.talkingClock.enums.TalkingClockEnum;
import com.talkingClock.exception.InvalidTimeException;
import com.talkingClock.utils.TalkingClockUtil;
import org.springframework.stereotype.Service;


@Service
public class TalkingClockService implements Clock{

    TalkingClockConstants talkingClockConstants = new TalkingClockConstants();

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
                if (min == 0) {
                    timeInWords.append(talkingClockConstants.getOClock());
                    hourInWords.append(TalkingClockEnum.toText(hour));
                    hourInWords.append(talkingClockConstants.getSpace());
                    finalOutput = hourInWords.append(timeInWords);
                } else if (min < 31) {
                    timeInWords.append(TalkingClockEnum.toText(min));
                    hourInWords.append(TalkingClockEnum.toText(hour));
                    finalOutput.append(timeInWords);
                    finalOutput.append(talkingClockConstants.getSpace());
                    finalOutput.append(talkingClockConstants.getPast());
                    finalOutput.append(talkingClockConstants.getSpace());
                    finalOutput.append(hourInWords);
                } else  {
                    min = 60 - min;
                    hourInWords.append(TalkingClockEnum.toText(hour+1));
                    timeInWords.append(TalkingClockEnum.toText(min));
                    finalOutput.append(timeInWords);
                    finalOutput.append(talkingClockConstants.getSpace());
                    finalOutput.append(talkingClockConstants.getTo());
                    finalOutput.append(talkingClockConstants.getSpace());
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
