package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.List;

import kata.supermarket.Item;

public interface DiscountScheme {
    BigDecimal calculate(final List<Item> items);
}