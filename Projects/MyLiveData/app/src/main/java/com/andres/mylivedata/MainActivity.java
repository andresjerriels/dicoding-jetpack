package com.andres.mylivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.andres.mylivedata.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mLiveDataTimerViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        mLiveDataTimerViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        subscribe();
    }

    private void subscribe() {
        final Observer<Long> elapsedTimeObserver = new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                String newText = MainActivity.this.getResources().getString(R.string.seconds, aLong);
                activityMainBinding.timerTextview.setText(newText);
            }
        };

        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver);
    }
}