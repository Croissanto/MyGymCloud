package it.corso.mygym.model;


import it.corso.mygym.model.enums.SubscriptionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity

public class Subscription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private SubscriptionType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;
    private boolean isPayed;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "gym_id")
    private GymStructure gym;





}
