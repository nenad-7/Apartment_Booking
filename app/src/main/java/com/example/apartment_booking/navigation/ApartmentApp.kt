package com.example.apartment_booking.navigation

import androidx.compose.runtime.*
import com.example.apartment_booking.ui.screens.HomeScreen
import com.example.apartment_booking.ui.screens.LoginFormScreen
import com.example.apartment_booking.ui.screens.LoginScreen
import com.example.apartment_booking.ui.screens.RegisterScreen

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
            HomeScreen(
                onLogout = {
                    currentScreen = "login"
                }
            )
        }
    }
}