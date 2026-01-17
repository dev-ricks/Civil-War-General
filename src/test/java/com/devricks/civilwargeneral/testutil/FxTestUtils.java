package com.devricks.civilwargeneral.testutil;

import javafx.application.Platform;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Minimal JavaFX test utilities: initializes the FX platform once and
 * provides a helper to run code on the FX Application Thread and wait.
 */
public final class FxTestUtils {
    private static volatile boolean fxStarted = false;

    private FxTestUtils() {}

    /** Ensure JavaFX Platform is started. Safe to call multiple times. */
    public static void ensurePlatformStarted() {
        if (!fxStarted) {
            synchronized (FxTestUtils.class) {
                if (!fxStarted) {
                    CountDownLatch latch = new CountDownLatch(1);
                    Platform.startup(latch::countDown);
                    try {
                        latch.await(5, TimeUnit.SECONDS);
                    } catch (InterruptedException ignored) {
                    }
                    fxStarted = true;
                }
            }
        }
    }

    /** Run the given runnable on the FX Application Thread and wait for completion. */
    public static void runOnFxAndWait(Runnable action) {
        if (Platform.isFxApplicationThread()) {
            action.run();
            return;
        }
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                action.run();
            } finally {
                latch.countDown();
            }
        });
        try {
            latch.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException ignored) {
        }
    }
}
