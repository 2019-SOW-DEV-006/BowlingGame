package com.kata.bowlinggame.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kata.bowlinggame.domain.PossiblePins;
import com.kata.bowlinggame.domain.ScoreBoard;
import com.kata.bowlinggame.model.BowlingGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameViewModel extends ViewModel {
    public static final List<String> ALL_POSSIBLE_BUTTONS;
    static final List<String> NEW_SCORE_BOARD;

    private MutableLiveData<ScoreBoard> scoreBoard = new MutableLiveData<>();
    private MutableLiveData<PossiblePins> possiblePins = new MutableLiveData<>();
    private MutableLiveData<Integer> gameScore = new MutableLiveData<>();

    private BowlingGame bowlingGame;
    private ScoreBoard currentScoreBoard;

    int rollIndex = 0;

    static {
        NEW_SCORE_BOARD = new ArrayList<>(getDefaultBoardUI());
        ALL_POSSIBLE_BUTTONS = new ArrayList<>(Arrays.asList(
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        ));
    }

    public GameViewModel(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
        newGame();
    }

    public LiveData<ScoreBoard> getScoreBoard() {
        return scoreBoard;
    }

    public void newGame() {
        rollIndex = 0;
        currentScoreBoard = new ScoreBoard(new ArrayList<>(getDefaultBoardUI()));
        scoreBoard.setValue(currentScoreBoard);
        possiblePins.setValue(new PossiblePins(ALL_POSSIBLE_BUTTONS));
        bowlingGame.newGame();
    }

    public void roll(int pinsDown) {

        if(bowlingGame.isGameEnds()) {
            return;
        }

        bowlingGame.pins(pinsDown);
        currentScoreBoard.board().set(rollIndex++, "" + pinsDown);
        currentScoreBoard.setPosition(rollIndex);
        incrementIndexIfStrike(pinsDown);

        scoreBoard.setValue(currentScoreBoard);
        possiblePins.setValue(new PossiblePins(bowlingGame.possiblePinsForSecondRoll()));

        if (bowlingGame.isGameEnds()) {
            gameScore.setValue(bowlingGame.score());
        }
    }

    public LiveData<PossiblePins> getPossiblePins() {
        return possiblePins;
    }

    public LiveData<Integer> getGameScore() {
        return gameScore;
    }


    static List<String> getDefaultBoardUI() {
        return Arrays.asList(
                "-", "-",
                "-", "-",
                "-", "-",
                "-", "-",
                "-", "-",
                "-", "-",
                "-", "-",
                "-", "-",
                "-", "-",
                "-", "-",
                "b", "b"
        );
    }

    private void incrementIndexIfStrike(int pinsDown) {
        if (pinsDown == 10 && rollIndex < 20) {
            rollIndex++;
        }
    }

    int getRollIndex() {
        return rollIndex;
    }
}
