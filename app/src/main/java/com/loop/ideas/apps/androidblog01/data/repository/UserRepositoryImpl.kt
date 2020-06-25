package com.loop.ideas.apps.androidblog01.data.repository

import com.loop.ideas.apps.androidblog01.data.local.LocalDataSource
import com.loop.ideas.apps.androidblog01.data.mapper.UserLocalMapper
import com.loop.ideas.apps.androidblog01.domain.model.UserDomain
import com.loop.ideas.apps.androidblog01.domain.repository.UserRepository
import kotlinx.coroutines.delay

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
class UserRepositoryImpl(
    private val dataSource: LocalDataSource,
    private val mapper: UserLocalMapper
) : UserRepository {

    override suspend fun getUser(userId: Int): UserDomain {
        delay(1500) // For create the false sensation of retrieving some information from disk or network
        return mapper.mapLocalUserToDomain(dataSource.getLocalUser(userId))
    }

    override suspend fun getUsers(): List<UserDomain> {
        delay(1500) // For create the false sensation of retrieving some information from disk or network
        return mapper.mapLocalUsersToDomain(dataSource.getLocalUsers())
    }

}