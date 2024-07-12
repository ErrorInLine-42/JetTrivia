package com.example.jettrivia.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "trivia_progress")


object PreferencesKeys {
    val QUESTION_INDEX = intPreferencesKey("question_index")
}

suspend fun saveQuestionIndex(context: Context, index: Int) {
    context.dataStore.edit { preferences ->
        preferences[PreferencesKeys.QUESTION_INDEX] = index
    }
}

fun getQuestionIndex(context: Context): Flow<Int> = context.dataStore.data
    .map { preferences ->
        preferences[PreferencesKeys.QUESTION_INDEX] ?: 0
    }
