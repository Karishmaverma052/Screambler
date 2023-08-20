package com.example.screambler.ui

import android.provider.UserDictionary.Words
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel: ViewModel() {

    private var currentWord: String =""
    private var usedWords: MutableSet<String> = mutableSetOf()
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()


    init {
        resetGame()
    }

    private fun resetGame() {
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
        usedWords.clear()
    }

    private fun pickRandomWordAndShuffle(): String {
        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }
    private fun shuffleCurrentWord(word:String):String {
        val tempWord = word.toCharArray()
        tempWord.shuffle()
        while (String(tempWord).equals(word)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }


    }


