package com.maiajam.counter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;

/**
 * Created by maiAjam on 7/22/2018.
 */

public class dbHandler extends SQLiteOpenHelper {



    private static final String Key_TableAthaker = "athkar";

    private static final String Key_Id = "id";
    private static final String Key_Theker_name = "ThekerName";
    private static final String Key_Count = "Count";
    private static final String Key_theker_Target = "ThekerTarget";
    private static final String Key_theker_achieve = "ThekerAchieve";
    private static final String Key_CompleteRatio ="CompleteRatio";







    private static final String SQL_CREATE_Theker =  "CREATE TABLE " + Key_TableAthaker + " ( " + Key_Id +  " INTEGER PRIMARY KEY," +

            Key_Theker_name + " TEXT,"+Key_theker_Target + " INTEGER, "+Key_theker_achieve + " INTEGER, " + Key_CompleteRatio + " REAL, "
            +  Key_Count + " INTEGER ) ";



    public dbHandler(Context context) {
        super(context, "athkar.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_Theker);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + Key_TableAthaker);

        onCreate(db);
    }


    public void Add(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(Key_Theker_name,name);
        values.put(Key_Count,0);
        values.put(Key_theker_achieve,0);
        values.put(Key_theker_Target,0);
        values.put(Key_CompleteRatio,0);
        db.insert(Key_TableAthaker,null,values);
        db.close();


    }

    public theker getCount(String name)
    {
        String selectQ = "SELECT Count,ThekerTarget FROM athkar WHERE ThekerName = '" + name + "';";;
        SQLiteDatabase db = this.getReadableDatabase();

        theker obj = new theker();

        Cursor cursor = db.rawQuery(selectQ,null);



        if( cursor != null && cursor.moveToFirst() ){
            obj.setThekerName(name);
            obj.setTheker_target( Integer.valueOf(cursor.getInt(1))) ;
            obj.setCount( Integer.valueOf(cursor.getInt(0)));
            cursor.close();
        }
        return obj ;

    }

    public int getThekerCount(String name)
    {
        String selectQ = "SELECT Count FROM athkar WHERE ThekerName = '" + name + "';";;
        SQLiteDatabase db = this.getReadableDatabase();

        int count = 0 ;

        Cursor cursor = db.rawQuery(selectQ,null);



        if( cursor != null && cursor.moveToFirst() ){

            count =  Integer.valueOf(cursor.getInt(0));

            cursor.close();
        }
        return count;

    }

    public ArrayList<theker> getAll()
    {
        String selectQ = "SELECT ThekerName,id,ThekerTarget,Count,CompleteRatio FROM athkar ORDER BY CompleteRatio ASC ;";;
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<theker> athkarList = new ArrayList<>();
        Cursor cursor = db.rawQuery(selectQ,null);



        String ThekerName = null;
        int new_Theker = 0 ;
       if(cursor != null && cursor.moveToFirst()){
           do {
               theker obj = new theker();

               Log.d(TAG, "getAll: ."+cursor.getInt(4));
               obj.setThekerName( cursor.getString(0));
               obj.setId(cursor.getInt(1));
               obj.setTheker_target(cursor.getInt(2));
               obj.setCount(cursor.getInt(3));
               athkarList.add(obj);
           }while (cursor.moveToNext());

        }

        db.close();
        cursor.close();
        return  athkarList;
    }

    public void update(String name,int count,int thekertarget,int type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        if(thekertarget != 0 )
        {
            float completteRatio = (count*100.0f)/thekertarget ;
            String addQuery = "UPDATE athkar SET Count = '" + count + "', CompleteRatio = ' "+ completteRatio +
                    "' WHERE ThekerName == '" +name + "' ;";
            Log.d(TAG, "updatemedcine: " + addQuery);
            db.execSQL(addQuery);
            db.close();
        }else
        {
            if(type == 1)
            {
                String addQuery = "UPDATE athkar SET Count = '" + count + "', ThekerTarget = ' "+ thekertarget + "', CompleteRatio = ' "+ 0.0 +
                        "' WHERE ThekerName == '" +name + "' ;";
                Log.d(TAG, "updatemedcine: " + addQuery);
                db.execSQL(addQuery);
                db.close();
            }else
            {
                String addQuery = "UPDATE athkar SET Count = '" + count +
                        "' WHERE ThekerName == '" +name + "' ;";
                Log.d(TAG, "updatemedcine: " + addQuery);
                db.execSQL(addQuery);
                db.close();
            }

        }


    }

    public void Delete(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String addQuery = "DELETE from athkar  WHERE ThekerName == '" +name + "' ;";

        Log.d(TAG, "updatemedcine: " + addQuery);
        db.execSQL(addQuery);
        db.close();

    }


    public void Edittheker(String name,int id)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String addQuery = "UPDATE athkar SET ThekerName = '" + name +
                "' WHERE id == '" +id + "' ;";
        Log.d(TAG, "updatemedcine: " + addQuery);
        db.execSQL(addQuery);
        db.close();

    }
    public void AddThekerTarget(String name,int theker_Target)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String addQuery = "UPDATE athkar SET ThekerTarget = '" + theker_Target +
                "' WHERE ThekerName == '" +name + "' ;";
        Log.d(TAG, "updatemedcine: " + addQuery);
        db.execSQL(addQuery);
        db.close();
    }


    public void ResetAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String addQuery = "UPDATE athkar SET Count = '" + 0 +
                "', CompleteRatio = 0 ;";
        Log.d(TAG, "updatemedcine: " + addQuery);
        db.execSQL(addQuery);
        db.close();
    }

    public int GetThekerTarget(String name)
    {
        String selectQ = "SELECT ThekerTarget FROM athkar WHERE ThekerName = '" + name + "';";;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQ,null);

        int thekerTarget = 0 ;


        if( cursor != null && cursor.moveToFirst() ){

            thekerTarget =cursor.getInt(0);
            cursor.close();
        }
        return thekerTarget ;
    }


}
