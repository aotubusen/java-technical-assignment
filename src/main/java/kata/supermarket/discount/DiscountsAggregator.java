package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.List;

import kata.supermarket.Item;

public interface DiscountsAggregator {
    BigDecimal calculateDiscount(List<Item> items);
    void addDiscountScheme(DiscountScheme discountScheme);
}