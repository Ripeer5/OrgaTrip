package com.example.orgatrip.data

import java.util.*


data class Trip(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
/*
    val participants: List<Participant>
*/
)