package com.andres.moviecatalogue.ui.detail;

import com.andres.moviecatalogue.data.DataEntity;
import com.andres.moviecatalogue.utils.DataDummy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieDetailViewModelTest {
    private DetailViewModel viewModel;
    private DataEntity dummyMovie = DataDummy.generateMoviesData().get(0);
    private String movieTitle = dummyMovie.getTitle();

    @Before
    public void setUp() {
        viewModel = new DetailViewModel();
        viewModel.setSelectedEntity(movieTitle);
    }

    @Test
    public void getEntity() {
        DataEntity movieEntity = viewModel.getEntity();
        assertNotNull(movieEntity);
        assertEquals(dummyMovie.getTitle(), movieEntity.getTitle());
        assertEquals(dummyMovie.getGenre(), movieEntity.getGenre());
        assertEquals(dummyMovie.getReleaseDate(), movieEntity.getReleaseDate());
        assertEquals(dummyMovie.getDuration(), movieEntity.getDuration());
        assertEquals(dummyMovie.getSlogan(), movieEntity.getSlogan());
        assertEquals(dummyMovie.getOverview(), movieEntity.getOverview());
        assertEquals(dummyMovie.getImagePath(), movieEntity.getImagePath());
    }
}