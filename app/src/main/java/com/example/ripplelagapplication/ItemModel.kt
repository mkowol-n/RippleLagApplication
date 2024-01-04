package com.example.ripplelagapplication

import java.util.UUID

data class ItemModel(
    val id: String = UUID.randomUUID().toString(),
    val url: String
)