package com.example.illegalstate;

import android.support.v7.app.ActionBarActivity;

public class BaseActivity extends ActionBarActivity {
    protected StateHandler stateHandler = new StateHandler();

    @Override
    protected void onPause() {
        super.onPause();
        stateHandler.pause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        stateHandler.resume(this);
    }
}
