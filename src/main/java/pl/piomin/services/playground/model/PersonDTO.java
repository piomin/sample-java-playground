package pl.piomin.services.playground.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private int age;
    private String city;
    private String street;
    private String houseNo;
    private int flatNo;

}
