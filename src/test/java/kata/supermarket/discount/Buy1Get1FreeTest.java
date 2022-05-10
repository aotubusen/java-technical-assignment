package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import kata.supermarket.Item;
import kata.supermarket.Product;
import kata.supermarket.WeighedProduct;

import static org.junit.jupiter.api.Assertions.*;

class Buy1Get1FreeTest {

    @Test
    void shouldReturnZeroWhenNoItems(){

        List<Item> items = new ArrayList<>();
        Buy1Get1Free bogof = new Buy1Get1Free("");

        assertEquals(BigDecimal.ZERO, bogof.calculate(items));
    }
    @Test
    void shouldDiscountPriceByHalf(){
        List<Item> items = new ArrayList<>();
        Product product = new Product(new BigDecimal(2));
        Buy1Get1Free bogof = new Buy1Get1Free(product.code());
        items.add(product.oneOf());
        items.add(product.oneOf());

        assertEquals(2, bogof.calculate(items).doubleValue());
    }

    @Test
    void shouldReturnZeroWhenOneProductAdded(){
        List<Item> items = new ArrayList<>();
        Product product = new Product(new BigDecimal(2));
        Buy1Get1Free bogof = new Buy1Get1Free(product.code());

        items.add(product.oneOf());

        assertEquals(0, bogof.calculate(items).doubleValue());
    }

    @Test
    void shouldReturnZeroWhenTwoDifferentProductsAdded(){
        List<Item> items = new ArrayList<>();
        Product product = new Product(new BigDecimal(2));
        Product product2 = new Product(new BigDecimal(2));
        Buy1Get1Free bogof = new Buy1Get1Free(product.code());

        items.add(product.oneOf());
        items.add(product2.oneOf());

        assertEquals(0, bogof.calculate(items).doubleValue());
    }

    @Test
    void shouldNotDiscountItemByWeightProducts(){
        List<Item> items = new ArrayList<>();
        WeighedProduct weighedProduct = new WeighedProduct(new BigDecimal(1.5));
        Buy1Get1Free bogof = new Buy1Get1Free(weighedProduct.code());

        items.add(weighedProduct.weighing(new BigDecimal(10)));
        items.add(weighedProduct.weighing(new BigDecimal(15)));

        assertEquals(0, bogof.calculate(items).doubleValue());
    }

    @Test
    void shouldDiscountOnlyItemByUnitProducts(){
        List<Item> items = new ArrayList<>();
        Product product = new Product(new BigDecimal(2));
        Buy1Get1Free bogof = new Buy1Get1Free(product.code());
        WeighedProduct weighedProduct = new WeighedProduct(new BigDecimal(1.5));
        items.add(product.oneOf());
        items.add(product.oneOf());
        items.add(weighedProduct.weighing(new BigDecimal(10)));
        items.add(weighedProduct.weighing(new BigDecimal(15)));

        assertEquals(2, bogof.calculate(items).doubleValue());
    }


    @DisplayName("calculating discount...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void bogofTest(String description, double discount, double price, int productCount){
        List<Item> items = new ArrayList<>();
        Product product = new Product(new BigDecimal(price));
        Buy1Get1Free bogof = new Buy1Get1Free(product.code());
        for (int i = 0; i < productCount; i++) {
            items.add(product.oneOf());
        }

        assertEquals(discount, bogof.calculate(items).doubleValue());
    }
    static Stream<Arguments> bogofTest() {
        return Stream.of(
                        Arguments.of("1 Item", 0, 0.25, 1),
                        Arguments.of("2 Items", 0.25, 0.25, 2),
                        Arguments.of("3 Items", 0.25, 0.25, 3),
                        Arguments.of("5 Items", 0.5, 0.25, 5)
        );
    }
}