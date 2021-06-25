package com.andres.moviecatalogue.ui.movie;

import androidx.lifecycle.ViewModel;

import com.andres.moviecatalogue.data.DataEntity;
import com.andres.moviecatalogue.utils.DataDummy;

import java.util.List;

public class MovieViewModel extends ViewModel {
    public List<DataEntity> getMovies() {
        return DataDummy.generateMoviesData();
    }
}
