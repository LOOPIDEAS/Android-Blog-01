package com.loop.ideas.apps.androidblog01.data.local

import com.loop.ideas.apps.androidblog01.data.model.UserLocal

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
class LocalDataSourceImpl : LocalDataSource {

    private val users: List<UserLocal> by lazy {
        listOf(
            UserLocal(
                id = 1,
                name = "Jake Wharton",
                twitter = "https://twitter.com/JakeWharton"
            ),
            UserLocal(
                id = 2,
                name = "Mario Sanoguera",
                twitter = "https://twitter.com/MarioSanoguera"
            ),
            UserLocal(
                id = 3,
                name = "Fernando Cejas",
                twitter = "https://twitter.com/fernando_cejas"
            ),
            UserLocal(
                id = 4,
                name = "Antonio Leiva",
                twitter = "https://twitter.com/lime_cl"
            ),
            UserLocal(
                id = 5,
                name = "Jose Alcérraca",
                twitter = "https://twitter.com/ppvi"
            )
        )
    }

    override suspend fun getLocalUser(userId: Int): UserLocal
            = users.getOrNull(userId) ?: UserLocal(-1, "Not found.", "")

    override suspend fun getLocalUsers(): List<UserLocal>
            = users.shuffled()

}