package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kata.supermarket.Item;

public class DiscountsAggregatorImpl implements DiscountsAggregator{
    private final Set<DiscountScheme> discountSchemes = new HashSet<>();
    @Override
    public BigDecimal calculateDiscount(List<Item> items) {
        return discountSchemes.stream()
                        .map(discountScheme ->  discountScheme.calculate(items))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void addDiscountScheme(DiscountScheme discountScheme) {
        discountSchemes.add(discountScheme);
    }
}