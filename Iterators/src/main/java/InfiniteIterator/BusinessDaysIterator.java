package InfiniteIterator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Iterator;

public class BusinessDaysIterator implements Iterator<LocalDate> {
    private LocalDate currentDate;

    public BusinessDaysIterator(LocalDate dateToInsert) {
        this.currentDate = dateToInsert;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public LocalDate next() {
        currentDate = currentDate.plusDays(1);
        switch (currentDate.getDayOfWeek()) {
            case SATURDAY -> {
                currentDate = currentDate.plusDays(2);
                return currentDate;
            }
            case SUNDAY -> {
                currentDate = currentDate.plusDays(1);
                return currentDate;
            }
            default -> {
                return currentDate;
            }
        }
    }
}
