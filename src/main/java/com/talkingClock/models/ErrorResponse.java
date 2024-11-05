package com.talkingClock.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;
}
