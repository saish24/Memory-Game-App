package com.example.memorygame

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.models.BoardSize
import com.example.memorygame.models.MemoryGame

class MainActivity : AppCompatActivity() {

    private lateinit var rvBoard : RecyclerView
    private lateinit var tvMoves : TextView
    private lateinit var tvNumPairs : TextView

    private lateinit var memoryGame :MemoryGame
    private lateinit var adapter: MemoryBoardAdapter
    private var boardSize : BoardSize = BoardSize.EASY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBoard    = findViewById(R.id.rv_board)
        tvMoves    = findViewById(R.id.tv_moves)
        tvNumPairs = findViewById(R.id.tv_numPairs)
        memoryGame = MemoryGame(boardSize)

        adapter = MemoryBoardAdapter(this, boardSize, memoryGame.cards, object : MemoryBoardAdapter.CardClickListener{
            override fun OnCardClicked(position: Int) = updateGame(position)
        })

        rvBoard.adapter = adapter
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }

    private fun updateGame(position: Int) {

    }

}