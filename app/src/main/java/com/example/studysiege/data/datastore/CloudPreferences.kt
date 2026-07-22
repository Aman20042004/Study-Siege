package com.example.studysiege.data.datastore




import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("cloud_prefs")

class CloudPreferences(
    private val context: Context
) {

    companion object {

        private val STUDY_ID =
            stringPreferencesKey("study_id")

        private val IS_LOGGED_IN =
            booleanPreferencesKey("is_logged_in")

    }

    val studyId: Flow<String> =

        context.dataStore.data.map {

            it[STUDY_ID] ?: ""

        }

    val isLoggedIn: Flow<Boolean> =

        context.dataStore.data.map {

            it[IS_LOGGED_IN] ?: false

        }

    suspend fun saveLogin(
        studyId: String
    ) {

        context.dataStore.edit {

            it[STUDY_ID] = studyId

            it[IS_LOGGED_IN] = true

        }

    }

    suspend fun logout() {

        context.dataStore.edit {

            it.clear()

        }

    }
}