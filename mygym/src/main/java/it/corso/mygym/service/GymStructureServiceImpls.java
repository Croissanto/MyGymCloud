package it.corso.mygym.service;

import it.corso.mygym.Constants;
import it.corso.mygym.model.GymStructure;
import it.corso.mygym.model.dto.GymStructureDto;
import it.corso.mygym.model.exceptions.UserNotFoundException;
import it.corso.mygym.repositories.GymStructureRepository;
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
public class GymStructureServiceImpls implements GymStructureService {

    @Autowired
    private GymStructureRepository gymRepo;

    @Override
    public GymStructure save(GymStructureDto dto) {
        ModelMapper mapper = new ModelMapper();
        GymStructure gym = mapper.map(dto, GymStructure.class);
        return gymRepo.save(gym);
    }

    @Override
    public GymStructure findById(long id) {
        Optional<GymStructure> opt = gymRepo.findById(id);
        return opt.orElse(null);
    }

    @Override
    public List<GymStructure> findAll() {
        return gymRepo.findAll();
    }

    @Override
    public boolean deleteById(long id) {
        try {
            gymRepo.deleteById(id);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public GymStructure update(long id, GymStructureDto dto) {
        Optional<GymStructure> opt = gymRepo.findById(id);
        dto.setId(id);
        if (opt.isPresent()) {
            GymStructure gymStructure = opt.get();
            copyNonNullProperties(dto, gymStructure);
            dto.setId(id);

            return gymRepo.saveAndFlush(gymStructure);

        } else throw new ResourceNotFoundException();
    }

    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
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
        if (gymRepo.findById(id).isEmpty()) throw new UserNotFoundException(Constants.USER_NOT_FOUND_EXCEPTION, id);
    }
}
