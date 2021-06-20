package com.example.memorygame.models

data class MemoryCard (
    val Identifier : Int,
    var isFaceUp : Boolean = false,
    var isMatched : Boolean = false
)