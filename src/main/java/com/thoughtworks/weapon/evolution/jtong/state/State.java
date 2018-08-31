package com.thoughtworks.weapon.evolution.jtong.state;

public class State {
    private StateTypes type;
    private int harmValue;

    protected void setType(StateTypes type){
        this.type = type;
    }
    public StateTypes getType() {
        return type;
    }

    protected void setHarmValue(int harmValue) {
        this.harmValue = harmValue;
    }

    public int getHarmValue() {
        return harmValue;
    }
}
