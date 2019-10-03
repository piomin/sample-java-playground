package pl.piomin.service.background;

import com.google.common.collect.Streams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.piomin.services.playground.model.Person;
import pl.piomin.services.playground.model.PersonAddress;
import pl.piomin.services.playground.model.PersonDTO;

import java.util.stream.Stream;

public class GuavaTests {

    @Test
    public void testZip() {
        Stream<Person> s1 = Stream.of(
                new Person(1, "John", "Smith"),
                new Person(2, "Tom", "Hamilton"),
                new Person(3, "Paul", "Walker"));
        Stream<PersonAddress> s2 = Stream.of(
                new PersonAddress(1, "London", "Street1", "100"),
                new PersonAddress(2, "Manchester", "Street1", "101"),
                new PersonAddress(3, "London", "Street2", "200"));
        Stream<PersonDTO> s3 = Streams.zip(s1, s2, (p, pa) -> PersonDTO.builder()
                .id(p.getId())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .city(pa.getCity())
                .street(pa.getStreet())
                .houseNo(pa.getHouseNo()).build());
        s3.forEach(dto -> {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getFirstName());
            Assertions.assertNotNull(dto.getCity());
        });
    }

    @Test
    public void testMultipleConcat() {
        Stream<Integer> s1 = Stream.of(1, 2, 3);
        Stream<Integer> s2 = Stream.of(4, 5, 6);
        Stream<Integer> s3 = Stream.of(7, 8, 9);
        Stream<Integer> s4 = Streams.concat(s1, s2, s3);
        Assertions.assertEquals(9, s4.count());
    }

}
