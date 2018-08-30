package com.thoughtworks.weapon.evolution.jtong.state;

public class State {
    private StateTypes type;

    protected void setType(StateTypes type){
        this.type = type;
    }
    public StateTypes getType() {
        return type;
    }
}
