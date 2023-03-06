package it.corso.mygym.service;

import it.corso.mygym.model.GymStructure;
import it.corso.mygym.model.dto.GymStructureDto;

import java.util.List;

public interface GymStructureService {

    GymStructure save(GymStructureDto dto);

    GymStructure findById(long id);

    List<GymStructure> findAll();

    boolean deleteById(long id);

    GymStructure update(long id, GymStructureDto dto);
}
