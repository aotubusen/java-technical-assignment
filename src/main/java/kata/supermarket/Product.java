package kata.supermarket;

import java.math.BigDecimal;

public class Product extends AbstractProduct{


    public Product(final BigDecimal pricePerUnit) {
        super(pricePerUnit);
    }

    BigDecimal pricePerUnit() {
        return price();
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}