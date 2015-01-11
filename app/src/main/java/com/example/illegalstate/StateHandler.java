package com.example.illegalstate;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StateHandler {

    /**
     * Runnable Queue Buffer
     */
    private final List<Runnable> queueBuffer = Collections.synchronizedList(new ArrayList<Runnable>());

    /**
     * Flag indicating the pause state
     */
    private Activity activity;

    /**
     * Resume the handler.
     */
    public final synchronized void resume(Activity activity) {
        this.activity = activity;

        while (queueBuffer.size() > 0) {
            final Runnable runnable = queueBuffer.get(0);
            queueBuffer.remove(0);
            runnable.run();
        }
    }

    /**
     * Pause the handler.
     */
    public final synchronized void pause() {
        activity = null;
    }

    /**
     * Store the runnable if we have been paused, otherwise handle it now.
     *
     * @param runnable  Runnable to run.
     */
    public final synchronized void run(Runnable runnable) {
        if (activity == null) {
            queueBuffer.add(runnable);
        } else {
            runnable.run();
        }
    }
}
