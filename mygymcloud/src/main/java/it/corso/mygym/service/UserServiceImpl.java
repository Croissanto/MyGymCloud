package it.corso.mygym.service;

import it.corso.mygym.Constants;
import it.corso.mygym.model.User;
import it.corso.mygym.model.dto.UserDto;
import it.corso.mygym.model.exceptions.UserNotFoundException;
import it.corso.mygym.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;


    @Override
    public User save(UserDto dto) {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(dto, User.class);
        return userRepo.save(user);
    }

    @Override
    public User findById(long id) {
        validateIdExists(id);
        Optional<User> opt = userRepo.findById(id);
        return opt.orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> findTop3ByOrderByBirthDayDesc() {
        return userRepo.findTop3ByOrderByBirthDayDesc();
    }

    @Override
    public boolean deleteById(long id) {
        try {
            userRepo.deleteById(id);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User update(long id, UserDto dto) {
        Optional<User> opt = userRepo.findById(id);
        dto.setId(id);

        if(opt.isPresent()){
            User user = opt.get();
            copyNonNullProperties(dto, user);
            dto.setId(id);

            return userRepo.saveAndFlush(user);

        }

         else throw new ResourceNotFoundException();
    }

    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames(Object source) {
         BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    private void validateIdExists(final Long id) {
        if (userRepo.findById(id).isEmpty()) throw new UserNotFoundException(Constants.USER_NOT_FOUND_EXCEPTION, id);
    }


}
