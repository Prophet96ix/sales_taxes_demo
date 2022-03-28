package org.example;

import lombok.NonNull;
import org.example.helpers.Category;
import org.example.helpers.ItemMeta;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class Item extends ItemMeta {

    public Item(
            @NonNull String name,
            @NonNull BigDecimal price,
            @NonNull Category category,
            @NonNull Boolean imported
    ) {
        super(name, price, category, imported);

        if (price == 0) {

        }
    }

    /**
     * @return the total cost of an item including taxes
     */
    public BigDecimal getTotalCost() {
        return getPrice().add(getSalesTax());
    }

    /**
     * @return the sales tax cost of an item
     */
    public BigDecimal getSalesTax() {
        return getPrice().multiply(getTaxPercentage())
                .divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP)
                .multiply(BigDecimal.valueOf(0.05));
    }

    /**
     * @return the percentage of all taxes combined
     */
    private BigDecimal getTaxPercentage() {
        BigDecimal tax = BigDecimal.ZERO;
        if (Category.STANDARD.equals(getCategory())) {
            tax = tax.add(BigDecimal.valueOf(0.1));
        }
        if (getImported()) {
            tax = tax.add(BigDecimal.valueOf(0.05));
        }
        return tax;
    }

}
