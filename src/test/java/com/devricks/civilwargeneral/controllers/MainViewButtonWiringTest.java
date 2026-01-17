package com.devricks.civilwargeneral.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MainViewButtonWiringTest {
    @Mock
    MainViewPresenter presenter;

    @Test
    void button_calls_presenter() {
        var view = new MainView();
        view.setPresenter(presenter);
        view.onCommandButtonClick(new javafx.event.ActionEvent());
        verify(presenter).onGenerateClicked();
    }
}