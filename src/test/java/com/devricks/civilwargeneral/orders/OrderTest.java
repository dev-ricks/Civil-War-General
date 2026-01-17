package com.devricks.civilwargeneral.orders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void isValid() {
        // valid order
        Order valid = new Order("Advance", "Move forward", 1);
        assertTrue(valid.isValid(), "Expected valid order to be valid");

        // invalid: null name
        Order nullName = new Order(null, "desc", 2);
        assertFalse(nullName.isValid());

        // invalid: empty name
        Order emptyName = new Order("", "desc", 3);
        assertFalse(emptyName.isValid());

        // invalid: null description
        Order nullDesc = new Order("Name", null, 4);
        assertFalse(nullDesc.isValid());

        // invalid: empty description
        Order emptyDesc = new Order("Name", "", 5);
        assertFalse(emptyDesc.isValid());

        // invalid: id <= 0
        Order zeroId = new Order("Name", "Desc", 0);
        Order negativeId = new Order("Name", "Desc", -1);
        assertFalse(zeroId.isValid());
        assertFalse(negativeId.isValid());
    }
}