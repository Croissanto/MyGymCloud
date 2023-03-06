package it.corso.mygym.service;

import it.corso.mygym.model.Subscription;
import it.corso.mygym.model.dto.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {

    Subscription save(SubscriptionDto dto);

    Subscription findById(long id);

    List<Subscription> findAll();

    boolean deleteById(long id);

    Subscription update(long id, SubscriptionDto dto);
}
