package org.example;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Item represents data which is used to create a kind of product/item
 */
public record Item(String name, BigDecimal price, Category category, Boolean imported) {

    public Item(String name, BigDecimal price, Category category, Boolean imported) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.imported = imported;

        validate();
    }

    /**
     * @return the total cost of an item including taxes
     */
    public BigDecimal getTotalCost() {
        return price().add(getSalesTax());
    }

    /**
     * @return the sales taxes cost of an item
     */
    public BigDecimal getSalesTax() {
        return price().multiply(getTaxValue()).divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP).multiply(BigDecimal.valueOf(0.05));
    }

    /**
     * @return the value of all taxes combined
     */
    private BigDecimal getTaxValue() {
        BigDecimal tax = BigDecimal.ZERO;
        if (Category.STANDARD.equals(category())) {
            tax = tax.add(BigDecimal.valueOf(0.1));
        }
        if (imported()) {
            tax = tax.add(BigDecimal.valueOf(0.05));
        }
        return tax;
    }

    /**
     * validate the given input
     */
    private void validate() {
        if (price() == null || price().compareTo(BigDecimal.ZERO) != 1) {
            throw new IllegalArgumentException("price must be specified");
        }
        if (name() == null || name().isBlank()) {
            throw new IllegalArgumentException("name must be specified");
        }
        if (category() == null) {
            throw new IllegalArgumentException("category must be specified");
        }
        if (imported() == null) {
            throw new IllegalArgumentException("imported must be specified");
        }
    }

}
