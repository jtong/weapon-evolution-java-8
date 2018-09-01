package com.thoughtworks.weapon.evolution.jtong.state;

public class PoisonState extends State{

    public PoisonState(int harmValue) {
        this.setHarmValue( harmValue );
        this.setType(StateTypes.Poison);
    }
}
