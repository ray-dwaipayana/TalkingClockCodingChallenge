package com.talkingClock.enums;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Getter
public enum TalkingClockEnum {
    ZERO(0, ""),
    ONE(1, "one"),
    TWO(2, "two"),
    THREE(3, "three"),
    FOUR(4, "four"),
    FIVE(5, "five"),
    SIX(6, "six"),
    SEVEN(7, "seven"),
    EIGHT(8, "eight"),
    NINE(9, "nine"),
    TEN(10, "ten"),
    ELEVEN(11, "eleven"),
    TWELVE(12, "twelve"),
    THIRTEEN(13, "thirteen"),
    FOURTEEN(14, "fourteen"),
    FIFTEEN(15, "quarter"),
    SIXTEEN(16, "sixteen"),
    SEVENTEEN(17, "seventeen"),
    EIGHTEEN(18, "eighteen"),
    NINETEEN(19, "nineteen"),
    TWENTY(20, "twenty"),
    TWENTYONE(21, "twenty one"),
    TWENTYTWO(22, "twenty two"),
    TWENTYTHREE(23, "twenty three"),
    TWENTYFOUR(24, "twenty four"),
    TWENTYFIVE(25, "twenty five"),
    TWENTYSIX(26, "twenty six"),
    TWENTYSEVEN(27, "twenty seven"),
    TWENTYEIGHT(28, "twenty eight"),
    TWENTYNINE(29, "twenty nine"),
    THIRTY(30, "half");


    private int value;
    private String text;

    TalkingClockEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    /***
     *
     * @param number
     * @return - Text value of the number passed
     */

    /*
    public static TalkingClockEnum toText(int number) {
        return Arrays.stream(values())
                .filter(value -> value.value == number)
                .findFirst().orElse(null);
    }

     */
    public static String toText(int number) {
        for (TalkingClockEnum talkingClockEnum : TalkingClockEnum.values()) {
            if (talkingClockEnum.value == number) {
                return talkingClockEnum.getText();
            }
        }
        throw new IllegalArgumentException("No enum constant with number " + number);
    }
}


