package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new Product(price).oneOf().price());
    }
    @Test
    void differentProductHaveDifferentCodes() {
        final BigDecimal price = new BigDecimal("2.49");
        Product product1 = new Product(price);
        Product product2 = new Product(price);
        assertNotEquals(product1.code(), product2.code());
    }
    @Test
    void itemsOfProductHaveSameCode() {
        final BigDecimal price = new BigDecimal("2.49");
        Product product1 = new Product(price);
        Item item1 = product1.oneOf();
        Item item2 = product1.oneOf();
        assertNotEquals(item1, item2);
        assertEquals(item1.code(), item2.code());
    }
}