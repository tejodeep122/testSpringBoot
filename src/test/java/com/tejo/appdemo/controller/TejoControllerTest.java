package com.tejo.appdemo.controller;

import com.tejo.appdemo.Service.TejoService;
import com.tejo.appdemo.entity.TejoEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(controllers = TejoController.class)
@WithMockUser(username = "testuser", roles = {"USER"})
class TejoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TejoService tejoService;

    @Test
    void testCreateTejo() throws Exception {
        TejoEntity tejo = new TejoEntity();
        tejo.setName("Test Tejo");

        Mockito.when(tejoService.saveTejo(Mockito.any(TejoEntity.class))).thenReturn(tejo);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tejo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Tejo\"}")
                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Tejo"));
    }

    @Test
    void testGetAllTejos() throws Exception {
        TejoEntity tejo1 = new TejoEntity();
        tejo1.setName("Tejo1");
        TejoEntity tejo2 = new TejoEntity();
        tejo2.setName("Tejo2");

        Mockito.when(tejoService.getAllTejos()).thenReturn(Arrays.asList(tejo1, tejo2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tejo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Tejo1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Tejo2"));
    }

    @Test
    void testGetTejoById() throws Exception {
        TejoEntity tejo = new TejoEntity();
        tejo.setName("Test Tejo");

        Mockito.when(tejoService.getTejoById(Mockito.anyInt())).thenReturn(Optional.of(tejo));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tejo/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Tejo"));
    }

    @Test
    void testUpdateTejo() throws Exception {
        TejoEntity tejo = new TejoEntity();
        tejo.setName("Updated Tejo");

        Mockito.when(tejoService.getTejoById(Mockito.anyInt())).thenReturn(Optional.of(tejo));
        Mockito.when(tejoService.updateTejo(Mockito.anyInt(), Mockito.any(TejoEntity.class))).thenReturn(tejo);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/tejo/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Tejo\"}")
                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Tejo"));
    }

    @Test
    void testDeleteTejo() throws Exception {
        Mockito.when(tejoService.getTejoById(Mockito.anyInt())).thenReturn(Optional.of(new TejoEntity()));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tejo/1")
                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
