package com.andres.myviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.andres.myviewmodel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        displayResult();

        findViewById(R.id.btn_calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String width = activityMainBinding.edtWidth.getText().toString();
                String height = activityMainBinding.edtHeight.getText().toString();
                String length = activityMainBinding.edtLength.getText().toString();

                if (width.isEmpty()) {
                    activityMainBinding.edtWidth.setError("Masih kosong");
                } else if (height.isEmpty()) {
                    activityMainBinding.edtHeight.setError("Masih kosong");
                } else if (length.isEmpty()) {
                    activityMainBinding.edtLength.setError("Masih kosong");
                } else {
                    viewModel.calculate(width, height, length);
                    displayResult();
                }
            }
        });
    }

    private void displayResult() {
        activityMainBinding.tvResult.setText(String.valueOf(viewModel.result));
    }
}