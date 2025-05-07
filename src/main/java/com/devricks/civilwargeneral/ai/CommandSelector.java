package com.devricks.civilwargeneral.ai;

import com.devricks.civilwargeneral.orders.Order;
import com.devricks.civilwargeneral.orders.Orders;
import java.security.SecureRandom;

public class CommandSelector {

    // Maybe this should be static if state is not a property needed for commandselector, will evaluate later on
    public Order randomOrderSelector(Orders orders) {
        if (orders == null || orders.getAllOrders().isEmpty()) {
            return null;
        }
        int randomIndex = randomNumber(orders.getAllOrders().size() - 1);
        Order selectedOrder = orders.getAllOrders().get(randomIndex);
        if (selectedOrder == null || !selectedOrder.isValid()) {
            return null;
        }
        return selectedOrder;
    }

    private int randomNumber(int max) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(max + 1);
    }
}
