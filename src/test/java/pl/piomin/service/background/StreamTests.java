package pl.piomin.service.background;

import one.util.streamex.StreamEx;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.piomin.services.playground.model.PersonDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTests {

    @Test
    public void testGrouping() {
        Stream<PersonDTO> s1 = Stream.of(
                PersonDTO.builder().id(1).firstName("John").lastName("Smith").city("London").street("Street1").houseNo("100").build(),
                PersonDTO.builder().id(2).firstName("Tom").lastName("Hamilton").city("Manchester").street("Street1").houseNo("101").build(),
                PersonDTO.builder().id(3).firstName("Paul").lastName("Walker").city("London").street("Street2").houseNo("200").build(),
                PersonDTO.builder().id(4).firstName("Joan").lastName("Collins").city("Manchester").street("Street2").houseNo("201").build()
        );
        Map<String, List<PersonDTO>> m = s1.collect(Collectors.groupingBy(PersonDTO::getCity));
        Assertions.assertNotNull(m.get("London"));
        Assertions.assertTrue(m.get("London").size() == 2);
        Assertions.assertNotNull(m.get("Manchester"));
        Assertions.assertTrue(m.get("Manchester").size() == 2);
    }

    @Test
    public void testPartitioning() {
        Stream<PersonDTO> s1 = Stream.of(
                PersonDTO.builder().id(1).firstName("John").lastName("Smith").city("London").street("Street1").houseNo("100").build(),
                PersonDTO.builder().id(2).firstName("Tom").lastName("Hamilton").city("Manchester").street("Street1").houseNo("101").build(),
                PersonDTO.builder().id(3).firstName("Paul").lastName("Walker").city("London").street("Street2").houseNo("200").build(),
                PersonDTO.builder().id(4).firstName("Joan").lastName("Collins").city("Manchester").street("Street2").houseNo("201").build()
        );
        Map<Boolean, List<PersonDTO>> m = s1.collect(Collectors.partitioningBy(dto -> dto.getStreet().equals("Street1")));
        Assertions.assertTrue(m.get(true).size() == 2);
        Assertions.assertTrue(m.get(false).size() == 2);
    }

}
