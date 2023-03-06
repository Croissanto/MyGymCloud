package it.corso.mygym.model.dto;

import it.corso.mygym.model.enums.SubscriptionType;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SubscriptionDto {

    private long id;
    private SubscriptionType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;
    private boolean isPayed;
    private long userId;
    private long gymId;
}
