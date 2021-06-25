package com.andres.moviecatalogue.ui.detail;

import com.andres.moviecatalogue.data.DataEntity;
import com.andres.moviecatalogue.utils.DataDummy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TvShowDetailViewModelTest {
    private DetailViewModel viewModel;
    private DataEntity dummyTvShow = DataDummy.generateTvShowsData().get(0);
    private String tvShowTitle = dummyTvShow.getTitle();

    @Before
    public void setUp() {
        viewModel = new DetailViewModel();
        viewModel.setSelectedEntity(tvShowTitle);
    }

    @Test
    public void getEntity() {
        DataEntity tvShowEntity = viewModel.getEntity();
        assertNotNull(tvShowEntity);
        assertEquals(dummyTvShow.getTitle(), tvShowEntity.getTitle());
        assertEquals(dummyTvShow.getGenre(), tvShowEntity.getGenre());
        assertEquals(dummyTvShow.getReleaseDate(), tvShowEntity.getReleaseDate());
        assertEquals(dummyTvShow.getDuration(), tvShowEntity.getDuration());
        assertEquals(dummyTvShow.getSlogan(), tvShowEntity.getSlogan());
        assertEquals(dummyTvShow.getOverview(), tvShowEntity.getOverview());
        assertEquals(dummyTvShow.getImagePath(), tvShowEntity.getImagePath());
    }
}
