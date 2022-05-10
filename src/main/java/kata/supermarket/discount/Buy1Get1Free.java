package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import kata.supermarket.Item;
import kata.supermarket.ItemByUnit;

public class Buy1Get1Free implements DiscountScheme{

    private final String productCode;
    public Buy1Get1Free(final String productCode){
        this.productCode = productCode;
    }
    @Override public BigDecimal calculate(final List<Item> items) {
        if(items == null || items.isEmpty())
            return BigDecimal.ZERO;
        List<Item> discountedItem = items.stream().filter(i -> i.code().equals(productCode) && i instanceof ItemByUnit)
                        .collect(Collectors.toList());
        BigDecimal factor = new BigDecimal(discountedItem.size()/2);
        return items.get(0).price().multiply(factor);
    }

}