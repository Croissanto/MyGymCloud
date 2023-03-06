package it.corso.mygym.service;

import it.corso.mygym.Constants;
import it.corso.mygym.model.Subscription;
import it.corso.mygym.model.dto.SubscriptionDto;
import it.corso.mygym.model.exceptions.UserNotFoundException;
import it.corso.mygym.repositories.SubscriptionRepository;
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
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepo;

    @Override
    public Subscription save(SubscriptionDto dto) {
        ModelMapper mapper = new ModelMapper();
        Subscription sub = mapper.map(dto, Subscription.class);
        return subscriptionRepo.save(sub);
    }

    @Override
    public Subscription findById(long id) {
        Optional<Subscription> opt = subscriptionRepo.findById(id);
        return opt.orElse(null);
    }

    @Override
    public List<Subscription> findAll() {
        return subscriptionRepo.findAll();
    }

    @Override
    public boolean deleteById(long id) {
        try {
            subscriptionRepo.deleteById(id);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Subscription update(long id, SubscriptionDto dto) {
        Optional<Subscription> opt = subscriptionRepo.findById(id);
        dto.setId(id);
        if (opt.isPresent()) {
            Subscription subscription = opt.get();
            copyNonNullProperties(dto, subscription);
            dto.setId(id);
            return subscriptionRepo.saveAndFlush(subscription);

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
        if (subscriptionRepo.findById(id).isEmpty()) throw new UserNotFoundException(Constants.USER_NOT_FOUND_EXCEPTION, id);
    }

}
