package com.example.hpapi.data

data class Character(
    val id: String,
    val name: String?,
    val alternate_names: List<String>?,
    val species: String?,
    val house: String?,
    val image: String?
)