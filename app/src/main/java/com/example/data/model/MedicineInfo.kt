package com.example.data.model

data class MedicineDatabaseEntry(
    val id: String,
    val brandName: String,
    val genericName: String,
    val category: String,
    val standardDosageForms: String,
    val commonUsesEn: String,
    val commonUsesKn: String,
    val commonUsesHi: String,
    val directionsEn: String,
    val directionsKn: String,
    val directionsHi: String,
    val warningsEn: String,
    val warningsKn: String,
    val warningsHi: String,
    val sideEffectsEn: String,
    val sideEffectsKn: String,
    val sideEffectsHi: String,
    val source: String = "Indian Pharmacopoeia & WHO Essential Medicines List"
)
