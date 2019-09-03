package com.maiajam.counter.ui.viewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.maiajam.counter.data.local.entity.ThekerEntity;
import com.maiajam.counter.data.local.repo.CounterRepo;
import com.maiajam.counter.data.local.repo.ThekerRepo;

public class CounterActivivtyViewModel extends ViewModel {

    private CounterActivivtyViewModel instance ;
    private CounterRepo thekerRepo ;


    private CounterActivivtyViewModel() {
        instance = new CounterActivivtyViewModel();
    }

    public CounterActivivtyViewModel getInstance() {
        if(instance != null)
        {
            instance = new CounterActivivtyViewModel();
        }
        return instance;
    }

}
