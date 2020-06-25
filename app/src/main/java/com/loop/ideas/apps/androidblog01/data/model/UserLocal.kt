package com.loop.ideas.apps.androidblog01.data.model

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */

/**
 * Here you can use annotations from Moshi(remote), Room(local) or any other
 * library in order to keep the separation of concerns clean.
 */
data class UserLocal(val id: Int,
                     val name: String,
                     val twitter: String)