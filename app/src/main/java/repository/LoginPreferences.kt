package com.example.modoguardian_grupomg.repository

import android.content.Context

/** Elimina el correo guardado por versiones anteriores; el login no recuerda cuentas. */
class LoginPreferences(context: Context) {
    private val preferences = context.getSharedPreferences("login_demo", Context.MODE_PRIVATE)

    fun borrarCorreoRecordado() {
        preferences.edit().remove(KEY_EMAIL).apply()
    }

    companion object {
        private const val KEY_EMAIL = "remembered_email"
    }
}
