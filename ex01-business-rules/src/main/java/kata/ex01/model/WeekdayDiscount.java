package kata.ex01.model;

import kata.ex01.util.HolidayUtils;
import kata.ex01.util.TimeRange;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class WeekdayDiscount implements Discount {

    private static final TimeRange MORNING_RANGE = new TimeRange(LocalTime.of(6, 0), LocalTime.of(9, 0));

    private static final TimeRange NIGHT_RANGE = new TimeRange(LocalTime.of(17, 0), LocalTime.of(20, 0));

    @Override
    public boolean match(HighwayDrive drive) {

        // 地方部のみ
        if (drive.getRouteType() != RouteType.RURAL) {
            return false;
        }

        LocalDateTime enteredAt = drive.getEnteredAt();
        if (HolidayUtils.isWeekday(enteredAt.toLocalDate())) {
            // 入口が平日6〜9時
            if (MORNING_RANGE.include(enteredAt.toLocalTime())) {
                return true;
            }
            // 入口が平日17〜20時
            if (NIGHT_RANGE.include(enteredAt.toLocalTime())) {
                return true;
            }
        }

        LocalDateTime exitedAt = drive.getExitedAt();
        if (HolidayUtils.isWeekday(exitedAt.toLocalDate())) {
            // 出口が平日6〜9時
            if (MORNING_RANGE.include(exitedAt.toLocalTime())) {
                return true;
            }
            // 出口が平日17〜20時
            if (NIGHT_RANGE.include(exitedAt.toLocalTime())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public long discountRate(HighwayDrive drive) {
        int countPerMonth = drive.getDriver().getCountPerMonth();
        if (5 <= countPerMonth && countPerMonth <= 9) {
            return 30;
        } else if (countPerMonth >= 10) {
            return 50;
        }
        return 0;
    }
}
