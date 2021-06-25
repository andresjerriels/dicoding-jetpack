package com.andres.academy.ui.bookmark;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.andres.academy.R;
import com.andres.academy.data.CourseEntity;
import com.andres.academy.databinding.FragmentBookmarkBinding;
import com.andres.academy.utils.DataDummy;
import com.andres.academy.viewmodel.ViewModelFactory;

import java.util.List;


public class BookmarkFragment extends Fragment implements BookmarkFragmentCallback {
    private FragmentBookmarkBinding fragmentBookmarkBinding;

    public BookmarkFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBookmarkBinding = FragmentBookmarkBinding.inflate(inflater, container, false);
        return fragmentBookmarkBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            BookmarkViewModel viewModel = new ViewModelProvider(this, factory).get(BookmarkViewModel.class);

            BookmarkAdapter adapter = new BookmarkAdapter(this);
            fragmentBookmarkBinding.progressBar.setVisibility(View.VISIBLE);
            viewModel.getBookmarks().observe(getViewLifecycleOwner(), courses -> {
                fragmentBookmarkBinding.progressBar.setVisibility(View.GONE);
                adapter.setCourses(courses);
                adapter.notifyDataSetChanged();
            });

            fragmentBookmarkBinding.rvBookmark.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentBookmarkBinding.rvBookmark.setHasFixedSize(true);
            fragmentBookmarkBinding.rvBookmark.setAdapter(adapter);
        }
    }

    @Override
    public void onShareClick(CourseEntity course) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang.")
                    .setText(getResources().getString(R.string.share_text, course.getTitle()))
                    .startChooser();
        }
    }
}