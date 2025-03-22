package com.satya.postApp.controller;

import com.satya.postApp.dto.SuvicharDto;
import com.satya.postApp.dto.TextDto;
import com.satya.postApp.entity.Suvichar;
import com.satya.postApp.service.SuvicharService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suvichar")
public class SuvicharController {

    @Autowired
    private SuvicharService suvicharService;

    // API to accept a list of Suvichars and store them with translations
    @PostMapping("/translate-and-store")
    public String translateAndStore(@RequestBody List<TextDto> suvichars) {
        try {
            suvicharService.translateAndSaveSuvichars(suvichars);
            return "Suvichars translated and stored successfully!";
        } catch (Exception e) {

            return e.getMessage();
        }

    }

    // POST endpoint to add a new Suvichar
    @PostMapping("/add")
    public ResponseEntity<Suvichar> addSuvichar(@RequestBody SuvicharDto suvicharDto) {
        Suvichar savedSuvichar = suvicharService.addSuvichar(suvicharDto);
        return ResponseEntity.ok(savedSuvichar);  // Return 200 OK with the saved entity
    }

    @GetMapping
    public List<Suvichar> test() {
        return suvicharService.getAllSuvichar();
    }


    @PostMapping("/add-batch")
    public ResponseEntity<String> addTranslatedSuvichars(@RequestBody List<String> suvichars) {
        suvicharService.processAndSaveSuvichars(suvichars);
        return ResponseEntity.ok("Suvichars translated, tagged, and saved successfully!");
    }
}
