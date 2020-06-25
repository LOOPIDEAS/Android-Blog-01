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
class UserPresentationMapperImpl : UserPresentationMapper {

    override suspend fun mapDomainUserToPresentation(domainUser: UserDomain): UserPresentation {
        return UserPresentation (
            id = domainUser.id.toString(),
            name = domainUser.name,
            twitter = domainUser.twitter,
            twitterId = domainUser.twitter.substring(domainUser.twitter.lastIndexOf("/") + 1)
        )
    }

    override suspend fun mapDomainUsersToPresentation(domainUsers: List<UserDomain>): List<UserPresentation> {
        return domainUsers.map { mapDomainUserToPresentation(it) }
    }
}