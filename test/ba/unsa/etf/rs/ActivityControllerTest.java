package ba.unsa.etf.rs;

import ba.unsa.etf.rs.models.Activity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class ActivityControllerTest {

    @Test
    public void ConstructorTest() {
        Activity ac = new Activity(1, "Descr", "Assig", "Rev", LocalDate.of(2020, Month.AUGUST, 5));
        assertEquals(1, ac.getId());
        assertEquals("Descr", ac.getDescription());
        assertEquals("Assig", ac.getAssignment());
        assertEquals("Rev", ac.getTeacherReview());
        assertEquals(LocalDate.of(2020, Month.AUGUST, 5), ac.getDateOfLecture());
    }

    @Test
    public void ConstructorWitouthParametersTest() {
        Activity ac = new Activity();
        assertEquals(1, ac.getId());
        assertEquals("", ac.getDescription());
        assertEquals("", ac.getAssignment());
        assertEquals("", ac.getTeacherReview());
        assertEquals(LocalDate.now(), ac.getDateOfLecture());
    }


}
