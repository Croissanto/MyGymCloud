package it.corso.mygym.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GymStructureDto {

    private long id;
    private String name;
    private String address;
    private String email;
    private String pIva;
    private List<Integer> availableSubscriptionsId;
}
