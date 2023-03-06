package it.corso.mygym.controller;

import it.corso.mygym.model.GymStructure;
import it.corso.mygym.model.dto.GymStructureDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/gyms")
public interface GymStructureController {

    @PostMapping()
    ResponseEntity<GymStructure> save(@RequestBody GymStructureDto dto);

    @GetMapping("/{id}")
    ResponseEntity<GymStructure> findById(@PathVariable("id") long id);

    @GetMapping("/all")
    ResponseEntity<List<GymStructure>> findAll();
}
