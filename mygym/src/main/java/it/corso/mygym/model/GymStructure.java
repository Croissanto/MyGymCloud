package it.corso.mygym.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class GymStructure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String name;
    private String address;
    private String email;
    private String pIva;
    @OneToMany(mappedBy = "gym")
    private List<Subscription> availableSubscriptions;


}
