package com.andres.academy.ui.reader.list;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.andres.academy.data.ModuleEntity;
import com.andres.academy.databinding.FragmentModuleListBinding;
import com.andres.academy.ui.reader.CourseReaderActivity;
import com.andres.academy.ui.reader.CourseReaderCallback;
import com.andres.academy.ui.reader.CourseReaderViewModel;
import com.andres.academy.utils.DataDummy;
import com.andres.academy.viewmodel.ViewModelFactory;

import java.util.List;


public class ModuleListFragment extends Fragment implements MyAdapterClickListener {

    public static final String TAG = ModuleListFragment.class.getSimpleName();

    private FragmentModuleListBinding fragmentModuleListBinding;
    private ModuleListAdapter adapter;
    private CourseReaderCallback courseReaderCallback;
    private CourseReaderViewModel viewModel;

    public ModuleListFragment() {
    }

    public static ModuleListFragment newInstance() {
        return new ModuleListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentModuleListBinding = FragmentModuleListBinding.inflate(inflater, container, false);
        return fragmentModuleListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(requireActivity());
            viewModel = new ViewModelProvider(requireActivity(), factory).get(CourseReaderViewModel.class);
            adapter = new ModuleListAdapter(this);

            fragmentModuleListBinding.progressBar.setVisibility(View.VISIBLE);
            viewModel.getModules().observe(getViewLifecycleOwner(), modules -> {
                fragmentModuleListBinding.progressBar.setVisibility(View.GONE);
                populateRecyclerView(modules);
            });
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        courseReaderCallback = ((CourseReaderActivity) context);
    }

    @Override
    public void onItemClicked(int position, String moduleId) {
        courseReaderCallback.moveTo(position, moduleId);
        viewModel.setSelectedModule(moduleId);
    }

    private void populateRecyclerView(List<ModuleEntity> modules) {
        fragmentModuleListBinding.progressBar.setVisibility(View.GONE);
        adapter.setModules(modules);
        fragmentModuleListBinding.rvModule.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentModuleListBinding.rvModule.setHasFixedSize(true);
        fragmentModuleListBinding.rvModule.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        fragmentModuleListBinding.rvModule.addItemDecoration(dividerItemDecoration);
    }
}