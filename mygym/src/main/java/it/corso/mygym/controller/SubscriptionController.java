package it.corso.mygym.controller;

import it.corso.mygym.model.Subscription;
import it.corso.mygym.model.dto.SubscriptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subscriptions")
public interface SubscriptionController {

    @PostMapping()
    ResponseEntity<Subscription> save(@RequestBody SubscriptionDto dto);

    @GetMapping("/{id}")
    ResponseEntity<Subscription> findById(@PathVariable("id") long id);

    @GetMapping("/all")
    ResponseEntity<List<Subscription>> findAll();
}
