package com.loop.ideas.apps.androidblog01.manual_di

import com.loop.ideas.apps.androidblog01.data.local.LocalDataSource
import com.loop.ideas.apps.androidblog01.data.local.LocalDataSourceImpl
import com.loop.ideas.apps.androidblog01.data.mapper.UserLocalMapper
import com.loop.ideas.apps.androidblog01.data.mapper.UserLocalMapperImpl
import com.loop.ideas.apps.androidblog01.data.repository.UserRepositoryImpl
import com.loop.ideas.apps.androidblog01.domain.repository.UserRepository
import com.loop.ideas.apps.androidblog01.domain.use_case.GetUserUseCase
import com.loop.ideas.apps.androidblog01.domain.use_case.GetUsersUseCase
import com.loop.ideas.apps.androidblog01.presentation.mapper.UserPresentationMapper
import com.loop.ideas.apps.androidblog01.presentation.mapper.UserPresentationMapperImpl
import kotlinx.coroutines.Dispatchers

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
/**
 * This can be replaced for Dagger, Koin or Hilt. But for the sake of simplicity we are using manual DI.
 */
object AppInjector {

    private fun provideLocalDataSource() : LocalDataSource {
        return LocalDataSourceImpl()
    }

    private fun provideLocalMapper() : UserLocalMapper {
        return UserLocalMapperImpl()
    }

    private fun provideUserRepository() : UserRepository {
        return UserRepositoryImpl(
            dataSource = provideLocalDataSource(),
            mapper = provideLocalMapper()
        )
    }

    fun providePresentationMapper() : UserPresentationMapper {
        return UserPresentationMapperImpl()
    }

    fun provideGetUserUseCase() : GetUserUseCase {
        return GetUserUseCase(
            repository = provideUserRepository(),
            backgroundDispatcher = Dispatchers.IO
        )
    }

    fun provideGetUsersUseCase() : GetUsersUseCase {
        return GetUsersUseCase(
            repository =  provideUserRepository(),
            backgroundDispatcher = Dispatchers.IO
        )
    }

}