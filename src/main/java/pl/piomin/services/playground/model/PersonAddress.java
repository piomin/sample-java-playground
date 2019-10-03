package pl.piomin.services.playground.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PersonAddress {

    @NonNull
    private Integer id;
    @NonNull
    private String city;
    @NonNull
    private String street;
    @NonNull
    private String houseNo;
    private int flatNo;

}
