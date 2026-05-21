package com.example.apartment_booking

import androidx.compose.runtime.*

@Composable
fun ApartmentApp() {

    var currentScreen by remember { mutableStateOf("login") }

    when (currentScreen) {

        "login" -> {
            LoginScreen(
                onLoginClick = {
                    currentScreen = "loginForm"
                },
                onRegisterClick = {
                    currentScreen = "register"
                }
            )
        }

        "loginForm" -> {
            LoginFormScreen(
                onLoginSuccess = {
                    currentScreen = "home"
                }
            )
        }

        "register" -> {
            RegisterScreen(
                onRegisterSuccess = {
                    currentScreen = "home"
                }
            )
        }

        "home" -> {
            HomeScreen()
        }
    }
}