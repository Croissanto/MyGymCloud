package it.corso.mygym.repositories;

import it.corso.mygym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByActiveFlagTrue();
    List<User> findTop3ByOrderByBirthDayDesc();
    List<User> findByCertifiedMedicallyTrue();

}
