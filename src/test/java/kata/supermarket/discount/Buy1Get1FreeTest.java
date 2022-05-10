package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import kata.supermarket.Item;
import kata.supermarket.Product;

import static org.junit.jupiter.api.Assertions.*;

class Buy1Get1FreeTest {

    @Test
    void shouldReturnZeroWhenNoItems(){

        List<Item> items = new ArrayList<>();
        Buy1Get1Free bogof = new Buy1Get1Free();

        assertEquals(BigDecimal.ZERO, bogof.calculate(items));
    }
    @Test
    void shouldDiscountPriceByHalf(){
        List<Item> items = new ArrayList<>();
        Product product = new Product(new BigDecimal(2));
        Buy1Get1Free bogof = new Buy1Get1Free();
        items.add(product.oneOf());
        items.add(product.oneOf());

        assertEquals(2, bogof.calculate(items).doubleValue());
    }

    @Test
    void shouldReturnZeroWhenOneProductAdded(){
        List<Item> items = new ArrayList<>();
        Product product = new Product(new BigDecimal(2));
        Buy1Get1Free bogof = new Buy1Get1Free();

        items.add(product.oneOf());

        assertEquals(0, bogof.calculate(items).doubleValue());
    }
}