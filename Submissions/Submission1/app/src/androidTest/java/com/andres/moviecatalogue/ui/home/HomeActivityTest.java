package com.andres.moviecatalogue.ui.home;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.andres.moviecatalogue.R;
import com.andres.moviecatalogue.data.DataEntity;
import com.andres.moviecatalogue.utils.DataDummy;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class HomeActivityTest {
    private ArrayList<DataEntity> dummyMovie = DataDummy.generateMoviesData();
    private ArrayList<DataEntity> dummyTvShows = DataDummy.generateTvShowsData();

    @Rule
    public ActivityScenarioRule activityRule = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition(dummyMovie.size()));
    }

    @Test
    public void loadDetailMovies() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText(dummyMovie.get(0).getTitle())));
        onView(withId(R.id.text_release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.text_release_date)).check(matches(withText(dummyMovie.get(0).getReleaseDate())));
        onView(withId(R.id.text_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.text_genre)).check(matches(withText(dummyMovie.get(0).getGenre())));
        onView(withId(R.id.text_duration)).check(matches(isDisplayed()));
        onView(withId(R.id.text_duration)).check(matches(withText(dummyMovie.get(0).getDuration())));
        onView(withId(R.id.text_slogan)).check(matches(isDisplayed()));
        onView(withId(R.id.text_slogan)).check(matches(withText(dummyMovie.get(0).getSlogan())));
        onView(withId(R.id.text_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.text_overview)).check(matches(withText(dummyMovie.get(0).getOverview())));
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()));


    }

    @Test
    public void loadTvShows() {
        onView(withText("TV Shows")).perform(click());
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition(dummyTvShows.size()));
    }

    @Test
    public void loadDetailTvShows() {
        onView(withText("TV Shows")).perform(click());
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText(dummyTvShows.get(0).getTitle())));
        onView(withId(R.id.text_release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.text_release_date)).check(matches(withText(dummyTvShows.get(0).getReleaseDate())));
        onView(withId(R.id.text_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.text_genre)).check(matches(withText(dummyTvShows.get(0).getGenre())));
        onView(withId(R.id.text_duration)).check(matches(isDisplayed()));
        onView(withId(R.id.text_duration)).check(matches(withText(dummyTvShows.get(0).getDuration())));
        onView(withId(R.id.text_slogan)).check(matches(isDisplayed()));
        onView(withId(R.id.text_slogan)).check(matches(withText(dummyTvShows.get(0).getSlogan())));
        onView(withId(R.id.text_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.text_overview)).check(matches(withText(dummyTvShows.get(0).getOverview())));
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()));
    }
}