package com.app.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDTO {

    private int id;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return String.format("PersonDTO{id=%d, name='%s', lastName='%s'}", id, firstName, lastName);
    }

}
