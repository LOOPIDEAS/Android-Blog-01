package com.loop.ideas.apps.androidblog01.domain.use_case

import android.util.Log
import com.loop.ideas.apps.androidblog01.domain.model.UserDomain
import com.loop.ideas.apps.androidblog01.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
class GetUserUseCase(private val repository: UserRepository,
                     private val backgroundDispatcher: CoroutineDispatcher)  {

    suspend fun invoke(userId: Int): UserDomain = withContext(backgroundDispatcher) {
        Log.i("GetUserUseCase", "Context: ${Thread.currentThread().name}")
        repository.getUser(userId)
    }
}