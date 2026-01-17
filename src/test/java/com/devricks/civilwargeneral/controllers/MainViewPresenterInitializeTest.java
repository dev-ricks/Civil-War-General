package com.devricks.civilwargeneral.controllers;

import com.devricks.civilwargeneral.ai.CommandSelector;
import com.devricks.civilwargeneral.orders.Order;
import com.devricks.civilwargeneral.orders.Orders;
import com.devricks.civilwargeneral.orders.OrdersLoadException;
import com.devricks.civilwargeneral.orders.OrdersLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainViewPresenterInitializeTest {
    @Mock
    MainViewUI ui;
    @Mock
    OrdersLoader loader;
    @Mock
    Orders orders;
    @Mock
    CommandSelector selector;

    @InjectMocks
    MainViewPresenter presenter;

    @Test
    void initialize_clearsList_and_enablesButton_whenDefaultsPresent() throws Exception {
        when(orders.getAllOrders()).thenReturn(List.of(new Order(/*...*/)));
        when(loader.loadDefaults()).thenReturn(orders);

        presenter.initialize();

        InOrder inOrder = inOrder(ui, loader);
        inOrder.verify(ui).clearList();
        inOrder.verify(loader).loadDefaults();
        verify(ui).setGeneratedEnabled(true);
    }

    @Test
    void initialize_disablesButton_whenNoOrdersLoaded() throws Exception {
        when(orders.getAllOrders()).thenReturn(List.of());
        when(loader.loadDefaults()).thenReturn(orders);

        presenter.initialize();

        verify(ui).clearList();
        verify(ui).setGeneratedEnabled(false);
    }

    @Test
    void initialize_disablesButton_onLoadFailure() throws Exception {
        when(loader.loadDefaults()).thenThrow(new OrdersLoadException("boom", null));

        presenter.initialize();

        verify(ui).clearList();
        verify(ui).setGeneratedEnabled(false);
    }

    @Test
    void initialize_is_idempotent_calls_loader_each_time() throws Exception {
        when(orders.getAllOrders()).thenReturn(List.of(new Order(/*...*/)));
        when(loader.loadDefaults()).thenReturn(orders);

        presenter.initialize();
        presenter.initialize();

        verify(loader, times(2)).loadDefaults();
        verify(ui, times(2)).clearList();
    }
}
