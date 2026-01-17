package com.devricks.civilwargeneral.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MainViewWiringTest {
    @Mock
    MainViewPresenter presenter;

    @Test
    void initialize_delegates_to_presenter() {
        var view = new MainView();
        // Use reflection or test-only setters if fields are private; or keep them null if not used here
        view.setPresenter(presenter);
        // Simulate FXMLLoader lifecycle
        view.initialize();
        verify(presenter).initialize();
    }
}
