package com.talkingClock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TalkingClockCacheIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testTalkincClockCache() throws Exception {
        // Fill the cache (5 entries)
        mockMvc.perform(MockMvcRequestBuilders.get("/talking-clock?time=01:00"));
        mockMvc.perform(MockMvcRequestBuilders.get("/talking-clock?time=02:00"));
        mockMvc.perform(MockMvcRequestBuilders.get("/talking-clock?time=03:00"));
        mockMvc.perform(MockMvcRequestBuilders.get("/talking-clock?time=04:00"));
        mockMvc.perform(MockMvcRequestBuilders.get("/talking-clock?time=05:00"));

        // Access the first entry (01:00) again - it becomes the most recently used
        mockMvc.perform(MockMvcRequestBuilders.get("/talking-clock?time=01:00"));

        // Add a new entry - the least recently used (02:00) should be evicted
        mockMvc.perform(MockMvcRequestBuilders.get("/talking-clock?time=06:00"));

        // Check if 02:00 is no longer in the cache (by making a request that would normally be cached)
        mockMvc.perform(MockMvcRequestBuilders.get("/talking-clock?time=02:00"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"value\":\"Two o'Clock\"}")); // Should recalculate, not get from cache

        //Check if 01:00 is still in the cache
        mockMvc.perform(MockMvcRequestBuilders.get("/talking-clock?time=01:00"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"value\":\"One o'Clock\"}")); // Should get from cache
    }
}