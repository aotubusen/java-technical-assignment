package kata.supermarket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeighedProductTest {

    @ParameterizedTest
    @MethodSource
    void itemFromWeighedProductHasExpectedUnitPrice(String pricePerKilo, String weightInKilos, String expectedPrice) {
        final WeighedProduct weighedProduct = new WeighedProduct(new BigDecimal(pricePerKilo));
        final Item weighedItem = weighedProduct.weighing(new BigDecimal(weightInKilos));
        assertEquals(new BigDecimal(expectedPrice), weighedItem.price());
    }

    static Stream<Arguments> itemFromWeighedProductHasExpectedUnitPrice() {
        return Stream.of(
                Arguments.of("100.00", "1.00", "100.00"),
                Arguments.of("100.00", "0.33333", "33.33"),
                Arguments.of("100.00", "0.33335", "33.34"),
                Arguments.of("100.00", "0", "0.00")
        );
    }
    @Test
    void differentProductHaveDifferentCodes() {
        final BigDecimal price = new BigDecimal("2.49");
        WeighedProduct product1 = new WeighedProduct(price);
        WeighedProduct product2 = new WeighedProduct(price);
        assertNotEquals(product1.code(), product2.code());
    }
    @Test
    void itemsOfProductHaveSameCode() {
        final BigDecimal price = new BigDecimal("2.49");
        WeighedProduct product1 = new WeighedProduct(price);
        Item item1 = product1.weighing(BigDecimal.valueOf(10));
        Item item2 = product1.weighing(BigDecimal.valueOf(12));
        assertNotEquals(item1, item2);
        assertEquals(item1.code(), item2.code());
    }

}