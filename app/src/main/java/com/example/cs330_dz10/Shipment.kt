package com.example.cs330_dz10

data class Shipment(
    val receiverName: String,
    val receiverEmail: String,
    val countryTo: String,
    val cityTo: String,
    val streetTo: String,
    val weight: Double,
    val fragile: Boolean
)
