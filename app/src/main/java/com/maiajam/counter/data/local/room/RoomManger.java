package com.maiajam.counter.data.local.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import com.maiajam.counter.data.local.entity.ThekerEntity;

@Database(entities = {ThekerEntity.class}, version = 1, exportSchema = false)
@TypeConverters({DataTypeConverter.class})
public abstract class RoomManger extends RoomDatabase {

    private static RoomManger ourInstance ;

    private static Callback initilizeDataBaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };

    public static RoomManger getInstance(Context context) {
        if(ourInstance == null)
        {
            ourInstance = Room.databaseBuilder(context.getApplicationContext(),RoomManger.class,
                    "athkar.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(initilizeDataBaseCallback)
                    .build();
        }
        return ourInstance;
    }

    private RoomManger() {
    }
}
