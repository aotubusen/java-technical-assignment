package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.List;

import kata.supermarket.Item;

public class Buy1Get1Free implements DiscountScheme{
    @Override public BigDecimal calculate(final List<Item> items) {
        if(items == null || items.isEmpty())
            return BigDecimal.ZERO;
        return null;
    }
}