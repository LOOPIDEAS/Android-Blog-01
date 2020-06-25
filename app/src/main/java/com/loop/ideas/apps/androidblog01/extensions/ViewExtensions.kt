package com.loop.ideas.apps.androidblog01.extensions

import android.view.inputmethod.EditorInfo
import android.widget.EditText

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
inline fun EditText.onSearchClicked(crossinline search: (text: String?) -> Unit) {
    this.setOnEditorActionListener { v, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            search(v.text?.toString())
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}