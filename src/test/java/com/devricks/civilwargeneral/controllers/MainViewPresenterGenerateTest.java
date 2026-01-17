package com.devricks.civilwargeneral.controllers;

import com.devricks.civilwargeneral.ai.CommandSelector;
import com.devricks.civilwargeneral.orders.Order;
import com.devricks.civilwargeneral.orders.Orders;
import com.devricks.civilwargeneral.orders.OrdersLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainViewPresenterGenerateTest {
    @Mock
    MainViewUI ui;
    @Mock
    OrdersLoader loader;
    @Mock
    CommandSelector selector;
    @Mock
    Orders orders;

    MainViewPresenter presenter;

    @BeforeEach
    void setUp() {
        presenter = new MainViewPresenter(ui, loader, selector);
    }

    @Test
    void onGenerateClicked_appends_selected_order_when_available() throws Exception {
        var order = new Order();
        when(loader.loadDefaults()).thenReturn(orders);
        when(orders.getAllOrders()).thenReturn(List.of(order));
        when(selector.randomOrderSelector(orders)).thenReturn(order);

        presenter.initialize();
        presenter.onGenerateClicked();

        verify(ui).addOrder(order);
    }

    @Test
    void onGenerateClicked_does_nothing_when_no_orders() throws Exception {
        when(loader.loadDefaults()).thenReturn(orders);
        when(orders.getAllOrders()).thenReturn(List.of());

        presenter.initialize();
        presenter.onGenerateClicked();

        verify(ui, never()).addOrder(any());
    }

    @Test
    void onGenerateClicked_ignores_null_selection() throws Exception {
        var order = new Order();
        when(loader.loadDefaults()).thenReturn(orders);
        when(orders.getAllOrders()).thenReturn(List.of(order));
        when(selector.randomOrderSelector(orders)).thenReturn(null);

        presenter.initialize();
        presenter.onGenerateClicked();

        verify(ui, never()).addOrder(any());
    }
}
