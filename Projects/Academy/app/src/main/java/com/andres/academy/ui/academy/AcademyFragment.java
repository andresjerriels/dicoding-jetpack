package com.andres.academy.ui.academy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.andres.academy.data.CourseEntity;
import com.andres.academy.databinding.FragmentAcademyBinding;
import com.andres.academy.utils.DataDummy;
import com.andres.academy.viewmodel.ViewModelFactory;

import java.util.List;


public class AcademyFragment extends Fragment {
    private FragmentAcademyBinding fragmentAcademyBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentAcademyBinding = FragmentAcademyBinding.inflate(inflater, container, false);
        return fragmentAcademyBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            AcademyViewModel viewModel = new ViewModelProvider(this, factory).get(AcademyViewModel.class);

            AcademyAdapter academyAdapter = new AcademyAdapter();

            fragmentAcademyBinding.progressBar.setVisibility(View.VISIBLE);
            viewModel.getCourses().observe(getViewLifecycleOwner(), courses -> {
                fragmentAcademyBinding.progressBar.setVisibility(View.GONE);
                academyAdapter.setCourses(courses);
                academyAdapter.notifyDataSetChanged();
            });

            fragmentAcademyBinding.rvAcademy.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentAcademyBinding.rvAcademy.setHasFixedSize(true);
            fragmentAcademyBinding.rvAcademy.setAdapter(academyAdapter);
        }
    }
}