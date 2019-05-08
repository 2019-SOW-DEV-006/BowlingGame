package com.kata.bowlinggame.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.kata.bowlinggame.domain.ScoreBoard;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

public class GameViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Test
    public void shouldUpdateScoreBoardWithEmptyValues_WhenNewGameIsCalled() {
        GameViewModel viewModel = new GameViewModel();

        viewModel.newGame();

        assertNotNull(viewModel.getScoreBoard());
        ScoreBoard scoreBoard = viewModel.getScoreBoard().getValue();
        assertNotNull(scoreBoard);
        assertArrayEquals(GameViewModel.NEW_SCORE_BOARD.toArray(), scoreBoard.getScoreBoard().toArray());
    }
}