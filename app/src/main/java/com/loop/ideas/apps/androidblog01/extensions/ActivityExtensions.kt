package com.loop.ideas.apps.androidblog01.extensions

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity


/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
fun AppCompatActivity.openTwitter(twitterUserId: String?) {
    if (twitterUserId == null) return
    var intent: Intent?
    try {
        // get the Twitter app if possible
        this.packageManager.getPackageInfo("com.twitter.android", 0)
        intent = Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=$twitterUserId"))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    } catch (e: Exception) {
        // no Twitter app, revert to browser
        intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://twitter.com/$twitterUserId")
        )
    }
    this.startActivity(intent)
}