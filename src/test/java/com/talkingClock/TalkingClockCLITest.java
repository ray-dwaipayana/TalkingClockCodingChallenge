    package com.talkingClock;

    import com.talkingClock.service.TalkingClockService;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.system.CapturedOutput;
    import org.springframework.boot.test.system.OutputCaptureExtension;
    import org.springframework.test.context.junit.jupiter.SpringExtension;

    import static org.assertj.core.api.Assertions.assertThat;

    import org.junit.jupiter.api.extension.ExtendWith;

    @SpringBootTest(args = {"00:00"})
    @ExtendWith(OutputCaptureExtension.class)
    public class TalkingClockCLITest {

        @Autowired
        private TalkingClockService talkingClockService;

        @BeforeEach
        public void setUp(){

        }

        @Test
        void testoClock(CapturedOutput output) {
            String[] args = {"11:00"};
            TalkingClockCLIApplication.main(args);
            assertThat(output.getOut()).contains("Eleven o'Clock");
        }

        @Test
        void testHalfPast(CapturedOutput output) {
            String[] args = {"12:30"};
            TalkingClockCLIApplication.main(args);
            assertThat(output.getOut()).contains("Half Past Twelve");
        }

        @Test
        void testBoundaryTimes(CapturedOutput output) {
            String[] args = {"23:59"};
            TalkingClockCLIApplication.main(args);
            assertThat(output.getOut()).contains("One to Twelve");
        }
        @Test
        void testQuarterPast(CapturedOutput output) {
            String[] args = {"20:15"};
            TalkingClockCLIApplication.main(args);
            assertThat(output.getOut()).contains("Quarter past Eight");
        }

        @Test
        void testQuarterTo(CapturedOutput output) {
            String[] args = {"17:45"};
            TalkingClockCLIApplication.main(args);
            assertThat(output.getOut()).contains("Quarter to Six");
        }

        @Test
        void testLessThanThirtyMin(CapturedOutput output) {
            String[] args = {"19:10"};
            TalkingClockCLIApplication.main(args);
            assertThat(output.getOut()).contains("Ten past Seven");
        }

        @Test
        void testMoreThanThirtyMin(CapturedOutput output) {
            String[] args = {"09:40"};
            TalkingClockCLIApplication.main(args);
            assertThat(output.getOut()).contains("Twenty to Ten");
        }


        @Test
        void testInvalidTime(CapturedOutput output) {
            String[] args = {"25:00"};
            TalkingClockCLIApplication.main(args);
            assertThat(output.getOut()).contains("Invalid time format entered. Please provide a valid time in HH:mm format(00:00 - 23:59).");
        }

    }

