package com.example.modoguardian_grupomg.repository

import com.example.modoguardian_grupomg.model.Rol
import com.example.modoguardian_grupomg.model.UsuarioAutenticado

interface LoginRepository {
    suspend fun login(email: String, password: String): Result<UsuarioAutenticado>
}

class DemoLoginRepository : LoginRepository {

    // 3 usuarios obligatorios según el documento oficial
    private val usuariosValidos = mapOf(
        "admin@guardian.test" to Pair("123456", Rol.ADMIN),
        "supervisor@guardian.test" to Pair("123456", Rol.SUPERVISOR),
        "operador@guardian.test" to Pair("123456", Rol.OPERADOR)
    )

    override suspend fun login(email: String, password: String): Result<UsuarioAutenticado> {
        val usuarioEncontrado = usuariosValidos[email.lowercase().trim()]

        return if (usuarioEncontrado != null && usuarioEncontrado.first == password) {
            Result.success(UsuarioAutenticado(email, usuarioEncontrado.second))
        } else {
            Result.failure(Exception("Credenciales incorrectas. Revisa tu correo o contraseña."))
        }
    }
}