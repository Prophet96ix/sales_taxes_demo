package org.example.helpers;

import lombok.Data;

import java.math.BigDecimal;


@Data
public abstract class ItemMeta {

    String name;
    BigDecimal price;
    Category category;
    Boolean imported;

    public ItemMeta(String name, BigDecimal price, Category category, Boolean imported) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.imported = imported;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public Boolean getImported() {
        return imported;
    }
}
