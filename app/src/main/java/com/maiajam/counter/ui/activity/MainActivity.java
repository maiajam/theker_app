package com.maiajam.counter.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.maiajam.counter.R;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {


    Boolean clicked  = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!isTaskRoot()&& getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)&& getIntent().getAction().equals(Intent.ACTION_MAIN))
        {
            finish();
            return;
        }

        ImageView select_b = (ImageView) findViewById(R.id.imageButton);
        select_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = true ;
                startActivity(new Intent(MainActivity.this,listActivity.class));
            }
        });


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    sleep(1*1000);

                    Intent i = new Intent(MainActivity.this,ThekerOptionActivity.class);
                    startActivity(i);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

            thread.start();


    }




}
