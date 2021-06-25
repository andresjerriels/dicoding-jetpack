package com.andres.moviecatalogue.ui.detail;

import android.os.Bundle;

import com.andres.moviecatalogue.data.DataEntity;
import com.andres.moviecatalogue.databinding.ActivityDetailBinding;
import com.andres.moviecatalogue.databinding.ContentDetailBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;

import com.andres.moviecatalogue.R;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_DETAILS = "extra_details";
    private ContentDetailBinding contentDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDetailBinding activityDetailBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        contentDetailBinding = activityDetailBinding.detailContent;

        setContentView(activityDetailBinding.getRoot());
        setSupportActionBar(activityDetailBinding.toolbar);
        setTitle("Details");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        DetailViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString(EXTRA_DETAILS);
            if (title != null) {
                viewModel.setSelectedEntity(title);
                populateDetails(viewModel.getEntity());
            }
        }
    }

    private void populateDetails(DataEntity dataEntity) {
        contentDetailBinding.textTitle.setText(dataEntity.getTitle());
        contentDetailBinding.textDuration.setText(dataEntity.getDuration());
        contentDetailBinding.textGenre.setText(dataEntity.getGenre());
        contentDetailBinding.textReleaseDate.setText(dataEntity.getReleaseDate());
        contentDetailBinding.textSlogan.setText(dataEntity.getSlogan());
        contentDetailBinding.textOverview.setText(dataEntity.getOverview());

        Glide.with(this)
                .load(dataEntity.getImagePath())
                .transform(new RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .into(contentDetailBinding.imagePoster);
    }
}