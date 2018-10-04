package com.maiajam.counter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThekerTargetActivity extends AppCompatActivity {


    EditText editText ;
    Button ok_b,reject_b;
    dbHandler db ;
    String theker_name;
    int count ,hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theker_target);

        ok_b = (Button)findViewById(R.id.ok_b);
        reject_b=(Button)findViewById(R.id.reject_b);

        editText =(EditText)findViewById(R.id.thekerTarget_edit);
        db = new dbHandler(getBaseContext());

        Bundle extra = getIntent().getExtras();
        if(extra!=null)
        {
            theker_name = extra.getString("thekerName");
            count = extra.getInt("count",0);
            hint = extra.getInt("hint",0);

        }

        if(hint == 1)
        {
            reject_b.setText("الغاء");
        }


        ok_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText.getText().toString()!=null)
                {
                    db.AddThekerTarget(theker_name,Integer.valueOf(editText.getText().toString()));
                    db.close();
                    finish();
                    Intent i = new Intent(ThekerTargetActivity.this,CounterActivity.class);
                    i.putExtra("thekerName",theker_name);
                    i.putExtra("count",count);
                    startActivity(i);
                }else
                {
                    Toast.makeText(getBaseContext(),"الرجاء إدخال قيمة لهدفك من الورد لهذا الذكر",Toast.LENGTH_LONG).show();
                }

            }
        });

        reject_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(hint == 1)
                {

                }else
                {
                    finish();
                    Intent i = new Intent(ThekerTargetActivity.this,CounterActivity.class);
                    i.putExtra("thekerName",theker_name);
                    i.putExtra("count",count);
                    startActivity(i);
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        Intent i = new Intent(ThekerTargetActivity.this,listActivity.class);

        startActivity(i);

    }
}
