package com.loop.ideas.apps.androidblog01.data.local

import com.loop.ideas.apps.androidblog01.data.model.UserLocal

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
interface LocalDataSource {

    suspend fun getLocalUser(userId: Int) : UserLocal

    suspend fun getLocalUsers() : List<UserLocal>

}