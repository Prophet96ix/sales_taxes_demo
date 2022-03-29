package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CartTest {

    private Cart cart;

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

        assertEquals(0, BigDecimal.valueOf(1.50).compareTo(cart.getTotalSalesTaxes()));
        assertEquals(0, BigDecimal.valueOf(29.83).compareTo(cart.getTotalCost()));
    }

    @Test
    public void testInput2() {
        Item boxOfChocolates = new Item("box of chocolates", BigDecimal.valueOf(10), Category.FOOD, true);
        Item bottleOfPerfume = new Item("bottle of perfume", BigDecimal.valueOf(47.50), Category.STANDARD, true);

        cart.addItem(boxOfChocolates, 1);
        cart.addItem(bottleOfPerfume, 1);
        cart.checkOut();

        assertEquals(0, BigDecimal.valueOf(7.65).compareTo(cart.getTotalSalesTaxes()));
        assertEquals(0, BigDecimal.valueOf(65.15).compareTo(cart.getTotalCost()));
    }

    @Test
    public void testInput3() {
        Item importedBottleOfPerfume = new Item("imported bottle of perfume", BigDecimal.valueOf(27.99), Category.STANDARD, true);
        Item bottleOfPerfume = new Item("bottle of perfume", BigDecimal.valueOf(18.99), Category.STANDARD, false);
        Item packetOfHeadachePills = new Item("packet of headache pills", BigDecimal.valueOf(9.75), Category.MEDICAL, false);
        Item boxOfImportedChocolates = new Item("box of imported chocolates", BigDecimal.valueOf(11.25), Category.FOOD, true);

        cart.addItem(importedBottleOfPerfume, 1);
        cart.addItem(bottleOfPerfume, 1);
        cart.addItem(packetOfHeadachePills, 1);
        cart.addItem(boxOfImportedChocolates, 1);
        cart.checkOut();

        assertEquals(0, BigDecimal.valueOf(6.70).compareTo(cart.getTotalSalesTaxes()));
        assertEquals(0, BigDecimal.valueOf(74.68).compareTo(cart.getTotalCost()));
    }

    @Test
    public void testInput3WithQuantity2() {
        Item importedBottleOfPerfume = new Item("imported bottle of perfume", BigDecimal.valueOf(27.99), Category.STANDARD, true);
        Item bottleOfPerfume = new Item("bottle of perfume", BigDecimal.valueOf(18.99), Category.STANDARD, false);
        Item packetOfHeadachePills = new Item("packet of headache pills", BigDecimal.valueOf(9.75), Category.MEDICAL, false);
        Item boxOfImportedChocolates = new Item("box of imported chocolates", BigDecimal.valueOf(11.25), Category.FOOD, true);

        cart.addItem(importedBottleOfPerfume, 2);
        cart.addItem(bottleOfPerfume, 2);
        cart.addItem(packetOfHeadachePills, 2);
        cart.addItem(boxOfImportedChocolates, 2);
        cart.checkOut();

        assertEquals(0, BigDecimal.valueOf(13.40).compareTo(cart.getTotalSalesTaxes()));
        assertEquals(0, BigDecimal.valueOf(149.36).compareTo(cart.getTotalCost()));
    }

    @Test
    public void testInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () ->
                new Item("null price", null, Category.STANDARD, false)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new Item("zero price", BigDecimal.ZERO, Category.STANDARD, false)
        );
    }

    @Test
    public void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () ->
                new Item(null, BigDecimal.valueOf(1), Category.STANDARD, false)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new Item("", BigDecimal.valueOf(1), Category.STANDARD, false)
        );
    }

    @Test
    public void testInvalidCategory() {
        assertThrows(IllegalArgumentException.class, () ->
                new Item("null category", BigDecimal.valueOf(1), null, false)
        );
    }

    @Test
    public void testInvalidImported() {
        assertThrows(IllegalArgumentException.class, () ->
                new Item("null imported", BigDecimal.valueOf(1), Category.STANDARD, null)
        );
    }

}