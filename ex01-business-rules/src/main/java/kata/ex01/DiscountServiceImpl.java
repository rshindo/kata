package kata.ex01;

import kata.ex01.model.Discount;
import kata.ex01.model.HighwayDrive;

import java.util.Comparator;
import java.util.List;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {

    private List<Discount> discounts;

    public DiscountServiceImpl(List<Discount> discounts) {
        this.discounts = discounts;
    }

    @Override
    public long calc(HighwayDrive drive) {
        return discounts.stream()
                .filter(d -> d.match(drive))
                .map(d -> d.discountRate(drive))
                .max(Comparator.naturalOrder())
                .orElse(0L);
    }
}
