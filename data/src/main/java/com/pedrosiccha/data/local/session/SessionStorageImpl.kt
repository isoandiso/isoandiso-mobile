package com.pedrosiccha.data.local.session

import android.content.Context
import com.google.gson.Gson
import com.pedrosiccha.domain.model.UserSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SessionStorageImpl(
    private val context: Context
) : SessionStorage {

    companion object {
        private const val PREF_NAME = "user_session_prefs"
        private const val KEY_SESSION = "session_data"
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private val gson = Gson()

    override suspend fun saveSession(session: UserSession) {
        withContext(Dispatchers.IO) {
            val json = gson.toJson(session)
            sharedPreferences.edit().putString(KEY_SESSION, json).apply()
        }
    }

    override suspend fun getSession(): UserSession? {
        return withContext(Dispatchers.IO) {
            sharedPreferences.getString(KEY_SESSION, null)?.let {
                gson.fromJson(it, UserSession::class.java)
            }
        }
    }

    override suspend fun clearSession() {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().remove(KEY_SESSION).apply()
        }
    }
}