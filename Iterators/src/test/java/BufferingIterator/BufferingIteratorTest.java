package BufferingIterator;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BufferingIteratorTest {
    @Test
    void defaultUsageTest() {
        Iterator<List<Integer>> iter = new BufferingIterator<Integer>(
                List.of(1, 2, 3, 4, 5).iterator(),
                3
        );

        List<Integer> batch1 = iter.next();
        assertEquals(List.of(1, 2, 3), batch1);
        assertTrue(iter.hasNext());
        List<Integer> batch2 = iter.next();
        assertEquals(List.of(4, 5), batch2);
        assertFalse(iter.hasNext());
    }
}