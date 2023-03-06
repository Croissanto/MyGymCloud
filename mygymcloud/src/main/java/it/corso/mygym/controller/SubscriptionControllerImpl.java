package it.corso.mygym.controller;

import it.corso.mygym.model.Subscription;
import it.corso.mygym.model.dto.SubscriptionDto;
import it.corso.mygym.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subscriptions")
@RestController
public class SubscriptionControllerImpl implements SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionControllerImpl(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    @PostMapping()
    public ResponseEntity<Subscription> save(SubscriptionDto dto) {
        Subscription subscription = subscriptionService.save(dto);
        return new ResponseEntity<>(subscription, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/subscriptions/{id}")
    public ResponseEntity<Subscription> findById(@PathVariable("id") long id) {
        Subscription subscription = subscriptionService.findById(id);
        return new ResponseEntity<>(subscription, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<Subscription>> findAll() {
        List<Subscription> subscriptionList = subscriptionService.findAll();
        return new ResponseEntity<>(subscriptionList, HttpStatus.FOUND);
    }
}
