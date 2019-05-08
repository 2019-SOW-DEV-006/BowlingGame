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

    private void pinsDownInEveryRow(int pinsDown) {
        for (int roll = 0; roll < 20; roll++) {
            bowlingGame.pins(pinsDown);
        }
    }
}