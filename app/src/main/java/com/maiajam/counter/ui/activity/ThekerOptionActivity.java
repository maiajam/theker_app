package com.maiajam.counter.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.maiajam.counter.R;
import com.maiajam.counter.util.Constant;

public class ThekerOptionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button MyAthekarB;
    private Button ConstantTheker;
    private Intent newActivity;
    private AlertDialog.Builder d;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theker_option);
        initialViews();
    }

    private void initialViews() {
        MyAthekarB = (Button) findViewById(R.id.ThekereOption_B_MyTheker);
        ConstantTheker = (Button) findViewById(R.id.ThekereOption_B_ConstantTheker);

        MyAthekarB.setOnClickListener(this);
        ConstantTheker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        selectThekerType(v);
    }

    private void selectThekerType(View v) {
        if (v == MyAthekarB) {
            newActivity = new Intent(ThekerOptionActivity.this, listActivity.class);
            newActivity.putExtra(getString(R.string.extra_ThekerOption), Constant.MyAthkar);
        } else {
            newActivity = new Intent(ThekerOptionActivity.this, listActivity.class);
            newActivity.putExtra(getString(R.string.extra_ThekerOption), Constant.ConstantAthkar);
        }
        startActivity(newActivity);
        finish();
    }

    @Override
    public void onBackPressed() {
        existDialoge();
    }

    private void existDialoge() {
        d = new AlertDialog.Builder(this);
        d.setMessage("هل أنت متأكد من الخروج ؟");
        d.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        d.setNegativeButton("كلا", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = d.create();
        dialog.show();
    }
}