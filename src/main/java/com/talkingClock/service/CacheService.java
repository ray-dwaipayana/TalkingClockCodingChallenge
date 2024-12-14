package com.talkingClock.service;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CacheService {

    private final LinkedHashMap<String, String> cache = new LinkedHashMap<>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return size() > 5;
        }
    };

    public String getFromCache(String time)
    {
        return cache.getOrDefault(time,null);
    }

    public void addToCache(String time , String timeInWords)
    {
        cache.put(time,timeInWords);
        System.out.println(cache.values());
    }

    public void removeFromCache(String time)
    {
        cache.entrySet().removeIf(entry -> entry.getKey().equals(time));
        System.out.println(cache.values());
    }



}
