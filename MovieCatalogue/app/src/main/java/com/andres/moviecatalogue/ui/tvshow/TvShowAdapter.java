package com.andres.moviecatalogue.ui.tvshow;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andres.moviecatalogue.R;
import com.andres.moviecatalogue.data.DataEntity;
import com.andres.moviecatalogue.databinding.ItemsTvshowBinding;
import com.andres.moviecatalogue.ui.detail.DetailActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {
    private final List<DataEntity> listTvShows = new ArrayList<>();

    void setTvShows(List<DataEntity> listTvShows) {
        if (listTvShows == null) return;
        this.listTvShows.clear();
        this.listTvShows.addAll(listTvShows);
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsTvshowBinding binding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TvShowViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        DataEntity movie = listTvShows.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return listTvShows.size();
    }


    static class TvShowViewHolder extends RecyclerView.ViewHolder {
        private final ItemsTvshowBinding binding;

        TvShowViewHolder(ItemsTvshowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(DataEntity tvshow) {
            binding.tvItemTitle.setText(tvshow.getTitle());
            binding.tvItemDate.setText(tvshow.getReleaseDate());
            binding.tvItemGenre.setText(tvshow.getGenre());
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_DETAILS, tvshow.getTitle());
                itemView.getContext().startActivity(intent);
            });
            Glide.with(itemView.getContext())
                    .load(tvshow.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.imgPoster);
        }
    }
}
