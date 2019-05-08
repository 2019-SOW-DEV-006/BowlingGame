package com.kata.bowlinggame.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    private void pinsDownInEveryRow(int pinsDown) {
        for (int roll = 0; roll < 20; roll++) {
            bowlingGame.pins(pinsDown);
        }
    }
}