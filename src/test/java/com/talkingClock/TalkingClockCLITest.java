package com.talkingClock;


import com.talkingClock.service.TalkingClockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
@ActiveProfiles("test")
public class TalkingClockCLITest {


    private TalkingClockCLIApplication talkingClockCLIApplication = new TalkingClockCLIApplication();

    @MockBean
    private TalkingClockService talkingClockService;

    @Test
    void testValidTimeInput(CapturedOutput output) {
        String inputTime = "13:45";
        when(talkingClockService.convertTimeToWords(inputTime)).thenReturn("Quarter to Two");
        talkingClockCLIApplication.processInput(inputTime);
        assertThat(output.getOut()).contains("Quarter to Two");
    }

        @Test
        void testHalfPast(CapturedOutput output) {
            String inputTime = "12:30";
            when(talkingClockService.convertTimeToWords(inputTime)).thenReturn("Half past Twelve");
            talkingClockCLIApplication.processInput(inputTime);
            assertThat(output.getOut()).contains("Half past Twelve");
        }

        @Test
        void testBoundaryTimes(CapturedOutput output) {
            String inputTime = "23:59";
            when(talkingClockService.convertTimeToWords(inputTime)).thenReturn("One to Twelve");
            talkingClockCLIApplication.processInput(inputTime);
            assertThat(output.getOut()).contains("One to Twelve");
        }
        @Test
        void testQuarterPast(CapturedOutput output) {
            String inputTime = "20:15";
            when(talkingClockService.convertTimeToWords(inputTime)).thenReturn("Quarter past Eight");
            talkingClockCLIApplication.processInput(inputTime);
            assertThat(output.getOut()).contains("Quarter past Eight");
        }

        @Test
        void testQuarterTo(CapturedOutput output) {
            String inputTime = "17:45";
            when(talkingClockService.convertTimeToWords(inputTime)).thenReturn("Quarter to Six");
            talkingClockCLIApplication.processInput(inputTime);
            assertThat(output.getOut()).contains("Quarter to Six");
        }

        @Test
        void testLessThanThirtyMin(CapturedOutput output) {
            String inputTime = "07:18";
            when(talkingClockService.convertTimeToWords(inputTime)).thenReturn("Eighteen past Seven");
            talkingClockCLIApplication.processInput(inputTime);
            assertThat(output.getOut()).contains("Eighteen past Seven");
        }

        @Test
        void testMoreThanThirtyMin(CapturedOutput output) {
            String inputTime = "09:48";
            when(talkingClockService.convertTimeToWords(inputTime)).thenReturn("Twelve to Ten");
            talkingClockCLIApplication.processInput(inputTime);
            assertThat(output.getOut()).contains("Twelve to Ten");
        }


    @Test
    void testInvalidTime(CapturedOutput output) {
        String inputTime = "88:88";
        when(talkingClockService.convertTimeToWords(inputTime)).thenReturn("Invalid time format entered. Please provide a valid time in HH:mm format(00:00 - 23:59).");
        talkingClockCLIApplication.processInput(inputTime);
        assertThat(output.getOut()).contains("Invalid time format entered. Please provide a valid time in HH:mm format(00:00 - 23:59).");
    }
}


