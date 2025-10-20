package FlatteningIterator;

import com.google.common.collect.Iterators;

import java.util.Iterator;

public class FlatteningIterator<T> implements Iterator<T> {
    Iterator<T> concatIterator;

    @SafeVarargs
    public FlatteningIterator(Iterator<T>... iterators) {
        for (Iterator<T> iter : iterators) {
            if (concatIterator == null) {
                concatIterator = iter;
            }
            concatIterator = Iterators.concat(concatIterator, iter);
        }
    }

    @Override
    public boolean hasNext() {
        return concatIterator.hasNext();
    }

    @Override
    public T next() {
        return concatIterator.next();
    }
}
