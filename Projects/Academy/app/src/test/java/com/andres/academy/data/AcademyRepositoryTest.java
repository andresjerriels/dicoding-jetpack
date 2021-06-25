package com.andres.academy.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.andres.academy.data.source.remote.RemoteDataSource;
import com.andres.academy.data.source.remote.response.ContentResponse;
import com.andres.academy.data.source.remote.response.CourseResponse;
import com.andres.academy.data.source.remote.response.ModuleResponse;
import com.andres.academy.utils.DataDummy;
import com.andres.academy.utils.LiveDataTestUtil;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcademyRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteDataSource remote = Mockito.mock(RemoteDataSource.class);
    private FakeAcademyRepository academyRepository = new FakeAcademyRepository(remote);

    private ArrayList<CourseResponse> courseResponses = (ArrayList<CourseResponse>) DataDummy.generateRemoteDummyCourses();
    private String courseId = courseResponses.get(0).getId();
    private ArrayList<ModuleResponse> moduleResponses = (ArrayList<ModuleResponse>) DataDummy.generateRemoteDummyModules(courseId);
    private String moduleId = moduleResponses.get(0).getModuleId();
    private ContentResponse content = DataDummy.generateRemoteDummyContent(moduleId);

    @Test
    public void getAllCourses() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadCoursesCallback) invocation.getArguments()[0])
                    .onAllCoursesReceived(courseResponses);
            return null;
        }).when(remote).getAllCourses(any(RemoteDataSource.LoadCoursesCallback.class));
        ArrayList<CourseEntity> courseEntities = (ArrayList<CourseEntity>) LiveDataTestUtil.getValue(academyRepository.getAllCourses());
        verify(remote).getAllCourses(any(RemoteDataSource.LoadCoursesCallback.class));
        assertNotNull(courseEntities);
        assertEquals(courseResponses.size(),courseEntities.size());
    }

    @Test
    public void getCourseWithModules() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadCoursesCallback) invocation.getArguments()[0])
                    .onAllCoursesReceived(courseResponses);
            return null;
        }).when(remote).getAllCourses(any(RemoteDataSource.LoadCoursesCallback.class));
        CourseEntity course = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId));
        verify(remote).getAllCourses(any(RemoteDataSource.LoadCoursesCallback.class));
        assertNotNull(course);
        assertEquals(courseResponses.get(0).getTitle(), course.getTitle());
    }

    @Test
    public void getAllModulesByCourse() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadModulesCallback) invocation.getArguments()[1])
                    .onAllModulesReceived(moduleResponses);
            return null;
        }).when(remote).getModules(eq(courseId), any(RemoteDataSource.LoadModulesCallback.class));
        ArrayList<ModuleEntity> moduleEntities = (ArrayList<ModuleEntity>) LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId));
        verify(remote).getModules(eq(courseId), any(RemoteDataSource.LoadModulesCallback.class));
        assertNotNull(moduleEntities);
        assertEquals(moduleResponses.size(), moduleEntities.size());
    }

    @Test
    public void getBookmarkedCourses() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadCoursesCallback) invocation.getArguments()[0])
                    .onAllCoursesReceived(courseResponses);
            return null;
        }).when(remote).getAllCourses(any(RemoteDataSource.LoadCoursesCallback.class));
        ArrayList<CourseEntity> courseEntities = (ArrayList<CourseEntity>) LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses());
        verify(remote).getAllCourses(any(RemoteDataSource.LoadCoursesCallback.class));
        assertNotNull(courseEntities);
        assertEquals(courseResponses.size(), courseEntities.size());
    }

    @Test
    public void getContent() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadModulesCallback) invocation.getArguments()[1])
                    .onAllModulesReceived(moduleResponses);
            return null;
        }).when(remote).getModules(eq(courseId), any(RemoteDataSource.LoadModulesCallback.class));
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadContentCallback) invocation.getArguments()[1])
                    .onContentReceived(content);
            return null;
        }).when(remote).getContent(eq(moduleId), any(RemoteDataSource.LoadContentCallback.class));
        ModuleEntity module = LiveDataTestUtil.getValue(academyRepository.getContent(courseId, moduleId));
        verify(remote).getModules(eq(courseId), any(RemoteDataSource.LoadModulesCallback.class));
        verify(remote).getContent(eq(moduleId), any(RemoteDataSource.LoadContentCallback.class));
        assertNotNull(module);
        assertEquals(content.getContent(), module.contentEntity.getContent());
    }


}