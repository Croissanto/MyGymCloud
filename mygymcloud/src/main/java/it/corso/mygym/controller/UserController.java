package it.corso.mygym.controller;


import it.corso.mygym.model.User;
import it.corso.mygym.model.dto.UserDto;
import it.corso.mygym.model.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public interface UserController {


    @PostMapping()
    ResponseEntity<User> save(@RequestBody UserDto dto);

    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable long id);

    @GetMapping("/all")
    ResponseEntity<List<User>> findAll();

    @PutMapping("/{id}")
    ResponseEntity<User> update(@PathVariable("id") long id, UserDto dto) throws UserNotFoundException;

    @DeleteMapping("/{id}")
    boolean deleteById(@PathVariable("id") long id);

    @GetMapping("/top3ByBirthDay")
    ResponseEntity<List<User>> findTop3ByBirthDayDesc();

}
