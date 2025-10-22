package FlatteningIterator;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlatteningIteratorTest {
    @Test
    void iteratorMergeTest() {
        Iterator<Integer> iterator = new FlatteningIterator<Integer>(
                List.of(42).iterator(),
                List.of(-4).iterator(),
                List.of(999).iterator()
        );

        assertTrue(iterator.hasNext());
        assertEquals(42, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(-4, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(999, iterator.next());
        assertFalse(iterator.hasNext());
    }
}