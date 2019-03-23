package com.maiajam.counter.data;

/**
 * Created by maiAjam on 7/25/2018.
 */

public class theker {

    String thekerName ;
    int newTheker ;
    int theker_target;
    int theker_achieve;
    int count ,id ;

    public theker()
    {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNewTheker(int newTheker) {
        this.newTheker = newTheker;
    }

    public int getNewTheker() {
        return newTheker;
    }

    public void setThekerName(String thekerName) {
        this.thekerName = thekerName;
    }

    public String getThekerName() {
        return thekerName;
    }

    public void setTheker_target(int theker_target) {
        this.theker_target = theker_target;
    }

    public int getTheker_target() {
        return theker_target;
    }

    public void setTheker_achieve(int theker_achieve) {
        this.theker_achieve = theker_achieve;
    }

    public int getTheker_achieve() {
        return theker_achieve;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
