package com.inforcap.exampledrawerapp

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object UserRepository {
    private const val PREFS_NAME = "user_prefs"
    private const val KEY_USERS = "users"

    val listaUsuarios = mutableListOf<User>()

    fun saveUsers(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val jsonArray = JSONArray()
        for (user in listaUsuarios) {
            val obj = JSONObject()
            obj.put("nombre", user.nombre)
            obj.put("apellido", user.apellido)
            obj.put("email", user.email)
            obj.put("telefono", user.telefono)
            obj.put("ciudad", user.ciudad)
            jsonArray.put(obj)
        }
        prefs.edit().putString(KEY_USERS, jsonArray.toString()).apply()
    }

    fun loadUsers(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val data = prefs.getString(KEY_USERS, null) ?: return
        val jsonArray = JSONArray(data)
        listaUsuarios.clear()
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val user = User(
                obj.getString("nombre"),
                obj.getString("apellido"),
                obj.getString("email"),
                obj.getString("telefono"),
                obj.getString("ciudad")
            )
            listaUsuarios.add(user)
        }
    }
}