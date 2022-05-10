package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import kata.supermarket.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
class DiscountsAggregatorImplTest {

    @Test
    void shouldReturnExpectedDiscount(){
        DiscountScheme scheme1 = mock(DiscountScheme.class);
        when(scheme1.calculate(any())).thenReturn(BigDecimal.valueOf(0.5));
        DiscountsAggregator discountsAggregator = new DiscountsAggregatorImpl();
        discountsAggregator.addDiscountScheme(scheme1);
        assertEquals(BigDecimal.valueOf(0.5), discountsAggregator.calculateDiscount(Arrays.asList(new Product(BigDecimal.valueOf(1)).oneOf())));
    }

    @Test
    void multipleSchemesShouldReturnExpectedDiscount(){
        DiscountScheme scheme1 = mock(DiscountScheme.class);
        when(scheme1.calculate(any())).thenReturn(BigDecimal.valueOf(0.5));
        DiscountScheme scheme2 = mock(DiscountScheme.class);
        when(scheme2.calculate(any())).thenReturn(BigDecimal.valueOf(0.2));
        DiscountsAggregator discountsAggregator = new DiscountsAggregatorImpl();
        discountsAggregator.addDiscountScheme(scheme1);
        discountsAggregator.addDiscountScheme(scheme2);
        assertEquals(BigDecimal.valueOf(0.7), discountsAggregator.calculateDiscount(Arrays.asList(new Product(BigDecimal.valueOf(1)).oneOf())));
    }
}