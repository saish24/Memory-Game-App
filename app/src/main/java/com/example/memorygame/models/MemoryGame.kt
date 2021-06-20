package com.example.memorygame.models

import com.example.memorygame.utils.DEFAULT_ICONS

class MemoryGame(boardSize: BoardSize) {
    val cards: List<MemoryCard>
    var previousCardIndex : Int? = null
    var numPairsFound: Int = 0

    init {
        val chosen = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        cards = (chosen + chosen).shuffled().map { MemoryCard(it) }
    }

    fun flip(position: Int) {
        val card = cards[position]

        when {
            previousCardIndex == null -> {
                restore()
                previousCardIndex = position
            }

            cards[previousCardIndex!!].Identifier == card.Identifier -> {
                numPairsFound++
                card.isMatched = true
                cards[previousCardIndex!!].isMatched = true
                previousCardIndex = null
            }

            else ->  previousCardIndex = null
        }

        card.isFaceUp = !card.isFaceUp
    }

    private fun restore() {
        for(card in cards) card.isFaceUp = card.isMatched
    }

}
