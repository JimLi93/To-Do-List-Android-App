package com.example.myapplication.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * Class that handles saving and retrieving pet preferences
 */

private const val PET_PREFERENCES_NAME = "pet_preferences"

// Create a DataStore instance using the preferencesDataStore delegate, with the Context as
// receiver.
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PET_PREFERENCES_NAME)

class PetDataStore(context: Context) {
    private val PET_INDEX = intPreferencesKey("pet_index")

    val preferenceFlow: Flow<Int> = context.dataStore.data.catch {
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
        } else throw it
    }.map { it[PET_INDEX] ?: 0 }  // On the first run of the app, we will use the 1st pet by default

    suspend fun savePetToPreferencesStore(petIndex: Int, context: Context) {
        context.dataStore.edit { it[PET_INDEX] = petIndex }
    }
}