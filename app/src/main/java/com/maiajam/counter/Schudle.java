package com.maiajam.counter;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

/**
 * Created by maiAjam on 8/9/2018.
 */

public class Schudle extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {

        Toast.makeText(getBaseContext(),"done",Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
