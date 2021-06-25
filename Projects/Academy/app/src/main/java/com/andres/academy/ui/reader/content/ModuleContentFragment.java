package com.andres.academy.ui.reader.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.andres.academy.data.ContentEntity;
import com.andres.academy.data.ModuleEntity;
import com.andres.academy.databinding.FragmentModuleContentBinding;
import com.andres.academy.ui.reader.CourseReaderViewModel;
import com.andres.academy.viewmodel.ViewModelFactory;


public class ModuleContentFragment extends Fragment {
    public static final String TAG = ModuleContentFragment.class.getSimpleName();
    private FragmentModuleContentBinding fragmentModuleContentBinding;

    public ModuleContentFragment() {
    }

    public static ModuleContentFragment newInstance() {
        return new ModuleContentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentModuleContentBinding = FragmentModuleContentBinding.inflate(inflater, container, false);
        return fragmentModuleContentBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(requireActivity());
            CourseReaderViewModel viewModel = new ViewModelProvider(requireActivity(), factory).get(CourseReaderViewModel.class);
            fragmentModuleContentBinding.progressBar.setVisibility(View.VISIBLE);
            viewModel.getSelectedModule().observe(getViewLifecycleOwner(), module -> {
                fragmentModuleContentBinding.progressBar.setVisibility(View.GONE);
                if (module != null) populateWebView(module);
            });
        }
    }

    private void populateWebView(ModuleEntity module) {
        fragmentModuleContentBinding.webView.loadData(module.contentEntity.getContent(), "text/html", "UTF-8");
    }
}