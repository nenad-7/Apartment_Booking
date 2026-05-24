package com.example.apartment_booking.ui.screens

import androidx.compose.ui.Alignment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import com.example.apartment_booking.auth.AuthRepository
import com.example.apartment_booking.ui.components.ApartmentCard
import com.example.apartment_booking.model.Apartment
import androidx.compose.material.icons.filled.ExitToApp

@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {

    var selectedCity by remember { mutableStateOf("All") }
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    //apartment list
    val apartments = listOf(
        Apartment("Modern Apartment", "Bitola", "€300"),
        Apartment("Sunny Studio", "Bitola", "€250"),
        Apartment("City Flat", "Bitola", "€280"),
        Apartment("Lake View Apartment", "Ohrid", "€450"),
        Apartment("Cozy Studio", "Ohrid", "€350"),
        Apartment("Luxury Flat", "Ohrid", "€500"),
        Apartment("Center Apartment", "Skopje", "€400"),
        Apartment("New Studio", "Skopje", "€320"),
        Apartment("Premium Flat", "Skopje", "€550")
    )

    //filter
    val filteredApartments = if (selectedCity == "All") apartments
    else apartments.filter { it.city == selectedCity }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        // main screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Discover",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Find apartments in Macedonia",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            // DROPDOWN
            Box(modifier = Modifier.fillMaxWidth()) {

                OutlinedTextField(
                    value = selectedCity,
                    onValueChange = {},
                    readOnly = true,
                    interactionSource = interactionSource,
                    placeholder = { Text("Select city") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true },
                    shape = MaterialTheme.shapes.large,
                    trailingIcon = {
                        Row {
                            if (selectedCity != "All") {
                                IconButton(onClick = { selectedCity = "All" }) {
                                    Icon(Icons.Default.Close, contentDescription = "Clear")
                                }
                            }

                            IconButton(onClick = { expanded = true }) {
                                Icon(Icons.Default.Search, contentDescription = null)
                            }
                        }
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("Bitola", "Ohrid", "Skopje").forEach { city ->
                        DropdownMenuItem(
                            text = { Text(city) },
                            onClick = {
                                selectedCity = city
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = if (selectedCity == "All")
                    "All locations"
                else
                    "Apartments in $selectedCity",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(18.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(filteredApartments) { apartment ->
                    ApartmentCard(apartment)
                }
            }
        }

        // logout button
        IconButton(
            onClick = { onLogout() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                contentDescription = "Logout",
                modifier = Modifier.size(32.dp) // icon size
            )
        }
    }
}