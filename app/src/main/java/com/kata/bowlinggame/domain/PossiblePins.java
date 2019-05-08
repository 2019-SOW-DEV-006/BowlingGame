package com.kata.bowlinggame.domain;

import java.util.List;

public class PossiblePins {
    private List<String> possiblePins;

    public PossiblePins(List<String> possiblePins) {
        this.possiblePins = possiblePins;
    }

    public List<String> getPossiblePins() {
        return possiblePins;
    }
}
