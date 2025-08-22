package com.tejo.appdemo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tejo.appdemo.Service.TejoService;
import com.tejo.appdemo.controller.TejoController;
import com.tejo.appdemo.entity.TejoEntity;

@AutoConfigureMockMvc
@WebMvcTest(controllers = TejoController.class)
class AppdemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllTejos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tejo"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreateTejo() throws Exception {
        TejoEntity tejo = new TejoEntity();
        tejo.setName("Test Tejo");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tejo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(tejo)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

