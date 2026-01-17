package com.devricks.civilwargeneral.orders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderEqualityTest {

    @Test
    void equals_and_hashCode_contract() {
        Order a1 = new Order("Advance", "Move", 1);
        Order a2 = new Order("Advance", "Move", 1);
        Order b = new Order("Hold", "Stay", 2);

        // Reflexive
        assertEquals(a1, a1);

        // Symmetric
        assertEquals(a1, a2);
        assertEquals(a2, a1);

        // Transitive
        Order a3 = new Order("Advance", "Move", 1);
        assertEquals(a1, a2);
        assertEquals(a2, a3);
        assertEquals(a1, a3);

        // Consistent and not equal to different object
        assertNotEquals(a1, b);
        assertNotEquals(a1, null);
        assertNotEquals(a1, "not-an-order");

        // hashCode contract
        assertEquals(a1.hashCode(), a2.hashCode());
        assertEquals(a1.hashCode(), a3.hashCode());
    }
}
