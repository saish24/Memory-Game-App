package com.example.memorygame

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.models.BoardSize
import com.example.memorygame.models.MemoryGame
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var clRoot : ConstraintLayout
    private lateinit var rvBoard : RecyclerView
    private lateinit var tvMoves : TextView
    private lateinit var tvNumPairs : TextView

    private lateinit var memoryGame : MemoryGame
    private lateinit var adapter : MemoryBoardAdapter
    private var boardSize = BoardSize.HARD
    private var moves = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBoard    = findViewById(R.id.rv_board)
        tvMoves    = findViewById(R.id.tv_moves)
        tvNumPairs = findViewById(R.id.tv_numPairs)
        clRoot = findViewById(R.id.clRoot)
        memoryGame = MemoryGame(boardSize)

        adapter = MemoryBoardAdapter(this, boardSize, memoryGame.cards, object : MemoryBoardAdapter.CardClickListener{
            override fun OnCardClicked(position: Int) = updateGame(position)
        })

        rvBoard.adapter = adapter
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }

    private fun updateGame(position: Int) {
        when {
            memoryGame.numPairsFound == boardSize.getNumPairs() ->
                Snackbar.make(clRoot, "You already won!", Snackbar.LENGTH_SHORT).show()

            memoryGame.cards[position].isFaceUp ->
                Snackbar.make(clRoot, "Invalid Move", Snackbar.LENGTH_SHORT).show()

            else -> {
                memoryGame.flip(position)
                adapter.notifyDataSetChanged()
                moves++
                tvMoves.setText("Moves : $moves")
                tvNumPairs.setText("Pairs : ${memoryGame.numPairsFound} / ${boardSize.getNumPairs()}")

            }
        }
    }

}