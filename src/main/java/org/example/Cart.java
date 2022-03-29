package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link Cart} is a kind of shopping cart used to purchase {@link Item Items}
 */
public class Cart {

    /**
     * stores the {@link Item Items}
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Add an {@link Item} to the {@link Cart} to purchase it
     * @param item the {@link Item} to purchase
     * @param quantity the quantity how many {@link Item Items} should be added
     */
    public void addItem(Item item, Integer quantity) {
        for (int index = 0; index < quantity; index++) {
            this.items.add(item);
        }
    }

    /**
     * checkout the {@link Cart} and print the receipt
     */
    public void checkOut() {
        System.out.println("=====");
        items.forEach(item ->
                System.out.println("- " + item.name() + ": " + item.getTotalCost())
        );
        System.out.println("Sales Taxes: " + getTotalSalesTaxes());
        System.out.println("Total: " + getTotalCost());
        System.out.println("=====");
    }

    /**
     * calculate the total sales taxes of all {@link Item Items}
     * @return {@link BigDecimal} of all sales taxes
     */
    public BigDecimal getTotalSalesTaxes() {
        return items.stream()
                .map(Item::getSalesTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * calculate the total cost of all {@link Item Items}
     * @return {@link BigDecimal} of all costs
     */
    public BigDecimal getTotalCost() {
        return items.stream()
                .map(Item::getTotalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
