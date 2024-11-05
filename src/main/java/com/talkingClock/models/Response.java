package com.talkingClock.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * author - Dwaipayana Ray
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String value;
}