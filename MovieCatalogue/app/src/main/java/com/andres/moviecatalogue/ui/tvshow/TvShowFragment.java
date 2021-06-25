package com.andres.moviecatalogue.ui.tvshow;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.andres.moviecatalogue.data.DataEntity;
import com.andres.moviecatalogue.databinding.FragmentTvShowBinding;

import java.util.List;

public class TvShowFragment extends Fragment {
    FragmentTvShowBinding fragmentTvShowBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false);
        return fragmentTvShowBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            TvShowViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel.class);
            List<DataEntity> tvShows = viewModel.getTvShows();

            TvShowAdapter tvShowAdapter = new TvShowAdapter();
            tvShowAdapter.setTvShows(tvShows);

            fragmentTvShowBinding.rvTvshow.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentTvShowBinding.rvTvshow.setHasFixedSize(true);
            fragmentTvShowBinding.rvTvshow.setAdapter(tvShowAdapter);
        }
    }
}