package com.maiajam.counter.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maiajam.counter.R;
import com.maiajam.counter.data.dbHandler;
import com.maiajam.counter.data.theker;


public class CounterActivity extends AppCompatActivity {

    RelativeLayout Count_lay;
    TextView txt ;
    int  number ;
    int No,removeTaget_clicked ;
    dbHandler db  ;
    String theker_name ;
    Bundle extra = new Bundle();
    theker obj ;
    int theker_target,finish_clciked ;
    Bundle publicNumber ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        publicNumber = new Bundle();
        publicNumber = savedInstanceState ;
        extra = getIntent().getExtras();
        if(extra!= null)
        {
            theker_name = extra.getString("thekerName");
             number = extra.getInt("count",0);
        }

        getSupportActionBar().setTitle(theker_name);

        obj = new theker();

        db = new dbHandler(getBaseContext());

        Count_lay = (RelativeLayout)findViewById(R.id.counter_lay);
        txt =(TextView)findViewById(R.id.count_text);


      theker_target  = db.GetThekerTarget(theker_name);

      if(savedInstanceState!=null)
      {

          txt.setText(String.valueOf(savedInstanceState.get("number")).toString());
      }else
      {

              txt.setText(String.valueOf(number));

      }




        Count_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number ++ ;
                No++;
                txt.setText(String.valueOf(number));
                if(number == theker_target)
                {

                    Count_lay.setBackgroundColor(Color.parseColor("#00FEA5"));
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(2000);
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();
                    Toast.makeText(getBaseContext(),"أحسنت لقد انهيت وردك لهذا اليوم ...يمكنك المتابعة او البدء من جديد",Toast.LENGTH_LONG).show();
                }


            }
        });


    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        number = savedInstanceState.getInt("number");
        No = savedInstanceState.getInt("number");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


            outState.putInt("number",No);

    }

    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(finish_clciked == 1)
        {
            db = new dbHandler(getBaseContext());
            db.update(theker_name,theker_target,theker_target,0);
        }else
        {
            if(removeTaget_clicked == 1)
            {   db = new dbHandler(getBaseContext());
                db.update(theker_name,number,theker_target,1);


            }else
            {
                db = new dbHandler(getBaseContext());
                db.update(theker_name,number,theker_target,0);

            }

        }
        db.close();

        startActivity(new Intent(CounterActivity.this,listActivity.class));

    }

    @Override
    protected void onStop() {
        super.onStop();

        if(finish_clciked == 1)
        {
            db = new dbHandler(getBaseContext());
            db.update(theker_name,theker_target,theker_target,0);
        }else
        {
            if(removeTaget_clicked == 1)
            {   db = new dbHandler(getBaseContext());
                db.update(theker_name,number,theker_target,1);


            }else
            {
                db = new dbHandler(getBaseContext());
                db.update(theker_name,number,theker_target,0);

            }


        }
        db.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.reset,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        db = new dbHandler(getBaseContext());

        int id = item.getItemId();
        if (id == R.id.action_ٌReset) {
            number = 0;
            txt.setText(String.valueOf(number));
            db.update(theker_name, number, theker_target,0);
            db.close();

        } else if (id == android.R.id.home) {
            // NavUtils.navigateUpFromSameTask(this);
            if (finish_clciked == 1) {
                db = new dbHandler(getBaseContext());
                db.update(theker_name, theker_target, theker_target,0);
                db.close();
            } else {
                if(removeTaget_clicked == 1)
                {
                    db = new dbHandler(getBaseContext());
                    db.update(theker_name, number, 0,1);
                    db.close();
                }else
                {
                    db = new dbHandler(getBaseContext());
                    db.update(theker_name, number, theker_target,0);
                    db.close();
                }
            }

            Intent i = new Intent(getBaseContext(), listActivity.class);
            startActivity(i);
        } else if (id == R.id.action_newTarget) {
            Intent i = new Intent(getBaseContext(), ThekerTargetActivity.class);
            i.putExtra("hint",1);
            i.putExtra("thekerName", theker_name);
            i.putExtra("count",number);

            startActivity(i);
        } else if (id == R.id.action_finishTarget) {
            if(theker_target == 0)
            {

                Toast.makeText(getBaseContext(), "ليس لدك ورد لهذا الذكر ", Toast.LENGTH_LONG).show();
            }else {

                finish_clciked = 1;
                number = obj.getTheker_target();
                txt.setText(String.valueOf(theker_target));
                db.update(theker_name, theker_target, theker_target,0);
                db.close();
                Count_lay.setBackgroundColor(getResources().getColor(R.color.complete));
                Toast.makeText(getBaseContext(), "أحسنت لقد انهيت وردك لهذا الذكر ..بإمكانك البدء من جديد او المتابعة ", Toast.LENGTH_LONG).show();
            }

        }
        else if (id == R.id.action_removeTarget)
        {
            // indicate that we have removed the theker target
            removeTaget_clicked = 1;
            db.update(theker_name, 0, 0,1);
            db.close();
            Toast.makeText(getBaseContext(), "لقد تم الغاء الورد لهذا الذكر ", Toast.LENGTH_LONG).show();

        }

        return true;
    }


}
