package com.example.apartment_booking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
)
{

    val turquoise = Color(0xFF2EC4B6)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(turquoise)
            .padding(horizontal = 24.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        // TOP BAR
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // LOGO
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(52.dp)
            )

            TextButton(onClick = onLoginClick) {
                Text("Log in", color = Color.White)
            }
        }

        // MIDDLE CONTENT
        Column(
            modifier = Modifier
                .offset(y = (-8).dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Macedonia Apartment Booking",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Light,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Book your favorite apartment today!",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.85f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // GOOGLE BUTTON
            GoogleLoginButton(
                onClick = onLoginClick,
                turquoise = turquoise
            )

            Spacer(modifier = Modifier.height(12.dp))

            // CREATE ACCOUNT
            OutlinedButton(
                onClick = onRegisterClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                ),
                border = ButtonDefaults.outlinedButtonBorder
            ) {
                Text("Create account")
            }
        }

        // BOTTOM TEXT
        Text(
            text = "I accept terms and conditions",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.White.copy(alpha = 0.8f),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun GoogleLoginButton(
    onClick: () -> Unit,
    turquoise: Color
) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = turquoise
        )
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = "Google Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text("Continue with Google")
        }
    }
}