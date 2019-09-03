package com.maiajam.counter.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.maiajam.counter.R;
import com.maiajam.counter.adapter.MyAdapter;
import com.maiajam.counter.data.dbHandler;
import com.maiajam.counter.data.local.entity.ThekerEntity;
import com.maiajam.counter.data.theker;
import com.maiajam.counter.ui.fragments.addDialoge;
import com.maiajam.counter.ui.viewModel.ThekerListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class listActivity extends AppCompatActivity {


    ArrayList<String> AthkarList;
    ArrayList<theker> advanceList;
    MyAdapter adapter;
    dbHandler db;
    RecyclerView.LayoutManager layoutManager ;
    AlertDialog dialog;
    @BindView(R.id.Rec)
    RecyclerView Rec;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.fab2)
    FloatingActionButton fab2;
    private ThekerListViewModel thekerLisrViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);
        ButterKnife.bind(this);

        Rec.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        thekerLisrViewModel = ViewModelProviders.of(this).get(ThekerListViewModel.class);
        thekerLisrViewModel.getThekerList().observe(this, new Observer<List<ThekerEntity>>() {
            @Override
            public void onChanged(@Nullable List<ThekerEntity> thekerEntities) {
                adapter = new MyAdapter(getBaseContext(), thekerEntities,thekerLisrViewModel);
                Rec.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDialoge addDialoge = new addDialoge();
                addDialoge.show(getFragmentManager(), "");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        final AlertDialog.Builder d = new AlertDialog.Builder(this);
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_ٌshare) {
            shareTheApp();
        } else if (item.getItemId() == R.id.action_ٌResetAll) {
           resetAllAthkarCount();
        } else if (item.getItemId() == R.id.action_OneTargetAllٌ) {
            startActivity(new Intent(listActivity.this, ThekerTargetActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetAllAthkarCount() {
        thekerLisrViewModel.ResetAll();
    }

    private void shareTheApp() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String sAux = "\nقم بتحميل تطبيق سبحتي حتى تتمكن من التسبيح  ومتابعة وردك  \n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.maiajam.counter\n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }
}
