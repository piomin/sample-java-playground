package pl.piomin.service.background;

import one.util.streamex.StreamEx;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.piomin.services.playground.model.Person;
import pl.piomin.services.playground.model.PersonAddress;
import pl.piomin.services.playground.model.PersonDTO;

import java.util.List;
import java.util.Map;

public class StreamExTests {

    @Test
    public void testZip() {
        StreamEx<Person> s1 = StreamEx.of(
                new Person(1, "John", "Smith"),
                new Person(2, "Tom", "Hamilton"),
                new Person(3, "Paul", "Walker"));
        StreamEx<PersonAddress> s2 = StreamEx.of(
                new PersonAddress(1, "London", "Street1", "100"),
                new PersonAddress(2, "Manchester", "Street1", "101"),
                new PersonAddress(3, "London", "Street2", "200"));
        StreamEx<PersonDTO> s3 = s1.zipWith(s2, (p, pa) -> PersonDTO.builder()
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
    public void testGrouping() {
        StreamEx<PersonDTO> s1 = StreamEx.of(
            PersonDTO.builder().id(1).firstName("John").lastName("Smith").city("London").street("Street1").houseNo("100").build(),
            PersonDTO.builder().id(2).firstName("Tom").lastName("Hamilton").city("Manchester").street("Street1").houseNo("101").build(),
            PersonDTO.builder().id(3).firstName("Paul").lastName("Walker").city("London").street("Street2").houseNo("200").build(),
            PersonDTO.builder().id(4).firstName("Joan").lastName("Collins").city("Manchester").street("Street2").houseNo("201").build()
        );
        Map<String, List<PersonDTO>> m = s1.groupingBy(PersonDTO::getCity);
        Assertions.assertNotNull(m.get("London"));
        Assertions.assertTrue(m.get("London").size() == 2);
        Assertions.assertNotNull(m.get("Manchester"));
        Assertions.assertTrue(m.get("Manchester").size() == 2);
    }

    @Test
    public void testPartitioning() {
        StreamEx<PersonDTO> s1 = StreamEx.of(
                PersonDTO.builder().id(1).firstName("John").lastName("Smith").city("London").street("Street1").houseNo("100").build(),
                PersonDTO.builder().id(2).firstName("Tom").lastName("Hamilton").city("Manchester").street("Street1").houseNo("101").build(),
                PersonDTO.builder().id(3).firstName("Paul").lastName("Walker").city("London").street("Street2").houseNo("200").build(),
                PersonDTO.builder().id(4).firstName("Joan").lastName("Collins").city("Manchester").street("Street2").houseNo("201").build()
        );
        Map<Boolean, List<PersonDTO>> m = s1.partitioningBy(dto -> dto.getStreet().equals("Street1"));
        Assertions.assertTrue(m.get(true).size() == 2);
        Assertions.assertTrue(m.get(false).size() == 2);
    }

    @Test
    public void testPairing() {
        StreamEx<Integer> s1 = StreamEx.of(1, 2, 1, 2, 1);
        StreamEx<Integer> s2 = s1.pairMap(Integer::sum);
        s2.forEach(i -> Assertions.assertEquals(3, i));
    }

}
