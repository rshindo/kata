package kata.ex01.model;

import kata.ex01.util.HolidayUtils;

import java.time.LocalDate;
import java.util.Set;

public class HolidayDiscount implements Discount {

    private static final Set<VehicleFamily> TARGET_VEHICLE_FAMILY =
            Set.of(VehicleFamily.STANDARD, VehicleFamily.MINI, VehicleFamily.MOTORCYCLE);

    @Override
    public boolean match(HighwayDrive drive) {

        // 普通車、軽自動車、二輪車限定
        if (!TARGET_VEHICLE_FAMILY.contains(drive.getVehicleFamily())) {
            return false;
        }
        // 地方部のみ
        if (drive.getRouteType() != RouteType.RURAL) {
            return false;
        }
        LocalDate enteredAt = drive.getEnteredAt().toLocalDate();
        LocalDate exitedAt = drive.getExitedAt().toLocalDate();
        LocalDate d = enteredAt;
        // 土日祝のみ
        // 入口、出口、その間の日のいずれかが土日祝ならOK
        while (d.isBefore(exitedAt) || d.isEqual(exitedAt)) {
            if (HolidayUtils.isHoliday(d)) {
                return true;
            }
            d = d.plusDays(1);
        }
        return false;
    }

    @Override
    public long discountRate(HighwayDrive drive) {
        return 30;
    }
}
