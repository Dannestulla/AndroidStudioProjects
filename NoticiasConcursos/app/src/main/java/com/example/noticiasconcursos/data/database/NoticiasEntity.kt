package com.example.noticiasconcursos.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noticias_table")
data class NoticiasEntity(
    @PrimaryKey val title: String,
    val description: String,
    val image_url: String,
    val link: String,
    val date: String
)
