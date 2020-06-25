package com.loop.ideas.apps.androidblog01.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loop.ideas.apps.androidblog01.domain.use_case.GetUserUseCase
import com.loop.ideas.apps.androidblog01.domain.use_case.GetUsersUseCase
import com.loop.ideas.apps.androidblog01.presentation.mapper.UserPresentationMapper

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
class MainViewModelFactory(private val getUserUseCase: GetUserUseCase,
                           private val getUsersUseCase: GetUsersUseCase,
                           private val mapper: UserPresentationMapper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getUserUseCase, getUsersUseCase, mapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}