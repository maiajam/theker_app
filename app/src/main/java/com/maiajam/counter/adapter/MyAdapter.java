package com.maiajam.counter.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maiajam.counter.R;
import com.maiajam.counter.util.customdrawable;
import com.maiajam.counter.data.dbHandler;
import com.maiajam.counter.data.theker;
import com.maiajam.counter.ui.activity.CounterActivity;
import com.maiajam.counter.ui.activity.ThekerTargetActivity;
import com.maiajam.counter.ui.fragments.addDialoge;
import com.maiajam.counter.ui.activity.listActivity;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * Created by maiAjam on 7/21/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {


    Context context ;
    ArrayList<theker> list ;
    dbHandler db ;
    theker obj ;
    Snackbar snackbar ;
    int delete ;



    public MyAdapter(Context con, ArrayList<theker> l)
    {
        context = con ;
        list = l ;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.txt_athkar,null);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {

        db = new dbHandler(context);
        obj = new theker();

        int po = position%2;



        if(list.size()!= 0) {
            obj = list.get(position);
            // final String txt = list.get(position);
            //int n = obj.getNewTheker();


            if (po == 0) {
               holder.textView.setTextColor(Color.CYAN);
                holder.result.setTextColor(Color.CYAN);
            }


            final int thekerTarget = obj.getTheker_target();
            final int theker_achieve = obj.getCount();
            final String thker_Nmae = obj.getThekerName().toString();
            final int id = obj.getId();
            holder.textView.setText(thker_Nmae);


            if (thekerTarget != 0) {
                holder.result.setVisibility(View.VISIBLE);
                holder.result.setText(theker_achieve + "/" + thekerTarget);
                if(theker_achieve != 0)
                {
                    holder.textView.setTextColor(Color.WHITE);
                    holder.result.setTextColor(Color.WHITE);
                }


            } else {
                if (theker_achieve != 0) {

                    holder.result.setVisibility(View.VISIBLE);
                    holder.result.setText((String.valueOf(theker_achieve)));

                } else {
                    holder.result.setVisibility(View.GONE);


                }

            }

            float ratio = 0;
            float fraction = 0;

            ratio = (theker_achieve * 100.0f) / thekerTarget;
            fraction = ratio / 100.00f;
            // holder.relativeLayout.setBackground();

            if (ratio == 0) {
                holder.relativeLayout.setBackground(new customdrawable((1 - fraction), context, 1));
            } else {

              //  holder.textView.setTextColor(Color.CYAN);
                //holder.result.setTextColor(Color.CYAN);
                 if (ratio >= 1 & ratio <= 16) {
                holder.relativeLayout.setBackground(new customdrawable((1 - fraction), context, 2));
            } else if (ratio >= 17 & ratio <= 32) {
                holder.relativeLayout.setBackground(new customdrawable((1 - fraction), context, 3));
            } else if (ratio >= 33 & ratio <= 48) {
                holder.relativeLayout.setBackground(new customdrawable((1 - fraction), context, 4));
            } else if (ratio >= 49 & ratio <= 64) {
                holder.relativeLayout.setBackground(new customdrawable((1 - fraction), context, 5));
            } else if (ratio >= 65 & ratio <= 80) {
                holder.relativeLayout.setBackground(new customdrawable((1 - fraction), context, 6));
            } else if (ratio >= 81 & ratio <= 99) {
                holder.relativeLayout.setBackground(new customdrawable((1 - fraction), context, 7));
            } else if (ratio >= 100) {
                holder.relativeLayout.setBackground(new customdrawable((1 - fraction), context, 8));
            }
        }

            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Create the Snackbar
                    LinearLayout.LayoutParams objLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    snackbar = Snackbar.make(view, "يمكنك حذف او تعديل هذا الذكر", Snackbar.LENGTH_LONG);

                    // Get the Snackbar layout view
                    Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

                    // Set snackbar layout params
                    // int navbarHeight = view.getNavBarHeight(this);
                    CoordinatorLayout.LayoutParams parentParams = (CoordinatorLayout.LayoutParams) layout.getLayoutParams();
                    parentParams.setMargins(0, 0, 0, 0 - 10 + 50);
                    layout.setLayoutParams(parentParams);
                    layout.setPadding(0, 0, 0, 0);
                    layout.setLayoutParams(parentParams);

                    // Inflate our custom view
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View snackView = inflater.inflate(R.layout.snack, null);

                    // Configure our custom view
                    TextView messageTextView = (TextView) snackView.findViewById(R.id.message_text_view);
                    messageTextView.setText("  قم بإجراء اللازم ..");

                    TextView textViewOne = (TextView) snackView.findViewById(R.id.first_text_view);
                    textViewOne.setText("حذف");
                    textViewOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("Allow", "showTwoButtonSnackbar() : allow clicked");
                            db.Delete(thker_Nmae);
                            db.close();


                            list.remove(holder.getAdapterPosition());
                            notifyItemRemoved(holder.getAdapterPosition());

                            Toast.makeText(context, "تمت عملية حذف الذكر بنجاح", Toast.LENGTH_LONG).show();
                            delete = 1;

                            snackbar.dismiss();
                        }
                    });

                    TextView textViewTwo = (TextView) snackView.findViewById(R.id.second_text_view);
                    textViewTwo.setText("تعديل");
                    textViewTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addDialoge dialoge = new addDialoge(thker_Nmae,id);

                            dialoge.show(((listActivity)v.getContext()).getFragmentManager(),"");
                            snackbar.dismiss();
                        }
                    });

                    TextView textviewThree = (TextView)snackView.findViewById(R.id.reset_text_view);
                    textviewThree.setText("تصفير");
                    textviewThree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                         db = new dbHandler(context);
                         db.update(thker_Nmae,0,thekerTarget,0);
                         db.close();
                            snackbar.dismiss();

                          list.set(holder.getAdapterPosition(), db.getCount(thker_Nmae));
                           notifyItemChanged(holder.getAdapterPosition());

                        }
                    });
                    // Add our custom view to the Snackbar's layout
                    layout.addView(snackView, objLayoutParams);

                    // Show the Snackbar
                    snackbar.show();



                    snackbar.addCallback(new Snackbar.Callback() {

                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                            if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT) {
                                // Snackbar closed on its own

                                if (thekerTarget == 0) {

                                    if(theker_achieve == 0)
                                    {
                                        Intent i = new Intent(context, ThekerTargetActivity.class);
                                        i.putExtra("thekerName", thker_Nmae);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);
                                    }else
                                    {
                                        Intent i = new Intent(context, CounterActivity.class);
                                        i.putExtra("thekerName", thker_Nmae);
                                        i.putExtra("count",theker_achieve);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);
                                    }


                                } else {
                                    if (delete == 0) {
                                        Intent i = new Intent(context, CounterActivity.class);
                                        i.putExtra("thekerName", thker_Nmae);
                                        i.putExtra("count", theker_achieve);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);
                                    }

                                }
                            } else if (event == Snackbar.Callback.DISMISS_EVENT_SWIPE) {

                                if (thekerTarget == 0) {

                                    if(theker_achieve == 0)
                                    {
                                        Intent i = new Intent(context, ThekerTargetActivity.class);
                                        i.putExtra("thekerName", thker_Nmae);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);

                                    }else
                                    {

                                        Intent i = new Intent(context, CounterActivity.class);
                                        i.putExtra("thekerName", thker_Nmae);
                                        i.putExtra("count",theker_achieve);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);
                                    }


                                } else {
                                    if (delete == 0) {
                                        Intent i = new Intent(context, CounterActivity.class);
                                        i.putExtra("thekerName", thker_Nmae);
                                        i.putExtra("count", theker_achieve);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);
                                    }

                                }
                            }
                        }


                    });
                    snackbar.show();


                }
            });

        }
    }

    private ArrayList<theker> rotate(ArrayList<theker> al, int adapterPosition) {

        if (al.size() == 0)
            return al;

        theker element = null;
        for(int i = 0; i < al.size()-1; i++)
        {

            element = al.remove( i );
            al.add(i+1, element);
        }

        element = al.remove(adapterPosition);
        al.add(0,element);
        return al;


    }

    private void swipItems(ArrayList<theker> all) {

        list = all ;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder
    {

        TextView textView,result ;
        RelativeLayout relativeLayout ;

        public Holder(View itemView) {
            super(itemView);

            result=(TextView) itemView.findViewById(R.id.result_txt);
            relativeLayout =(RelativeLayout)itemView.findViewById(R.id.theker_lay);
            textView = (TextView) itemView.findViewById(R.id.theker_txt);
        }


    }


}
