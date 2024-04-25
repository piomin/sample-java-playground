package pl.piomin.service.background;

import one.util.streamex.StreamEx;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.piomin.services.playground.model.EmployeePosition;
import pl.piomin.services.playground.model.PersonDTO;
import pl.piomin.services.playground.model.record.Employee;
import pl.piomin.services.playground.model.record.EmployeeBuilder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StreamTests {

    @Test
    void testGrouping() {
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
    void testPartitioning() {
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

    @Test
    void testGroupingWithCalculation() {
        Stream<Employee> s1 = Stream.of(
                EmployeeBuilder.builder().firstName("AAA").lastName("BBB").position("developer").salary(10000).build(),
                EmployeeBuilder.builder().firstName("AAB").lastName("BBC").position("architect").salary(15000).build(),
                EmployeeBuilder.builder().firstName("AAC").lastName("BBD").position("developer").salary(13000).build(),
                EmployeeBuilder.builder().firstName("AAD").lastName("BBE").position("tester").salary(7000).build(),
                EmployeeBuilder.builder().firstName("AAE").lastName("BBF").position("tester").salary(9000).build()
        );

        var m = s1.collect(Collectors.groupingBy(Employee::position, Collectors.summingInt(Employee::salary)));
        assertEquals(3, m.size());
        assertEquals(m.get("developer"), 23000);
    }

    @Test
    void testImmutability() {
        var l = Stream.of(null, "Green", "Yellow").toList();
        assertEquals(3, l.size());
        assertThrows(UnsupportedOperationException.class, () -> l.add("Red"));
        assertThrows(UnsupportedOperationException.class, () -> l.set(0, "Red"));
    }

    @Test
    void testMutability() {
        var l = Stream.of(null, "Green", "Yellow").collect(Collectors.toList());
        l.add("Red");
        assertEquals(4, l.size());
    }

    @Test
    void testUnmodifiable() {
        assertThrows(NullPointerException.class, () -> Stream.of(null, "Green", "Yellow").collect(Collectors.toUnmodifiableList()));
    }

    @Test
    void testEnums() {
        var x = EnumSet.of(
                EmployeePosition.SRE,
                EmployeePosition.ARCHITECT,
                EmployeePosition.DEVELOPER);
        long beg = System.nanoTime();
        for (int i = 0; i < 100_000_000; i++) {
            var es = EnumSet.allOf(EmployeePosition.class);
            es.containsAll(x);
        }
        long end = System.nanoTime();
        System.out.println(x.getClass() + ": " + (end - beg)/1e9);
    }

    @Test
    void testSet() {
        var x = Set.of(
                EmployeePosition.SRE,
                EmployeePosition.ARCHITECT,
                EmployeePosition.DEVELOPER);
        long beg = System.nanoTime();
        for (int i = 0; i < 100_000_000; i++) {
            var hs = Set.of(EmployeePosition.values());
            hs.containsAll(x);
        }
        long end = System.nanoTime();
        System.out.println(x.getClass() + ": " + (end - beg)/1e9);
    }

    @Test
    void mergeMapCount() {
        var map = new HashMap<Integer, Integer>();
        var nums = List.of(2, 3, 4, 2, 3, 5, 1, 3, 4, 4);
        nums.forEach(num -> map.merge(num, 1, Integer::sum));
        assertEquals(5, map.size());
        assertEquals(map.get(4), 3);
    }

    @Test
    void mergeMapSum() {
        var s1 = List.of(
                new Employee("AAA", "BBB", "developer", 10000),
                new Employee("AAB", "BBC", "architect", 15000),
                new Employee("AAC", "BBD", "developer", 13000),
                new Employee("AAD", "BBE", "tester", 7000),
                new Employee("AAE", "BBF", "tester", 9000)
        );
        var map = new HashMap<String, Integer>();
        s1.forEach(emp -> map.merge(emp.position(), emp.salary(), Integer::sum));
        assertEquals(3, map.size());
        assertEquals(map.get("developer"), 23000);
    }
}
