package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import kata.supermarket.Item;

import static org.junit.jupiter.api.Assertions.*;

class Buy1Get1FreeTest {

    @Test
    void shouldReturnZeroWhenNoItems(){

        List<Item> items = new ArrayList<>();
        Buy1Get1Free bogof = new Buy1Get1Free();

        assertEquals(BigDecimal.ZERO, bogof.calculate(items));
    }
}