package com.kata.bowlinggame.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingGameTest {

    @Test
    public void shouldReturnScoreAs0_WhenNoPinsDownInAll20Rolls() {
        BowlingGame bowlingGame = new BowlingGame();

        for (int roll = 0; roll < 20; roll++) {
            bowlingGame.pins(0);
        }

        assertEquals(0, bowlingGame.score().intValue());
    }

    @Test
    public void shouldReturnScoreAs20_When1PinDownInAll20Rolls() {
        BowlingGame bowlingGame = new BowlingGame();

        for (int roll = 0; roll < 20; roll++) {
            bowlingGame.pins(1);
        }

        assertEquals(20, bowlingGame.score().intValue());
    }
}