package com.devricks.civilwargeneral.controllers;

import com.devricks.civilwargeneral.ai.CommandSelector;
import com.devricks.civilwargeneral.orders.Order;
import com.devricks.civilwargeneral.orders.Orders;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
class MainViewTest {

    private MainView mainView;

    @Mock
    private Orders mockOrders;

    @Mock
    private CommandSelector mockCommandSelector;

    //@Mock
    //private ListView<Order> mockCommandList;

    @Mock
    private ObservableList<Order> mockObservableList;

    @Mock
    private ActionEvent mockActionEvent;

    @BeforeEach
    void setUp() {
        //        MockitoAnnotations.openMocks(this);
        //        mainView = new MainView();
        //        mainView.orders = mockOrders;
        //        mainView.commandList = mockCommandList;
        //
        //        when(mockCommandList.getItems()).thenReturn(mockObservableList);
    }

    @Test
    void testInitialize() {
        fail("Test not implemented yet");
        //        // Arrange
        //        doNothing().when(mockOrders).loadFromFile(anyString());
        //
        //        // Act
        //        mainView.initialize();
        //
        //        // Assert
        //        verify(mockCommandList.getItems()).clear();
        //        verify(mockOrders).loadFromFile("/com/devricks/civilwargeneral/default-orders.json");
    }

    @Test
    void testOnCommandButtonClick() {
        fail("Test not implemented yet");
        //        // Arrange
        //        Order mockOrder = new Order("Test Order", "Test Description", 1);
        //        when(mockCommandSelector.randomOrderSelector(mockOrders)).thenReturn(mockOrder);
        //
        //        // Act
        //        mainView.onCommandButtonClick(mockActionEvent);
        //
        //        // Assert
        //        verify(mockCommandList.getItems()).add(mockOrder);
    }

    @Test
    void testOnCommandButtonClickWithNullOrder() {
        fail("Test not implemented yet");
        //        // Arrange
        //        when(mockCommandSelector.randomOrderSelector(mockOrders)).thenReturn(null);
        //
        //        // Act
        //        mainView.onCommandButtonClick(mockActionEvent);
        //
        //        // Assert
        //        verify(mockCommandList.getItems(), never()).add(any());
    }
}
