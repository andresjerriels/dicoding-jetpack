package com.andres.moviecatalogue.ui.tvshow;

import androidx.lifecycle.ViewModel;

import com.andres.moviecatalogue.data.DataEntity;
import com.andres.moviecatalogue.utils.DataDummy;

import java.util.List;

public class TvShowViewModel extends ViewModel {
    public List<DataEntity> getTvShows(){
        return DataDummy.generateTvShowsData();
    }
}
