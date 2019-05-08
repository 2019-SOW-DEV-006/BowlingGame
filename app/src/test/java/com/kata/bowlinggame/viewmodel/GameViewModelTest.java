package com.kata.bowlinggame.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.kata.bowlinggame.domain.ScoreBoard;
import com.kata.bowlinggame.model.BowlingGame;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class GameViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private BowlingGame bowlingGame;
    private GameViewModel viewModel;

    @Before
    public void setUp() {
        bowlingGame = Mockito.mock(BowlingGame.class);
        viewModel = new GameViewModel(bowlingGame);
    }

    @Test
    public void shouldUpdateScoreBoardWithEmptyValues_WhenNewGameIsCalled() {
        viewModel.newGame();

        assertValidScoreBoard();
        ScoreBoard scoreBoard = getScoreBoard();
        assertArrayEquals(GameViewModel.NEW_SCORE_BOARD.toArray(), scoreBoard.board().toArray());
    }

    @Test
    public void shouldSendPinsDownToBowlingModel_AndUpdateScoreBoard() {
        int pinsDown = 7;
        viewModel.roll(pinsDown);

        Mockito.verify(bowlingGame).pins(pinsDown);
        assertValidScoreBoard();
        ScoreBoard scoreBoard = getScoreBoard();
        assertEquals(""+pinsDown, scoreBoard.board().get(0));
    }

    @Test
    public void shouldShowAllButtonsOnNewGame() {
        viewModel.newGame();

        List<String> possiblePins = viewModel.getPossiblePins().getValue().getPossiblePins();
        assertArrayEquals(GameViewModel.ALL_POSSIBLE_BUTTONS.toArray(), possiblePins.toArray());
    }

    @Test
    public void shouldShowPossibleButtonsFromModel() {
        when(bowlingGame.possiblePinsForSecondRoll()).thenReturn(Arrays.asList("0","1","2","3"));

        viewModel.roll(7);

        List<String> possiblePins = viewModel.getPossiblePins().getValue().getPossiblePins();
        assertArrayEquals(new String[]{"0","1","2","3"}, possiblePins.toArray());
    }

    private void assertValidScoreBoard() {
        assertNotNull(viewModel.getScoreBoard());
        assertNotNull(getScoreBoard());
    }

    private ScoreBoard getScoreBoard() {
        return viewModel.getScoreBoard().getValue();
    }
}