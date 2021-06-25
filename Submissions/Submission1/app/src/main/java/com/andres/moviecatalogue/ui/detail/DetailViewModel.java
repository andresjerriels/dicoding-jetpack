package com.andres.moviecatalogue.ui.detail;

import androidx.lifecycle.ViewModel;

import com.andres.moviecatalogue.data.DataEntity;
import com.andres.moviecatalogue.utils.DataDummy;

import java.util.ArrayList;

public class DetailViewModel extends ViewModel {
    private String title;

    public void setSelectedEntity(String title) {
        this.title = title;
    }

    public DataEntity getEntity() {
        DataEntity data = null;
        ArrayList<DataEntity> entities = DataDummy.generateMoviesData();
        entities.addAll(DataDummy.generateTvShowsData());

        for (DataEntity entity : entities) {
            if (entity.getTitle().equals(title)) {
                data = entity;
            }
        }
        return data;
    }
}
