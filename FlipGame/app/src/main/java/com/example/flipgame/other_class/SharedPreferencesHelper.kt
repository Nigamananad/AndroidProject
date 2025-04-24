package com.example.flipgame.other_class

import android.content.Context

object SharedPreferencesHelper {
    private const val PREF_NAME = "MusicPrefs"
    private const val KEY_MUSIC_STATE = "music_enabled"
    private const val KEY_MUSIC_LAST_STATE = "music_last_state"

    fun saveMusicState(context: Context, isMusicOn: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(KEY_MUSIC_STATE, isMusicOn).apply()
    }

    fun getMusicState(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_MUSIC_STATE, true) // Default ON
    }
}
