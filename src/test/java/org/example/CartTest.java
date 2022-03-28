package org.example;

import lombok.NonNull;
import org.example.helpers.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTest {

    private @NonNull Cart cart;

    @BeforeEach
    public void beforeEach() {
        cart = new Cart();
    }

    @Test
    public void testInput1() {
        Item book = new Item("book", BigDecimal.valueOf(12.49), Category.BOOK, false);
        Item musicCD = new Item("music CD", BigDecimal.valueOf(14.99), Category.STANDARD, false);
        Item chocolateBar = new Item("chocolate bar", BigDecimal.valueOf(0.85), Category.FOOD, false);

        cart.addItem(book, 1);
        cart.addItem(musicCD, 1);
        cart.addItem(chocolateBar,1);
        cart.checkOut();

        assertEquals(0, BigDecimal.valueOf(12.49).compareTo(book.getTotalCost()));
        assertEquals(0, BigDecimal.valueOf(16.49).compareTo(musicCD.getTotalCost()));
        assertEquals(0, BigDecimal.valueOf(0.85).compareTo(chocolateBar.getTotalCost()));

        assertEquals(0, BigDecimal.valueOf(1.50).compareTo(cart.getTotalSalesTaxes()));
        assertEquals(0, BigDecimal.valueOf(29.83).compareTo(cart.getTotalCost()));
    }

    @Test
    public void testInput2() {
        Item boxOfChocolates = new Item("box of chocolates", BigDecimal.valueOf(10), Category.FOOD, true);
        Item bottleOfPerfume = new Item("bottle of perfume", BigDecimal.valueOf(47.50), Category.STANDARD, true);

        cart.addItems(boxOfChocolates, bottleOfPerfume);
        cart.checkOut();

        assertEquals(0, BigDecimal.valueOf(10.50).compareTo(boxOfChocolates.getTotalCost()));
        assertEquals(0, BigDecimal.valueOf(54.65).compareTo(bottleOfPerfume.getTotalCost()));

        assertEquals(0, BigDecimal.valueOf(7.65).compareTo(cart.getTotalSalesTaxes()));
        assertEquals(0, BigDecimal.valueOf(65.15).compareTo(cart.getTotalCost()));
    }

    @Test
    public void testInput3() {
        Item importedBottleOfPerfume = new Item("imported bottle of perfume", BigDecimal.valueOf(27.99), Category.STANDARD, true);
        Item bottleOfPerfume = new Item("bottle of perfume", BigDecimal.valueOf(18.99), Category.STANDARD, false);
        Item packetOfHeadachePills = new Item("packet of headache pills", BigDecimal.valueOf(9.75), Category.MEDICAL, false);
        Item boxOfImportedChocolates = new Item("box of imported chocolates", BigDecimal.valueOf(11.25), Category.FOOD, true);

        cart.addItems(importedBottleOfPerfume, bottleOfPerfume, packetOfHeadachePills, boxOfImportedChocolates);
        cart.checkOut();

        assertEquals(0, BigDecimal.valueOf(32.19).compareTo(importedBottleOfPerfume.getTotalCost()));
        assertEquals(0, BigDecimal.valueOf(20.89).compareTo(bottleOfPerfume.getTotalCost()));
        assertEquals(0, BigDecimal.valueOf(9.75).compareTo(packetOfHeadachePills.getTotalCost()));
        assertEquals(0, BigDecimal.valueOf(11.85).compareTo(boxOfImportedChocolates.getTotalCost()));

        assertEquals(0, BigDecimal.valueOf(6.70).compareTo(cart.getTotalSalesTaxes()));
        assertEquals(0, BigDecimal.valueOf(74.68).compareTo(cart.getTotalCost()));
    }

}