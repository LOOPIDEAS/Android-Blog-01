package com.loop.ideas.apps.androidblog01.domain.use_case

import android.util.Log
import com.loop.ideas.apps.androidblog01.domain.model.UserDomain
import com.loop.ideas.apps.androidblog01.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
class GetUsersUseCase(private val repository: UserRepository,
                      private val backgroundDispatcher: CoroutineDispatcher
) {

    suspend fun invoke() : List<UserDomain> = withContext(backgroundDispatcher) {
        Log.i("GetUsersUseCase", "Context: ${Thread.currentThread().name}")
        repository.getUsers()
    }

}