package com.andres.moviecatalogue.ui.tvshow;

import com.andres.moviecatalogue.data.DataEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TvShowViewModelTest {
    private TvShowViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new TvShowViewModel();
    }

    @Test
    public void getTvShows() {
        List<DataEntity> tvShowEntities = viewModel.getTvShows();
        assertNotNull(tvShowEntities);
        assertEquals(12, tvShowEntities.size());
    }
}