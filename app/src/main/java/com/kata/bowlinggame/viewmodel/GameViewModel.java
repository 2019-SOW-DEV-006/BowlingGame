package com.kata.bowlinggame.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.kata.bowlinggame.domain.ScoreBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameViewModel {
    static final List<String> NEW_SCORE_BOARD;
    private MutableLiveData<ScoreBoard> scoreBoard = new MutableLiveData<>();

    static {
        NEW_SCORE_BOARD = new ArrayList<>(Arrays.asList(
           "","",
           "","",
           "","",
           "","",
           "","",
           "","",
           "","",
           "","",
           "","",
           "","",
           "",""
        ));
    }

    LiveData<ScoreBoard> getScoreBoard() {
        return scoreBoard;
    }

    void newGame() {
        scoreBoard.setValue(new ScoreBoard(NEW_SCORE_BOARD));
    }
}
