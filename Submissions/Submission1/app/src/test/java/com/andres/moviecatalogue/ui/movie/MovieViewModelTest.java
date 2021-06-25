package com.andres.moviecatalogue.ui.movie;

import com.andres.moviecatalogue.data.DataEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MovieViewModelTest {

    private MovieViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new MovieViewModel();
    }

    @Test
    public void getMovies() {
        List<DataEntity> movieEntities = viewModel.getMovies();
        assertNotNull(movieEntities);
        assertEquals(12, movieEntities.size());
    }
}