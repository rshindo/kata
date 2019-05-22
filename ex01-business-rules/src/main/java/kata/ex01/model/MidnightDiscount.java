package kata.ex01.model;

import kata.ex01.util.TimeRange;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class MidnightDiscount implements Discount {

    private static final TimeRange MIDNIGHT_RANGE = new TimeRange(LocalTime.MIDNIGHT, LocalTime.of(4, 0));

    @Override
    public boolean match(HighwayDrive drive) {
        LocalDateTime enteredAt = drive.getEnteredAt();
        LocalDateTime exitedAt = drive.getExitedAt();
        LocalDateTime d = enteredAt;
        // 入口、出口のいずれかが対象時間内
        // もしくは対象時間をまたがった利用
        while (d.isBefore(exitedAt) || d.equals(exitedAt)) {
            if (MIDNIGHT_RANGE.include(d.toLocalTime())) {
                return true;
            }
            d = d.plusHours(1);
        }
        return false;
    }

    @Override
    public long discountRate(HighwayDrive drive) {
        return 30;
    }
}
