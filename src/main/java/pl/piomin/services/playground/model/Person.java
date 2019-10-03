package pl.piomin.services.playground.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Person {

    @NonNull
    private Integer id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private int age;

}
