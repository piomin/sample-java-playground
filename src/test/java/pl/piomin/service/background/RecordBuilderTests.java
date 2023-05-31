package pl.piomin.service.background;

import org.junit.jupiter.api.Test;
import pl.piomin.services.playground.model.record.Person;
import pl.piomin.services.playground.model.record.PersonBuilder;

import static org.junit.jupiter.api.Assertions.*;

public class RecordBuilderTests {

    @Test
    void testPersonSimple() {
        Person p = PersonBuilder.builder().age(20).firstName("Test").lastName("Test").build();
        assertNotNull(p);
        assertEquals(20, p.age());
        assertEquals("Test", p.firstName());
        assertEquals("Test", p.lastName());
    }

    @Test
    void testPersonSimpleWithNull() {
        Person p = PersonBuilder.builder().age(20).firstName("Test").build();
        assertNotNull(p);
        assertEquals(20, p.age());
        assertEquals("Test", p.firstName());
        assertNull(p.lastName());
    }
}
