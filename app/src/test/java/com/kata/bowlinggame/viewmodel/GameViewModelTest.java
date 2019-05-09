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
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
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
        assertEquals("" + pinsDown, scoreBoard.board().get(0));
        assertEquals(1, scoreBoard.getPosition());
    }

    @Test
    public void shouldShowAllButtonsOnNewGame() {
        bowlingGame = Mockito.mock(BowlingGame.class);
        viewModel = new GameViewModel(bowlingGame); //internally call newGame method

        assertNotNull(viewModel.getPossiblePins().getValue());
        List<String> possiblePins = viewModel.getPossiblePins().getValue().getPossiblePins();
        assertArrayEquals(GameViewModel.ALL_POSSIBLE_BUTTONS.toArray(), possiblePins.toArray());
        verify(bowlingGame).newGame();
    }

    @Test
    public void shouldShowPossibleButtonsForSecondRollOfSameFrameFromModel() {
        when(bowlingGame.possiblePinsForSecondRoll()).thenReturn(Arrays.asList("0", "1", "2", "3"));

        viewModel.roll(7);

        assertNotNull(viewModel.getPossiblePins().getValue());
        List<String> possiblePins = viewModel.getPossiblePins().getValue().getPossiblePins();
        assertArrayEquals(new String[]{"0", "1", "2", "3"}, possiblePins.toArray());
    }

    @Test
    public void shouldDisplayScore_WhenGameEnds() {
        int score = 300;
        when(bowlingGame.score()).thenReturn(score);
        when(bowlingGame.isGameEnds()).thenReturn(false).thenReturn(true);

        viewModel.roll(2);

        assertNotNull(viewModel.getGameScore().getValue());
        assertEquals(score, viewModel.getGameScore().getValue().intValue());
    }

    @Test
    public void shouldNotUpdateScore_WhenGameIsInProgress() {
        int score = 150;
        when(bowlingGame.score()).thenReturn(score);
        when(bowlingGame.isGameEnds()).thenReturn(false);

        viewModel.roll(2);

        assertNull(viewModel.getGameScore().getValue());
    }

    @Test
    public void shouldResetValues_WhenNewGame() {
        viewModel.roll(8);

        viewModel.newGame();

        assertEquals(0, viewModel.rollIndex);
        assertArrayEquals(GameViewModel.getDefaultBoardUI().toArray(), viewModel.getScoreBoard().getValue().board().toArray());
        assertArrayEquals(GameViewModel.ALL_POSSIBLE_BUTTONS.toArray(), viewModel.getPossiblePins().getValue().getPossiblePins().toArray());
    }

    @Test
    public void shouldIncrementIndexOnce_IfNotStrike() {
        int rollIndex = viewModel.getRollIndex();

        viewModel.roll(7);

        assertEquals(rollIndex + 1, viewModel.getRollIndex());
    }

    @Test
    public void shouldIncrementIndexTwice_IfStrike() {
        int rollIndex = viewModel.getRollIndex();

        viewModel.roll(10);

        assertEquals(rollIndex + 2, viewModel.getRollIndex());
    }

    @Test
    public void shouldNotAllowToInsertPins_IfGameEnds() {
        when(bowlingGame.isGameEnds()).thenReturn(true);
        int rollIndex = viewModel.getRollIndex();

        viewModel.roll(10);

        assertEquals(rollIndex, viewModel.getRollIndex());
    }

    private void assertValidScoreBoard() {
        assertNotNull(viewModel.getScoreBoard());
        assertNotNull(getScoreBoard());
    }

    private ScoreBoard getScoreBoard() {
        return viewModel.getScoreBoard().getValue();
    }
}