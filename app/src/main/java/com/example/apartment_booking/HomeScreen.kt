package com.example.apartment_booking

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

@Composable
fun HomeScreen() {

    var selectedCity by remember { mutableStateOf("All") }
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

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

    val filteredApartments = if (selectedCity == "All") {
        apartments
    } else {
        apartments.filter { it.city == selectedCity }
    }

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

        // DROPDOWN FIELD
        Box(modifier = Modifier.fillMaxWidth()) {

            OutlinedTextField(
                value = selectedCity,
                onValueChange = { },
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

                        // CLEAR BUTTON (reset to All)
                        if (selectedCity != "All") {
                            IconButton(onClick = {
                                selectedCity = "All"
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Clear"
                                )
                            }
                        }

                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.Search, contentDescription = null)
                        }
                    }
                }
            )

            // DROPDOWN MENU
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                DropdownMenuItem(
                    text = { Text("Bitola") },
                    onClick = {
                        selectedCity = "Bitola"
                        expanded = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Ohrid") },
                    onClick = {
                        selectedCity = "Ohrid"
                        expanded = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Skopje") },
                    onClick = {
                        selectedCity = "Skopje"
                        expanded = false
                    }
                )
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
}