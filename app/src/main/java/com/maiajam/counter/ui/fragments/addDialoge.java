package com.maiajam.counter.ui.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.maiajam.counter.R;
import com.maiajam.counter.data.dbHandler;
import com.maiajam.counter.ui.activity.listActivity;
import com.maiajam.counter.util.Constant;

/**
 * Created by maiAjam on 7/22/2018.
 */

public class addDialoge extends DialogFragment {

    Button add ;
    TextView theker_txt ;
    dbHandler db ;
    String Theker_Name ;
    int Edit_x,Id ;

    @SuppressLint("ValidFragment")
    public addDialoge()
    {

    }
    @SuppressLint("ValidFragment")
    public  addDialoge(String thekerName,int id)
    {
        Theker_Name = thekerName ;
        Id =id ;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        db = new dbHandler(getActivity());
        final AlertDialog.Builder d = new AlertDialog.Builder(getActivity());

        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.add,null);

        add = (Button)view.findViewById(R.id.add_b);
        theker_txt =(TextView)view.findViewById(R.id.theker_txt);

        if(Theker_Name != null)
        {
            theker_txt.setText(Theker_Name.toString());
            Edit_x= 1;
        }else
        {
            Edit_x = 0 ;
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(theker_txt.getText().toString()== null)
                {
                    Toast.makeText(getContext(),"أدخل الذكر",Toast.LENGTH_LONG).show();
                }else
                {
                    if(Edit_x == 1)
                    {
                        db.Edittheker(theker_txt.getText().toString(),Id);
                        db.close();
                        getActivity().startActivity(new Intent(getActivity(), listActivity.class));

                    }else {

                        db.Add(theker_txt.getText().toString(), Constant.MyAthkar);
                        db.close();
                        getActivity().startActivity(new Intent(getActivity(), listActivity.class));
                    }

                    dismiss();
                }



            }
        });
        d.setView(view);

        return d.create();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
