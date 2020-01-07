package com.github.fernandotaa.springbatch.multipleexecutions.controller;

import com.github.fernandotaa.springbatch.multipleexecutions.dto.PersonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final JdbcTemplate jdbcTemplate;

    public PersonController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonDTO>> getPeople() {
        var list = jdbcTemplate.query("SELECT * FROM PATIENT", PersonDTO.ROW_MAPPER);
        return ResponseEntity.ok(list);
    }
}
