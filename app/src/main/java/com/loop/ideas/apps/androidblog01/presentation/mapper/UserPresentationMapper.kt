package com.loop.ideas.apps.androidblog01.presentation.mapper

import com.loop.ideas.apps.androidblog01.domain.model.UserDomain
import com.loop.ideas.apps.androidblog01.presentation.model.UserPresentation

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
interface UserPresentationMapper {

    suspend fun mapDomainUserToPresentation(domainUser: UserDomain) : UserPresentation

    suspend fun mapDomainUsersToPresentation(domainUsers: List<UserDomain>) : List<UserPresentation>

}