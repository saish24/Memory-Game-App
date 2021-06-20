package com.example.memorygame.models

import com.example.memorygame.MemoryBoardAdapter
import com.example.memorygame.utils.DEFAULT_ICONS

class MemoryGame(private val boardSize: BoardSize) {
    lateinit var cards: List<MemoryCard>
    var numPairsFound: Int = 0

    init {
        val chosen = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        cards = (chosen + chosen).shuffled().map { MemoryCard(it) }
    }
}