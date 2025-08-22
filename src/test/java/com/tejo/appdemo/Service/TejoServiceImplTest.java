package com.tejo.appdemo.Service;

import com.tejo.appdemo.entity.TejoEntity;
import com.tejo.appdemo.repository.TejoRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TejoServiceImplTest {

    @Mock
    private TejoRepo tejoRepo;

    @InjectMocks
    private TejoServiceImpl tejoService;

    @Test
    void testSaveTejo() {
        TejoEntity tejo = new TejoEntity();
        tejo.setName("Test Tejo");

        Mockito.when(tejoRepo.save(Mockito.any(TejoEntity.class))).thenReturn(tejo);

        TejoEntity savedTejo = tejoService.saveTejo(tejo);
        assertEquals("Test Tejo", savedTejo.getName());
    }

    @Test
    void testGetAllTejos() {
        TejoEntity tejo1 = new TejoEntity();
        tejo1.setName("Tejo1");
        TejoEntity tejo2 = new TejoEntity();
        tejo2.setName("Tejo2");

        Mockito.when(tejoRepo.findAll()).thenReturn(Arrays.asList(tejo1, tejo2));

        assertEquals(2, tejoService.getAllTejos().size());
    }

    @Test
    void testGetTejoById() {
        TejoEntity tejo = new TejoEntity();
        tejo.setName("Test Tejo");

        Mockito.when(tejoRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(tejo));

        Optional<TejoEntity> foundTejo = tejoService.getTejoById(1);
        assertTrue(foundTejo.isPresent());
        assertEquals("Test Tejo", foundTejo.get().getName());
    }

    @Test
    void testUpdateTejo() {
        TejoEntity tejo = new TejoEntity();
        tejo.setName("Updated Tejo");

        Mockito.when(tejoRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(tejo));
        Mockito.when(tejoRepo.save(Mockito.any(TejoEntity.class))).thenReturn(tejo);

        TejoEntity updatedTejo = tejoService.updateTejo(1, tejo);
        assertEquals("Updated Tejo", updatedTejo.getName());
    }

    @Test
    void testDeleteTejo() {
        Mockito.doNothing().when(tejoRepo).deleteById(Mockito.anyInt());

        assertDoesNotThrow(() -> tejoService.deleteTejo(1));
    }
}
