package kata.ex01.model;

public interface Discount {

    boolean match(HighwayDrive drive);

    long discountRate(HighwayDrive drive);

}
