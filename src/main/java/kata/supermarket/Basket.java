package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kata.supermarket.discount.DiscountsAggregator;

public class Basket {
    private final List<Item> items;
    private DiscountsAggregator discountsAggregator;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    public void applyDiscounts(final DiscountsAggregator discountsAggregator){
        this.discountsAggregator = discountsAggregator;
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of
         *  the discount calculations.
         *  It is not likely to be the best place to do those calculations.
         *  Think about how Basket could interact with something
         *  which provides that functionality.
         */
        private BigDecimal discounts() {
            if(discountsAggregator == null)
                return BigDecimal.ZERO;
            return discountsAggregator.calculateDiscount(items);
        }

        private BigDecimal calculate() {
            BigDecimal discount = discounts();
            BigDecimal subtotal = subtotal();
            if(discount.doubleValue() > subtotal.doubleValue()) throw new RuntimeException("Error : Discount greater that basket total");
            return subtotal.subtract(discount);
        }
    }
}