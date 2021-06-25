package com.andres.academy.ui.academy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.andres.academy.data.CourseEntity;
import com.andres.academy.data.source.AcademyRepository;
import com.andres.academy.utils.DataDummy;

import java.util.List;

public class AcademyViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public AcademyViewModel(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    public LiveData<List<CourseEntity>> getCourses() {
        return academyRepository.getAllCourses();
    }
}
