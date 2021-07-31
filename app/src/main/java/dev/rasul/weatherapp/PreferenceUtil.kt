package dev.rasul.weatherapp

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import dev.rasul.weatherapp.entity.PlacesEntity

object PreferenceUtil {
    private const val IS_FIRST = "is_first"
    private const val SELECTED_PLACE = "selected_place"

    fun isFirstOpen(context: Context): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(IS_FIRST, true)
    }

    fun setIsFirstOpen(context: Context, isFirst: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context).edit {
            putBoolean(IS_FIRST, isFirst)
        }
    }

    fun setSelectedPlace(context: Context, place: PlacesEntity) {
        PreferenceManager.getDefaultSharedPreferences(context).edit {
            putString(SELECTED_PLACE, Gson().toJson(place))
        }
    }

    fun getSelectedPlace(context: Context): PlacesEntity? {
        val placeJson = PreferenceManager.getDefaultSharedPreferences(context).getString(
            SELECTED_PLACE, null
        )
        placeJson?.let {
            return Gson().fromJson(it, PlacesEntity::class.java)
        }
        return null
    }
}