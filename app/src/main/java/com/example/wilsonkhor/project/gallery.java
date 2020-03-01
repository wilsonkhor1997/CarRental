package com.example.wilsonkhor.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class gallery extends AppCompatActivity {

    ViewFlipper viewFlipper;
    Button next;
    Button previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);
    }

    public void viewgallery(View v) {
        if (v == next) {
            viewFlipper.showNext();
        } else if (v == previous) {
            viewFlipper.showPrevious();
        }
    }
}
