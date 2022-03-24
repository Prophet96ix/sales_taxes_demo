package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cart {

    private final List<Item> items = new ArrayList<>();

    public void addItems(Item... item) {
        this.items.addAll(Arrays.asList(item));
    }

    public void checkOut() {
        items.forEach(item ->
                System.out.println("1 " + item.getName() + ": " + item.getTotalCost())
        );
        System.out.println("Sales Taxes: " + getTotalSalesTaxes());
        System.out.println("Total: " + getTotalCost());
    }

    public BigDecimal getTotalSalesTaxes() {
        return items.stream()
                .map(Item::getSalesTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalCost() {
        return items.stream()
                .map(Item::getTotalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
