package kata.ex01.util;

import java.time.LocalTime;

public class TimeRange {

    LocalTime min;

    LocalTime max;

    public TimeRange(LocalTime min, LocalTime max) {
        this.min = min;
        this.max = max;
    }

    public boolean include(LocalTime t) {
        return isAfterOrEqualTo(t) && isBeforeOrEqualTo(t);
    }

    private boolean isAfterOrEqualTo(LocalTime t) {
        return t.isAfter(min) || t.equals(min);
    }

    private boolean isBeforeOrEqualTo(LocalTime t) {
        return t.isBefore(max) || t.equals(max);
    }

    @Override
    public String toString() {
        return "TimeRange{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
