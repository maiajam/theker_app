package com.maiajam.counter.data.local.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.maiajam.counter.data.local.entity.ThekerEntity;
import com.maiajam.counter.data.local.room.RoomDao;
import com.maiajam.counter.data.local.room.RoomManger;
import com.maiajam.counter.helper.AppExecutors;


public class CounterRepo {

    private static CounterRepo instance;
    private static LiveData<ThekerEntity> selectedTheker;
    private static RoomDao db;
    private static ThekerEntity thekerEntity;

    private CounterRepo(Application appContext, String thekerName) {
        RoomManger roomManger = RoomManger.getInstance(appContext);
        db = roomManger.RoomDao();
    }

    public static CounterRepo getInstance(Application appContext, String thekerName) {
        if (instance == null) {
            instance = new CounterRepo(appContext, thekerName);
        }
        return instance;
    }

    public static LiveData<ThekerEntity> getSelectedTheker() {
        return selectedTheker;
    }

    public static void upadteThekerAchive(final int thekerAchive, final String thekerName) {

        AppExecutors.getInstance().diskIO().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        db.updateThekerCount(thekerAchive, thekerName);
                    }
                }
        );

    }


}
