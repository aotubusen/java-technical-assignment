package kata.supermarket;

import java.math.BigDecimal;
import java.util.UUID;

public class AbstractProduct {

    private String code = UUID.randomUUID().toString();
    private final BigDecimal price;

    public AbstractProduct(final BigDecimal price) {
        this.price = price;
    }
    public String code() {
        return code;
    }

    public BigDecimal price(){
        return price;
    }
}