package com.tejo.appdemo.controller;

import com.tejo.appdemo.entity.TejoEntity;
import com.tejo.appdemo.Service.TejoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/tejo")
public class TejoController {

    private final TejoService tejoService;

    @Autowired
    public TejoController(TejoService tejoService) {
        this.tejoService = tejoService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<TejoEntity> createTejo(@RequestBody TejoEntity tejo) {
        log.info("Creating a new Tejo entity: {}", tejo);
        TejoEntity saved = tejoService.saveTejo(tejo);
        return ResponseEntity.ok(saved);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<TejoEntity>> getAllTejos() {
        log.info("Fetching all Tejo entities");
        List<TejoEntity> list = tejoService.getAllTejos();
        return ResponseEntity.ok(list);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TejoEntity> getTejoById(@PathVariable Integer id) {
        log.info("Fetching Tejo entity with ID: {}", id);
        return tejoService.getTejoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE BY ID
    @PutMapping("/{id}")
    public ResponseEntity<TejoEntity> updateTejo(@PathVariable Integer id, @RequestBody TejoEntity tejo) {
        log.info("Updating Tejo entity with ID: {}", id);
        if (tejoService.getTejoById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        TejoEntity updated = tejoService.updateTejo(id, tejo);
        return ResponseEntity.ok(updated);
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTejo(@PathVariable Integer id) {
        if (tejoService.getTejoById(id).isEmpty()) { // Updated from isPresent() to isEmpty()
            return ResponseEntity.notFound().build();
        }
        tejoService.deleteTejo(id);
        return ResponseEntity.noContent().build();
    }
}
