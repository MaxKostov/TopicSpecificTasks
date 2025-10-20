package BufferingIterator;

import java.util.*;

public class BufferingIterator <T> implements Iterator<List<T>> {
    private final Iterator<T> iter;
    private final int batchSize;

    public BufferingIterator(Iterator<T> iter, int batchSize) {
        this.batchSize = batchSize;
        this.iter = iter;
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public List<T> next() {
        if (!iter.hasNext()) {
            throw new NoSuchElementException();
        }

        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < batchSize && iter.hasNext(); i++) {
            returnList.add(iter.next());
        }

        return returnList;
    }
}
