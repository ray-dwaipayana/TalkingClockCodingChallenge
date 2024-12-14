package com.talkingClock.service;

import com.talkingClock.exception.InvalidTimeException;
import org.springframework.stereotype.Service;

@Service
public class TalkingClockCacheService implements Clock{

    private TalkingClockService talkingClockService;
    private CacheService cacheService;

    public TalkingClockCacheService(TalkingClockService talkingClockService, CacheService cacheService) {
        this.talkingClockService = talkingClockService;
        this.cacheService = cacheService;
    }

    @Override
    public String convertTimeToWords(String time) throws InvalidTimeException {

        String cachedResult = cacheService.getFromCache(time);
        if (cachedResult != null) {
            System.out.println("getting from Cache :" + cachedResult);
            cacheService.removeFromCache(time);
            cacheService.addToCache(time,cachedResult);
            return cachedResult;

        }
        else {
            String result = talkingClockService.convertTimeToWords(time);
            System.out.println("getting not from cache : " + result);
            cacheService.addToCache(time, result);
            return result;
        }

    }
}
