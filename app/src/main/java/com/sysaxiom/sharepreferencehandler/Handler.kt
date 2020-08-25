package com.sysaxiom.sharepreferencehandler

import `in`.co.ophio.secure.core.KeyStoreKeyGenerator
import `in`.co.ophio.secure.core.ObscuredPreferencesBuilder
import android.app.Application
import android.content.SharedPreferences

class Handler {

    companion object {

        const val STRING: String = ""
        const val INT: Int = 0
        const val FLOAT: Float = 0.0F
        const val LONG: Long = 0L
        const val BOOLEAN: Boolean = false

        fun savePreferenceValue(preferenceKey: String, preferenceValue: Any, app: Application) {
            try {
                val sharedPreferences = getPreferences(app)
                when(preferenceValue) {
                    is String -> {
                        if(preferenceKey.isNotEmpty() && preferenceValue.isNotEmpty()){
                            val editor = sharedPreferences.edit()
                            editor?.putString(preferenceKey, preferenceValue)
                            editor?.apply()
                        } else return
                    }
                    is Int -> {
                        if(preferenceKey.isNotEmpty()){
                            val editor = sharedPreferences.edit()
                            editor?.putInt(preferenceKey, preferenceValue)
                            editor?.apply()
                        } else return
                    }
                    is Boolean -> {
                        if(preferenceKey.isNotEmpty()){
                            val editor = sharedPreferences.edit()
                            editor?.putBoolean(preferenceKey, preferenceValue)
                            editor?.apply()
                        } else return
                    }
                    is Float -> {
                        if(preferenceKey.isNotEmpty()){
                            val editor = sharedPreferences.edit()
                            editor?.putFloat(preferenceKey, preferenceValue)
                            editor?.apply()
                        } else return
                    }
                    is Long -> {
                        if(preferenceKey.isNotEmpty()){
                            val editor = sharedPreferences.edit()
                            editor?.putLong(preferenceKey, preferenceValue)
                            editor?.apply()
                        } else return
                    }
                    else -> {
                        //do nothing
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun <T: Any> getPreferenceValue(preferenceKey: String, type: T, app: Application) : T {
            try {
                val sharedPreferences = getPreferences(app)
                when(type) {
                    is String -> {
                        return if(preferenceKey.isNotEmpty()){
                            sharedPreferences.getString(preferenceKey, "") as T
                        } else "" as T
                    }
                    is Int -> {
                        return if(preferenceKey.isNotEmpty()){
                            sharedPreferences.getInt(preferenceKey, 0) as T
                        } else 0 as T
                    }
                    is Boolean -> {
                        return if(preferenceKey.isNotEmpty()){
                            sharedPreferences.getBoolean(preferenceKey, false) as T
                        } else false as T
                    }
                    is Float -> {
                        return if(preferenceKey.isNotEmpty()){
                            sharedPreferences.getFloat(preferenceKey, 0.0F) as T
                        } else 0.0F as T
                    }
                    is Long -> {
                        return if(preferenceKey.isNotEmpty()){
                            sharedPreferences.getLong(preferenceKey, 0) as T
                        } else 0 as T
                    }
                    else -> {
                        return "" as T
                    }
                }
            } catch (e: Exception){
                return "" as T
                e.printStackTrace()
            }
        }

        fun clearValues(app: Application){
            try{
                val editor = getPreferences(app).edit()
                editor.clear()
                editor.apply()
            } catch (e: Exception){
                e.printStackTrace()
            }
        }

        fun clearValue(key: String, app: Application) {
            try{
                val editor = getPreferences(app).edit()
                editor.remove(key)
                editor.apply()
            } catch (e: Exception){
                e.printStackTrace()
            }
        }

        private fun getPreferences(app: Application) : SharedPreferences{
            val tag = Handler::class.java.name
            val secretKey: String = try {
                KeyStoreKeyGenerator.get(app, app.packageName + tag).loadOrGenerateKeys()
            } catch (e: Exception) {
                app.applicationInfo.loadLabel(app.packageManager).toString()
            }
            return ObscuredPreferencesBuilder()
                .setApplication(app)
                .obfuscateValue(true)
                .obfuscateKey(true)
                .setSharePrefFileName(tag)
                .setSecret(secretKey)
                .createSharedPrefs()
        }
    }
}

