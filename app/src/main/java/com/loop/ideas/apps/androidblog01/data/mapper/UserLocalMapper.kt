package com.loop.ideas.apps.androidblog01.data.mapper

import com.loop.ideas.apps.androidblog01.data.model.UserLocal
import com.loop.ideas.apps.androidblog01.domain.model.UserDomain

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
interface UserLocalMapper {

    suspend fun mapLocalUserToDomain(localUser: UserLocal) : UserDomain

    suspend fun mapLocalUsersToDomain(localUsers: List<UserLocal>) : List<UserDomain>

}