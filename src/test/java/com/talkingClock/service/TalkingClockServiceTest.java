package com.talkingClock.service;



import com.talkingClock.exception.InvalidTimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TalkingClockServiceTest {


    private final TalkingClockService talkingClockService = TalkingClockService.getInstance();

    public static Stream<Arguments> provideValidTimeCases() {
        return Stream.of(
                Arguments.of("10:00","Ten o'Clock"),
                Arguments.of("05:15","Quarter past Five"),
                Arguments.of("10:50","Ten to Eleven"),
                Arguments.of("00:00","Twelve o'Clock"),
                Arguments.of("17:15","Quarter past Five"),
                Arguments.of("12:50","Ten to One"),
                Arguments.of("23:59","One to Twelve"),
                Arguments.of("08:30","Half past Eight"),
                Arguments.of("10:50","Ten to Eleven")
        );

    }

    public static Stream<Arguments> provideInValidTimeCases() {
        return Stream.of(
                Arguments.of("10:77"),
                Arguments.of("34:15"),
                Arguments.of("88:88"),
                Arguments.of("22"),
                Arguments.of("asd")
        );

    }


    @ParameterizedTest
    @MethodSource("provideValidTimeCases")
    public void covertTimeToWordsTestValid(String time , String expectedOutput) throws InvalidTimeException {

        assertEquals(expectedOutput, talkingClockService.convertTimeToWords(time));
    }

    @ParameterizedTest
    @MethodSource("provideInValidTimeCases")
    public void covertTimeToWordsTestInvalid(String time)
    {
        assertThrows(InvalidTimeException.class, () -> {
            talkingClockService.convertTimeToWords(time);
            throw new InvalidTimeException("Invalid time format entered. Please provide a valid time in HH:mm format(00:00 - 23:59).");
        });
    }


}
