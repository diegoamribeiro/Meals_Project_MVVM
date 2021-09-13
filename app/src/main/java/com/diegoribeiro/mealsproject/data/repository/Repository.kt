package com.diegoribeiro.mealsproject.data.repository

import com.diegoribeiro.mealsproject.data.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource
){

    val remote = remoteDataSource


}