package com.loop.ideas.apps.androidblog01.domain.repository

import com.loop.ideas.apps.androidblog01.domain.model.UserDomain

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
interface UserRepository {

    suspend fun getUser(userId: Int) : UserDomain

    suspend fun getUsers() : List<UserDomain>
}