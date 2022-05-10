package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct extends AbstractProduct{

    public WeighedProduct(final BigDecimal pricePerKilo) {
        super(pricePerKilo);
    }

    BigDecimal pricePerKilo() {
        return price();
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }

}