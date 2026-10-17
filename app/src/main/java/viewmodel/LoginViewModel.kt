package com.example.modoguardian_grupomg.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.modoguardian_grupomg.model.LoginErrores
import com.example.modoguardian_grupomg.model.LoginUiState
import com.example.modoguardian_grupomg.model.Rol
import com.example.modoguardian_grupomg.repository.DemoLoginRepository
import com.example.modoguardian_grupomg.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LoginRepository = DemoLoginRepository()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            errores = _uiState.value.errores.copy(email = null, general = null)
        )
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            errores = _uiState.value.errores.copy(password = null, general = null)
        )
    }

    fun onLogin(onSuccess: (Rol) -> Unit) {
        val email = _uiState.value.email
        val password = _uiState.value.password

        if (email.isBlank() || password.isBlank()) {
            _uiState.value = _uiState.value.copy(
                errores = LoginErrores(general = "Debe ingresar correo y contraseña")
            )
            return
        }

        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val result = repository.login(email, password)
            if (result.isSuccess) {
                val usuario = result.getOrNull()!!
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    usuarioLogueado = usuario
                )
                onSuccess(usuario.rol)
            } else {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errores = LoginErrores(general = result.exceptionOrNull()?.message ?: "Error al autenticar")
                )
            }
        }
    }

    fun logout() {
        // Reinicia todo el estado a cadenas vacías para no conservar el correo anterior
        _uiState.value = LoginUiState()
    }
}