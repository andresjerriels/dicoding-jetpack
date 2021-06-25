package com.andres.academy.data.source.remote;

import android.os.Handler;
import android.os.Looper;

import com.andres.academy.data.source.remote.response.ContentResponse;
import com.andres.academy.data.source.remote.response.CourseResponse;
import com.andres.academy.data.source.remote.response.ModuleResponse;
import com.andres.academy.utils.EspressoIdlingResource;
import com.andres.academy.utils.JsonHelper;

import java.util.List;

public class RemoteDataSource {
    private static RemoteDataSource INSTANCE;
    private JsonHelper jsonHelper;
    private Handler handler = new Handler(Looper.getMainLooper());
    private final long SERVICE_LATENCY_IN_MILLIS = 1000;

    private RemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteDataSource getInstance(JsonHelper jsonHelper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(jsonHelper);
        }
        return INSTANCE;
    }

    public void getAllCourses(LoadCoursesCallback callback) {
        EspressoIdlingResource.increment();
        handler.postDelayed(() -> {
            callback.onAllCoursesReceived(jsonHelper.loadCourses());
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public void getModules(String courseId, LoadModulesCallback callback) {
        EspressoIdlingResource.increment();
        handler.postDelayed(() -> {
            callback.onAllModulesReceived(jsonHelper.loadModules(courseId));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public void getContent(String moduleId, LoadContentCallback callback) {
        EspressoIdlingResource.increment();
        handler.postDelayed(() -> {
            callback.onContentReceived(jsonHelper.loadContent(moduleId));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public interface LoadCoursesCallback {
        void onAllCoursesReceived(List<CourseResponse> courseResponses);
    }

    public interface LoadModulesCallback {
        void onAllModulesReceived(List<ModuleResponse> moduleResponses);
    }

    public interface LoadContentCallback {
        void onContentReceived(ContentResponse contentResponse);
    }
}
