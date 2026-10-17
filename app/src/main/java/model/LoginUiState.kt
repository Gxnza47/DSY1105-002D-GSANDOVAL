package com.example.modoguardian_grupomg.model

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errores: LoginErrores = LoginErrores(),
    val usuarioLogueado: UsuarioAutenticado? = null
)