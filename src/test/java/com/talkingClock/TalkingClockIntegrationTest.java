package com.talkingClock;

import com.talkingClock.TalkingClockApplication;
import com.talkingClock.models.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.talkingClock.models.ErrorResponse;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/***
 * Test class for Controller. Testing the positive,negative and boundary conditions using Parameterized tests.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TalkingClockApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TalkingClockIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @ParameterizedTest
    @MethodSource("invalidTimesProvider")
    void testTalkingClockWhenInvalidTimeFormatIsSupplied(String time) {
        String url = createURLWithPort("/talking-clock?time=" + time);
        ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(url,ErrorResponse.class);
        String expectedResponse = "Invalid time format entered. Please provide a valid time in HH:mm format(00:00 - 23:59).";
        ErrorResponse errorResponse = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedResponse,errorResponse.getMessage());
    }
    @ParameterizedTest
    @MethodSource("validTimesProvider")
    void testTalkingClockWithValidTimeFormats(String time, String expectedOutput) {
        String url = createURLWithPort("/talking-clock?time=" + time);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOutput, response.getBody());
    }

    @Test
    void testTalkingClockNoArguments()
    {
        String url = createURLWithPort("/talking-clock");
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify that the response body is not null and contains a message
        assertNotNull(response);
        assertNotNull(response.getBody());

    }





    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private static Stream<Arguments> validTimesProvider() {
        return Stream.of(

                Arguments.of("11:22","{\"value\":\"Twenty Two past Eleven\"}"),
                Arguments.of("03:45","{\"value\":\"Quarter to Four\"}"),
                Arguments.of("11:00","{\"value\":\"Eleven o'Clock\"}"),
                Arguments.of("06:30","{\"value\":\"Half past Six\"}"),
                Arguments.of("00:00","{\"value\":\"Twelve o'Clock\"}"),
                Arguments.of("22:45","{\"value\":\"Quarter to Eleven\"}"),
                Arguments.of("10:15","{\"value\":\"Quarter past Ten\"}"),
                Arguments.of("18:30","{\"value\":\"Half past Six\"}"),
                Arguments.of("23:59","{\"value\":\"One to Twelve\"}"),
                Arguments.of("17:40","{\"value\":\"Twenty to Six\"}"),
                Arguments.of("23:00","{\"value\":\"Eleven o'Clock\"}"),
                Arguments.of("02:11","{\"value\":\"Eleven past Two\"}")
        );
    }
    private static Stream<Arguments> invalidTimesProvider() {
        return Stream.of(
                Arguments.of("123:432"),
                Arguments.of("G1:2I"),
                Arguments.of("24;24"),
                Arguments.of("01:61"),
                Arguments.of("asdsd"),
                Arguments.of("03:1"),
                Arguments.of("1:43"),
                Arguments.of("0123")
        );
    }
}