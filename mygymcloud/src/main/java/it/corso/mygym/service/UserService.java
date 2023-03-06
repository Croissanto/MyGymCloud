package it.corso.mygym.service;

import it.corso.mygym.model.User;
import it.corso.mygym.model.dto.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);

    User findById(long id);

    List<User> findAll();

    List<User> findTop3ByOrderByBirthDayDesc();

    boolean deleteById(long id);

    User update(long id, UserDto user);


}
