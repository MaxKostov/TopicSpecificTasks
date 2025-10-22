package ZippingIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

public class ZipIterator<A, B, C> implements Iterator<C> {
    private final Iterator<? extends A> iterA;
    private final Iterator<? extends B> iterB;
    private final BiFunction<A, B, C> function;

    public ZipIterator(Iterator<? extends A> iterA, Iterator<? extends B> iterB, BiFunction<A, B, C> function) {
        this.iterA = iterA;
        this.iterB = iterB;
        this.function = function;
    }


    @Override
    public boolean hasNext() {
        return iterA.hasNext() && iterB.hasNext();
    }

    @Override
    public C next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return function.apply(iterA.next(), iterB.next());
    }
}
