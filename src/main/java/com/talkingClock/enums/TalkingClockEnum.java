package com.talkingClock.enums;



import lombok.Getter;
import java.util.Arrays;

@Getter
public enum TalkingClockEnum {
    ZERO(0, ""),
    ONE(1, "One"),
    TWO(2, "Two"),
    THREE(3, "Three"),
    FOUR(4, "Four"),
    FIVE(5, "Five"),
    SIX(6, "Six"),
    SEVEN(7, "Seven"),
    EIGHT(8, "Eight"),
    NINE(9, "Nine"),
    TEN(10, "Ten"),
    ELEVEN(11, "Eleven"),
    TWELVE(12, "Twelve"),
    THIRTEEN(13, "Thirteen"),
    FOURTEEN(14, "Fourteen"),
    FIFTEEN(15, "Quarter"),
    SIXTEEN(16, "Sixteen"),
    SEVENTEEN(17, "Seventeen"),
    EIGHTEEN(18, "Eighteen"),
    NINETEEN(19, "Nineteen"),
    TWENTY(20, "Twenty"),
    TWENTYONE(21, "Twenty One"),
    TWENTYTWO(22, "Twenty Two"),
    TWENTYTHREE(23, "Twenty Three"),
    TWENTYFOUR(24, "Twenty Four"),
    TWENTYFIVE(25, "Twenty Five"),
    TWENTYSIX(26, "Twenty Six"),
    TWENTYSEVEN(27, "Twenty Seven"),
    TWENTYEIGHT(28, "Twenty Eight"),
    TWENTYNINE(29, "Twenty Nine"),
    THIRTY(30, "Half");


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

    public static String toText(int number) {
        return Arrays.stream(values())
                .filter(value -> value.value == number)
                .map(TalkingClockEnum::getText)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No enum constant with number " + number));
    }
}


