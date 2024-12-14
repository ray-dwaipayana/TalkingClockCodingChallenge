package com.talkingClock.service;

import com.talkingClock.exception.InvalidTimeException;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class CacheServiceTest {


   private final CacheService cacheService = new CacheService();

   @Test
    public void testAddAndRetrieveFromCache()
   {
       cacheService.addToCache("10:10" , "ten past ten");
       String result = cacheService.getFromCache("10:10");
       assertEquals(result,cacheService.getFromCache("10:10"));
   }


   @Test
    public void testCacheEviction()
   {
       cacheService.addToCache("10:10" , "ten past ten");
       cacheService.addToCache("10:11" , "eleven past ten");
       cacheService.addToCache("10:12" , "twelve past ten");
       cacheService.addToCache("10:13" , "thirteen past ten");
       cacheService.addToCache("10:14" , "fourteen past ten");
       cacheService.addToCache("10:15" , "quarter past ten");
        //testing the automatic eviction
       String cacheResult = cacheService.getFromCache("10:10");
       assertNull(cacheResult);

   }

   @Test
    public void testCacheRemoval()
   {
       cacheService.addToCache("10:10" , "ten past ten");
       cacheService.removeFromCache("10:10");
        //testing the removal
       String cacheResult = cacheService.getFromCache("10:10");
       assertNull(cacheResult);
   }

}
