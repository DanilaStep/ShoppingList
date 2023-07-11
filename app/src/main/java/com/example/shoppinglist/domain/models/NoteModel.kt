package com.example.shoppinglist.domain.models

import java.io.Serializable

data class NoteModel(
    val id: Int? = null,
    val title: String,
    val content: String,
    val time: String,
): Serializable