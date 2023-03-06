package it.corso.mygym.controller;

import it.corso.mygym.model.GymStructure;
import it.corso.mygym.model.dto.GymStructureDto;
import it.corso.mygym.service.GymStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gyms")
public class GymStructureControllerImpl implements GymStructureController{


    private final GymStructureService gymService;


    @Autowired
    public GymStructureControllerImpl(GymStructureService gymService) {
        this.gymService = gymService;
    }


    @Override
    @PostMapping()
    public ResponseEntity<GymStructure> save(GymStructureDto dto) {
        GymStructure gym = gymService.save(dto);
        return new ResponseEntity<>(gym, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<GymStructure> findById(@PathVariable("id") long id) {
        GymStructure gym = gymService.findById(id);
        return new ResponseEntity<>(gym, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<GymStructure>> findAll() {
        List<GymStructure> gymStructureList = gymService.findAll();
        return new ResponseEntity<>(gymStructureList, HttpStatus.FOUND);
    }
}
