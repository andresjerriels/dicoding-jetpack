package com.andres.moviecatalogue.ui.movie;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andres.moviecatalogue.R;
import com.andres.moviecatalogue.data.DataEntity;
import com.andres.moviecatalogue.databinding.ItemsMovieBinding;
import com.andres.moviecatalogue.ui.detail.DetailActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final List<DataEntity> listMovies = new ArrayList<>();

    void setMovies(List<DataEntity> listMovies) {
        if (listMovies == null) return;
        this.listMovies.clear();
        this.listMovies.addAll(listMovies);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsMovieBinding binding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        DataEntity movie = listMovies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }


    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemsMovieBinding binding;

        MovieViewHolder(ItemsMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(DataEntity movie) {
            binding.tvItemTitle.setText(movie.getTitle());
            binding.tvItemDate.setText(movie.getReleaseDate());
            binding.tvItemGenre.setText(movie.getGenre());
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_DETAILS, movie.getTitle());
                itemView.getContext().startActivity(intent);
            });
            Glide.with(itemView.getContext())
                    .load(movie.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.imgPoster);
        }
    }
}
