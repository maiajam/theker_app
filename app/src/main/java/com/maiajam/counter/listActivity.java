package com.maiajam.counter;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class listActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    ArrayList<String> AthkarList ;
    ArrayList<theker> advanceList ;
    MyAdapter adapter ;
    dbHandler db ;
    AlertDialog dialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);

        db = new dbHandler(getBaseContext());
        recyclerView = (RecyclerView)findViewById(R.id.Rec);

        getSupportActionBar().setTitle("");
        SharedPreferences sp = getSharedPreferences("MyFirstVisit",0);
        SharedPreferences.Editor editor = sp.edit();

        AthkarList = new ArrayList<>();

        if(sp.getBoolean("first",true))
        {


            editor.putBoolean("first",false);
            editor.commit();

            AthkarList.add("الحمدلله");
            AthkarList.add("استغفر الله");
            AthkarList.add("لا اله الا انت سبحانك إني كنت من الظالمين");
            AthkarList.add("سبحان الله وبحمده");
            AthkarList.add("اللهم صلي على سيدنا محمد");
            AthkarList.add("لا حول ولا قوة الا بالله");
            AthkarList.add("الله اكبر ");
            AthkarList.add("لا إله الا الله");

            int i = 0 ;
            while (i < AthkarList.size()){

                db.Add(AthkarList.get(i));
                i++;
            }

            db.close();

        }else
        {


            advanceList  = db.getAll();
        }



        adapter = new MyAdapter(getBaseContext(),advanceList);

        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());

        recyclerView.setLayoutManager(layoutManager);

        adapter.notifyDataSetChanged();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addDialoge addDialoge = new addDialoge();
                addDialoge.show(getFragmentManager(),"");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.list,menu);
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

        int id = item.getItemId();
        if(id == R.id.action_ٌshare)
        {

            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String sAux = "\nقم بتحميل تطبيق سبحتي حتى تتمكن من التسبيح  ومتابعة وردك  \n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=com.example.maiajam.counter \n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }

        }else if(id == R.id.action_ٌResetAll)
        {
            db = new dbHandler(getBaseContext());
            db.ResetAll();
            //db.update();
            db.close();

            advanceList = new ArrayList<>();
            advanceList = db.getAll();

            adapter = new MyAdapter(getBaseContext(),advanceList);

            recyclerView.setAdapter(adapter);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());

            recyclerView.setLayoutManager(layoutManager);

            adapter.notifyDataSetChanged();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        advanceList = new ArrayList<>();
        advanceList = db.getAll();

        adapter = new MyAdapter(getBaseContext(),advanceList);

        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());

        recyclerView.setLayoutManager(layoutManager);

        adapter.notifyDataSetChanged();

    }
}
