package com.thoughtworks.weapon.evolution.jtong.state;

import java.util.function.Function;

public enum StateTypes {
    Poison(PoisonState::new);

    private Function<Integer, State> stateConstructor;

    StateTypes(Function<Integer, State> stateConstructor) {

        this.stateConstructor = stateConstructor;
    }

    public State buildState(int harmValue) {
        return stateConstructor.apply(harmValue);
    }
}
