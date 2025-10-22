package ZippingIterator;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

class ZipIteratorTest {

    @Test
    void testZipWithEqualLengthLists() {
        List<Integer> listA = Arrays.asList(1, 2, 3);
        List<String> listB = Arrays.asList("A", "B", "C");
        BiFunction<Integer, String, String> zipper = (a, b) -> a + b;

        ZipIterator<Integer, String, String> zipIterator = new ZipIterator<>(listA.iterator(), listB.iterator(), zipper);

        assertTrue(zipIterator.hasNext());
        assertEquals("1A", zipIterator.next());

        assertTrue(zipIterator.hasNext());
        assertEquals("2B", zipIterator.next());

        assertTrue(zipIterator.hasNext());
        assertEquals("3C", zipIterator.next());

        assertFalse(zipIterator.hasNext());
    }

    @Test
    void testZipStopsWhenFirstIteratorIsShorter() {
        List<Integer> listA = Arrays.asList(1, 2);
        List<String> listB = Arrays.asList("A", "B", "C");
        BiFunction<Integer, String, String> zipper = (a, b) -> a + b;

        ZipIterator<Integer, String, String> zipIterator = new ZipIterator<>(listA.iterator(), listB.iterator(), zipper);

        List<String> results = new ArrayList<>();
        zipIterator.forEachRemaining(results::add);

        assertEquals(2, results.size());
        assertEquals(Arrays.asList("1A", "2B"), results);
        assertFalse(zipIterator.hasNext());
    }

    @Test
    void testZipStopsWhenSecondIteratorIsShorter() {
        List<Integer> listA = Arrays.asList(1, 2, 3);
        List<String> listB = Arrays.asList("A", "B");
        BiFunction<Integer, String, String> zipper = (a, b) -> a + b;

        ZipIterator<Integer, String, String> zipIterator = new ZipIterator<>(listA.iterator(), listB.iterator(), zipper);

        List<String> results = new ArrayList<>();
        zipIterator.forEachRemaining(results::add);

        assertEquals(2, results.size());
        assertEquals(Arrays.asList("1A", "2B"), results);
        assertFalse(zipIterator.hasNext());
    }

    @Test
    void testZipWithFirstIteratorEmpty() {
        List<Integer> listA = Collections.emptyList();
        List<String> listB = Arrays.asList("A", "B", "C");
        BiFunction<Integer, String, String> zipper = (a, b) -> a + b;

        ZipIterator<Integer, String, String> zipIterator = new ZipIterator<>(listA.iterator(), listB.iterator(), zipper);

        assertFalse(zipIterator.hasNext());
    }

    @Test
    void testZipWithSecondIteratorEmpty() {
        List<Integer> listA = Arrays.asList(1, 2, 3);
        List<String> listB = Collections.emptyList();
        BiFunction<Integer, String, String> zipper = (a, b) -> a + b;

        ZipIterator<Integer, String, String> zipIterator = new ZipIterator<>(listA.iterator(), listB.iterator(), zipper);

        assertFalse(zipIterator.hasNext());
    }

    @Test
    void testZipWithBothIteratorsEmpty() {
        List<Integer> listA = Collections.emptyList();
        List<String> listB = Collections.emptyList();
        BiFunction<Integer, String, String> zipper = (a, b) -> a + b;

        ZipIterator<Integer, String, String> zipIterator = new ZipIterator<>(listA.iterator(), listB.iterator(), zipper);

        assertFalse(zipIterator.hasNext());
    }

    @Test
    void testNextThrowsExceptionWhenNoMoreElements() {
        List<Integer> listA = Collections.singletonList(1);
        List<String> listB = Collections.singletonList("A");
        BiFunction<Integer, String, String> zipper = (a, b) -> a + b;

        ZipIterator<Integer, String, String> zipIterator = new ZipIterator<>(listA.iterator(), listB.iterator(), zipper);

        zipIterator.next();

        assertFalse(zipIterator.hasNext());
        assertThrows(NoSuchElementException.class, zipIterator::next);
    }

    @Test
    void testWithNullElementsInLists() {
        List<Integer> listA = Arrays.asList(1, null, 3);
        List<String> listB = Arrays.asList("A", "B", null);
        BiFunction<Integer, String, String> zipper = (a, b) -> String.valueOf(a) + String.valueOf(b);

        ZipIterator<Integer, String, String> zipIterator = new ZipIterator<>(listA.iterator(), listB.iterator(), zipper);

        assertEquals("1A", zipIterator.next());
        assertEquals("nullB", zipIterator.next());
        assertEquals("3null", zipIterator.next());
        assertFalse(zipIterator.hasNext());
    }

    private static class Pair<K, V> {
        K key;
        V value;
        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Test
    void testZipToCreateCustomObjects() {
        List<Integer> listA = Arrays.asList(1, 2);
        List<String> listB = Arrays.asList("One", "Two");
        BiFunction<Integer, String, Pair<Integer, String>> zipper = Pair::new;

        Iterator<Pair<Integer, String>> zipIterator = new ZipIterator<>(listA.iterator(), listB.iterator(), zipper);

        assertTrue(zipIterator.hasNext());
        Pair<Integer, String> p1 = zipIterator.next();
        assertEquals(1, p1.key);
        assertEquals("One", p1.value);


        assertTrue(zipIterator.hasNext());
        Pair<Integer, String> p2 = zipIterator.next();
        assertEquals(2, p2.key);
        assertEquals("Two", p2.value);

        assertFalse(zipIterator.hasNext());
    }
}