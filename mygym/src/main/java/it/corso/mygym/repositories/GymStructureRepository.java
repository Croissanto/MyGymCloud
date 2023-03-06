package it.corso.mygym.repositories;

import it.corso.mygym.model.GymStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymStructureRepository extends JpaRepository<GymStructure, Long> {
}
