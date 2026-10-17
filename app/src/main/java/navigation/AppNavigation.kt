package com.example.modoguardian_grupomg.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modoguardian_grupomg.model.Rol
import com.example.modoguardian_grupomg.ui.screens.HomeScreen
import com.example.modoguardian_grupomg.ui.screens.LoginScreen
import com.example.modoguardian_grupomg.viewmodel.LoginViewModel

@Composable
fun AppNavigation(loginViewModel: LoginViewModel = viewModel()) {
    val navController = rememberNavController()
    val uiState by loginViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                uiState = uiState,
                onEmailChange = { loginViewModel.onEmailChange(it) },
                onPasswordChange = { loginViewModel.onPasswordChange(it) },
                onLoginClick = {
                    loginViewModel.onLogin { rol ->
                        when (rol) {
                            Rol.ADMIN -> navController.navigate(Screen.HomeAdmin.route)
                            Rol.SUPERVISOR -> navController.navigate(Screen.HomeSupervisor.route)
                            Rol.OPERADOR -> navController.navigate(Screen.HomeOperator.route)
                        }
                    }
                }
            )
        }

        composable(Screen.HomeAdmin.route) {
            HomeScreen(
                loginViewModel = loginViewModel,
                onLogout = {
                    loginViewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.HomeSupervisor.route) {
            HomeScreen(
                loginViewModel = loginViewModel,
                onLogout = {
                    loginViewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.HomeOperator.route) {
            HomeScreen(
                loginViewModel = loginViewModel,
                onLogout = {
                    loginViewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}