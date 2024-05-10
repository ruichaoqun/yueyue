package com.ruichaoqun.core.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.ruichaoqun.yueyue.core.model.UserBean
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreDataSource(val context: Application, val gson: Gson) : LocalDataSource {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
    private val USER_INFO = stringPreferencesKey("userinfo")

    override fun getUserInfo(): Flow<UserBean> = context.dataStore.data.map {
        gson.fromJson(it[USER_INFO] ?: "{}", UserBean::class.java)
    }

    override suspend fun saveUserInfo(userInfo: UserBean) {
        context.dataStore.edit {
            it[USER_INFO] = gson.toJson(userInfo)
        }
    }
}