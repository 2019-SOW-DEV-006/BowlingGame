package com.kata.bowlinggame.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.kata.bowlinggame.domain.ScoreBoard;
import com.kata.bowlinggame.model.BowlingGame;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mockito;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    private GameViewModel viewModel;
    private BowlingGame bowlingGame;

    @Before
    public void setUp() {
        bowlingGame = Mockito.mock(BowlingGame.class);
        viewModel = new GameViewModel(bowlingGame);
    }

    @Test
    public void shouldUpdateScoreBoardWithEmptyValues_WhenNewGameIsCalled() {
        viewModel.newGame();

        assertNotNull(viewModel.getScoreBoard());
        ScoreBoard scoreBoard = viewModel.getScoreBoard().getValue();
        assertNotNull(scoreBoard);
        assertArrayEquals(GameViewModel.NEW_SCORE_BOARD.toArray(), scoreBoard.getScoreBoard().toArray());
    }

    @Test
    public void shouldSendPinsDownToBowlingModel_AndUpdateScoreBoard() {
        int pinsDown = 7;
        viewModel.roll(pinsDown);

        Mockito.verify(bowlingGame).pins(pinsDown);
        assertNotNull(viewModel.getScoreBoard());
        ScoreBoard scoreBoard = viewModel.getScoreBoard().getValue();
        assertNotNull(scoreBoard);
        assertEquals(""+pinsDown, scoreBoard.getScoreBoard().get(0));
    }
}