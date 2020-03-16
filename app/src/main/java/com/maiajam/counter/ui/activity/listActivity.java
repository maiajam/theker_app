package com.maiajam.counter.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.maiajam.counter.adapter.MyAdapter;
import com.maiajam.counter.R;
import com.maiajam.counter.ui.fragments.addDialoge;
import com.maiajam.counter.data.dbHandler;
import com.maiajam.counter.data.theker;
import com.maiajam.counter.util.Constant;

import java.util.ArrayList;

public class listActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> AthkarList;
    ArrayList<theker> advanceList;
    MyAdapter adapter;
    dbHandler db;
    AlertDialog dialog;
    private Bundle extra;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private int theker_Type;
    private AlertDialog.Builder d;
    private TextView welcomeEmptyText;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);

        checkThekerType();
        initializeViews();

        db = new dbHandler(getBaseContext());

        getSupportActionBar().setTitle("");

        AthkarList = new ArrayList<>();

        if (theker_Type == Constant.ConstantAthkar) {
            initialConstantThekers();
            advanceList = db.getAll(Constant.ConstantAthkar);
        } else {
            advanceList = db.getAll(Constant.MyAthkar);
            if (advanceList.size() == 0)
                welcomeEmptyText.setVisibility(View.VISIBLE);
            else
                welcomeEmptyText.setVisibility(View.GONE);
        }


        adapter = new MyAdapter(getBaseContext(), advanceList);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();

        if (theker_Type == Constant.ConstantAthkar) {
            fab.setVisibility(View.GONE);
        } else {
            fab.setVisibility(View.VISIBLE);
        }

    }

    private void initializeViews() {
        recyclerView = (RecyclerView) findViewById(R.id.Rec);
        welcomeEmptyText = (TextView) findViewById(R.id.ListActivity_TV_welcomeInEmptyList);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDialoge addDialoge = new addDialoge();
                addDialoge.show(getFragmentManager(), "");
            }
        });
    }

    private void initialConstantThekers() {

        if (sp.getBoolean("first", true)) {
            editor.putBoolean("first", false);
            editor.commit();
            AthkarList.add("الحمدلله");
            AthkarList.add("استغفر الله");
            AthkarList.add("لا اله الا انت سبحانك إني كنت من الظالمين");
            AthkarList.add("سبحان الله وبحمده");
            AthkarList.add("اللهم صلي على سيدنا محمد");
            AthkarList.add("لا حول ولا قوة الا بالله");
            AthkarList.add("الله اكبر ");
            AthkarList.add("لا إله الا الله");
            int i = 0;
            while (i < AthkarList.size()) {
                db.Add(AthkarList.get(i), Constant.ConstantAthkar);
                i++;
            }
            db.close();
        }
    }

    private void checkThekerType() {
        extra = getIntent().getExtras();
        if (extra != null) {
            theker_Type = extra.getInt(getString(R.string.extra_ThekerOption));
        }
        sp = getSharedPreferences("MyFirstVisit", 0);
        editor = sp.edit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.list, menu);
        return super.onCreateOptionsMenu(menu);
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

        int id = item.getItemId();
        if (id == R.id.action_ٌshare) {

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

        } else if (id == R.id.action_ٌResetAll) {
            resetAll();
        } else if (id == R.id.action_OneTargetAllٌ) {
            startActivity(new Intent(listActivity.this, ThekerTargetActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetAll() {
        asureResetAll();
    }

    private void asureResetAll() {
        d = new AlertDialog.Builder(this);
        d.setMessage("هل تريد تصفير كل العدادات ؟");

        d.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doResetAll();
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

    private void doResetAll() {
        db = new dbHandler(getBaseContext());
        db.ResetAll();
        db.close();
        advanceList = new ArrayList<>();
        advanceList = db.getAll(theker_Type);
        adapter = new MyAdapter(getBaseContext(), advanceList);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        advanceList = new ArrayList<>();
        advanceList = db.getAll(theker_Type);
        adapter = new MyAdapter(getBaseContext(), advanceList);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(listActivity.this, ThekerOptionActivity.class);
        startActivity(i);
    }
}
