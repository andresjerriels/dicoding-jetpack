package com.andres.academy.di;

import android.content.Context;

import com.andres.academy.data.source.AcademyRepository;
import com.andres.academy.data.source.remote.RemoteDataSource;
import com.andres.academy.utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Context context) {
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));
        return AcademyRepository.getInstance(remoteDataSource);
    }
}
