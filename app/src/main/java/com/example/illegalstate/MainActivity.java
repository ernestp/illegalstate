package com.example.illegalstate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAsyncTask();
    }

    private void startAsyncTask() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showMyDialog();
                    }
                });
                return null;
            }
        }.execute();
    }

    private void showMyDialog() {
        // if we run it here we can got IllegalStateException
        //new TestDialog().show(getSupportFragmentManager(), "dialog");

        // so we wrap it with StateHandler
        stateHandler.run(new Runnable() {
            @Override
            public void run() {
                new TestDialog().show(getSupportFragmentManager(), "dialog");
            }
        });
    }

    public class TestDialog extends DialogFragment {
        public TestDialog(){}

        @Override
        public Dialog onCreateDialog (Bundle savedInstanceState){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Hello World");
            builder.setPositiveButton("OK", null);
            return builder.create();
        }
    }
}
