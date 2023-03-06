package it.corso.mygym.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@Data
public class UserDto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDay;
    private boolean certifiedMedically;
    private boolean activeFlag;

}
