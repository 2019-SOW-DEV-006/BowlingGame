package com.kata.bowlinggame.model;

import org.junit.Before;
import org.junit.Test;

import static com.kata.bowlinggame.viewmodel.GameViewModel.ALL_POSSIBLE_BUTTONS;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BowlingGameTest {

    private BowlingGame bowlingGame;

    @Before
    public void setUp() {
        bowlingGame = new BowlingGame();
    }

    @Test
    public void shouldReturnScoreAs0_WhenNoPinsDownInAll20Rolls() {
        pinsDownInEveryRow(0);

        assertEquals(0, bowlingGame.score().intValue());
    }

    @Test
    public void shouldReturnScoreAs20_When1PinDownInAll20Rolls() {
        pinsDownInEveryRow(1);

        assertEquals(20, bowlingGame.score().intValue());
    }

    @Test
    public void shouldReturnScoreAs20_WhenSpareInFirstFrame_And5PinsDownInFirstRollOfSecondFrame_AndNoPinsDownInAllOtherRolls() {
        bowlingGame.pins(4);
        bowlingGame.pins(6);
        bowlingGame.pins(5);
        for (int roll = 3; roll < 20; roll++) {
            bowlingGame.pins(0);
        }

        assertEquals(20, bowlingGame.score().intValue());
    }

    @Test
    public void shouldReturnScoreAs15_WhenSpareInLastFrame_And5PinsDownInBonus_AndNoPinsDownInAllOtherRolls() {
        for (int roll = 0; roll < 18; roll++) {
            bowlingGame.pins(0);
        }
        bowlingGame.pins(4);
        bowlingGame.pins(6);
        bowlingGame.pins(5);

        assertEquals(15, bowlingGame.score().intValue());
    }

    @Test
    public void shouldReturnScoreAs30_WhenStrikeInFirstFrame_And5PinsDownInBothRollsOfSecondFrame_AndNoPinsDownInAllOtherRolls() {
        bowlingGame.pins(10);
        bowlingGame.pins(5);
        bowlingGame.pins(5);
        for (int roll = 3; roll < 20; roll++) {
            bowlingGame.pins(0);
        }

        assertEquals(30, bowlingGame.score().intValue());
    }

    @Test
    public void shouldReturnScoreAs20_WhenStrikeInLastFrame_And5PinsDownInBothBonus_AndNoPinsDownInAllOtherRolls() {
        for (int roll = 0; roll < 18; roll++) {
            bowlingGame.pins(0);
        }
        bowlingGame.pins(10);
        bowlingGame.pins(5);
        bowlingGame.pins(5);

        assertEquals(20, bowlingGame.score().intValue());
    }

    @Test
    public void shouldReturnScoreAs150_When5PinsDownInAllRollsIncludingBonus() {
        for (int roll = 0; roll < 21; roll++) {
            bowlingGame.pins(5);
        }

        assertEquals(150, bowlingGame.score().intValue());
    }

    @Test
    public void shouldReturnScoreAs300_WhenStrikeInAllFramesIncludingBonus() {
        for (int frame = 0; frame < 10; frame++) {
            bowlingGame.pins(10);
        }
        bowlingGame.pins(10);
        bowlingGame.pins(10);

        assertEquals(300, bowlingGame.score().intValue());
    }

    @Test
    public void shouldReturnTrue_WhenGameEnds() {
        for (int roll = 0; roll < 20; roll++) {
            bowlingGame.pins(4);
        }

        assertTrue(bowlingGame.isGameEnds());
    }

    @Test
    public void shouldReturnTrue_WhenGameEndsWithBonus() {
        for (int roll = 0; roll < 21; roll++) {
            bowlingGame.pins(5);
        }

        assertTrue(bowlingGame.isGameEnds());
    }

    @Test
    public void shouldReturnFalse_WhenGameIsInProgress() {
        for (int roll = 0; roll < 19; roll++) {
            bowlingGame.pins(4);
        }

        assertFalse(bowlingGame.isGameEnds());
    }

    @Test
    public void shouldReturnFalse_WhenBonusIsNotProvided() {
        for (int roll = 0; roll < 20; roll++) {
            bowlingGame.pins(5);
        }

        assertFalse(bowlingGame.isGameEnds());
    }

    @Test
    public void shouldReturnFalse_WhenStrikeBonusAreNotGiven() {
        for (int frame = 0; frame < 10; frame++) {
            bowlingGame.pins(10);
        }
        bowlingGame.pins(10);

        assertFalse(bowlingGame.isGameEnds());
    }

    @Test
    public void shouldReturnAllButtons_OnNewFrame() {
        bowlingGame.pins(5);
        bowlingGame.pins(5);

        assertArrayEquals(ALL_POSSIBLE_BUTTONS.toArray(), bowlingGame.possiblePinsForSecondRoll().toArray());
    }

    @Test
    public void shouldReturnAllPossibleButtons_OnSecondRollOfAnyFrame() {
        bowlingGame.pins(5);
        bowlingGame.pins(5);
        bowlingGame.pins(7);

        assertArrayEquals(new String[]{"0","1","2","3"}, bowlingGame.possiblePinsForSecondRoll().toArray());
    }

    @Test
    public void shouldReturnAllButtons_WhenStrike() {
        bowlingGame.pins(10);

        assertArrayEquals(ALL_POSSIBLE_BUTTONS.toArray(), bowlingGame.possiblePinsForSecondRoll().toArray());
    }

    @Test
    public void shouldResetGame_OnNewGame() {
        bowlingGame.newGame();

        int[] rolls = bowlingGame.getRolls();
        for (int index = 0; index < rolls.length; index++) {
            assertEquals(0, rolls[index]);
        }
        assertEquals(0, bowlingGame.getRollIndex());
    }

    private void pinsDownInEveryRow(int pinsDown) {
        for (int roll = 0; roll < 20; roll++) {
            bowlingGame.pins(pinsDown);
        }
    }
}