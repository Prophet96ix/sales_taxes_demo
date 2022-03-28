package org.example.helpers;

import java.math.BigDecimal;

/**
 *
 */
public enum Category {

    STANDARD(BigDecimal.valueOf(0.1)),
    BOOK,
    FOOD,
    MEDICAL;

    Category(BigDecimal tax) {
    }
}
