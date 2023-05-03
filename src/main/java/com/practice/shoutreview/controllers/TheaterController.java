package com.practice.shoutreview.controllers;

import com.practice.shoutreview.dto.TheaterDto;
import com.practice.shoutreview.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity<TheaterDto> addTheater(@RequestBody @Validated TheaterDto theaterDto) {

        return ResponseEntity.ok(theaterService.addTheater(theaterDto));
    }

   // @Min(value = 1, message = "Theater Id Cannot be -ve")
    @GetMapping("/{id}")
    public ResponseEntity<TheaterDto> getTheater(@PathVariable(name = "id") Integer id) {

        return ResponseEntity.ok(theaterService.getTheater(id));
    }
}
