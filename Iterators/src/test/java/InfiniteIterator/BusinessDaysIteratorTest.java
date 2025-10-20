package InfiniteIterator;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BusinessDaysIteratorTest {
    @Test
    void nextBusinessDayTest() {
        Iterator<LocalDate> iter = new BusinessDaysIterator(LocalDate.of(2025, 10, 20));
        assertEquals(LocalDate.of(2025, 10, 21), iter.next());
        assertEquals(LocalDate.of(2025, 10, 22), iter.next());
    }

    @Test
    void nextBusinessDayStartFromWeekendTest() {
        Iterator<LocalDate> iter = new BusinessDaysIterator(LocalDate.of(2025, 10, 25));
        Iterator<LocalDate> iter2 = new BusinessDaysIterator(LocalDate.of(2025, 10, 26));
        assertEquals(LocalDate.of(2025, 10, 27), iter.next());
        assertEquals(LocalDate.of(2025, 10, 28), iter.next());

        assertEquals(LocalDate.of(2025, 10, 27), iter2.next());
        assertEquals(LocalDate.of(2025, 10, 28), iter2.next());
    }

    @Test
    void nextBusinessDayStartFromFridayTest() {
        Iterator<LocalDate> iter = new BusinessDaysIterator(LocalDate.of(2025, 10, 24));

        assertEquals(LocalDate.of(2025, 10, 27), iter.next());
        assertEquals(LocalDate.of(2025, 10, 28), iter.next());
    }

}