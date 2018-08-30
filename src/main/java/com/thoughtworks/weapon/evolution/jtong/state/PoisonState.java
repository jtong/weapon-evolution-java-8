package com.thoughtworks.weapon.evolution.jtong.state;

public class PoisonState extends State{
    private int harmValue;

    public PoisonState(int harmValue) {
        this.harmValue = harmValue;
        this.setType(StateTypes.Poison);
    }
}
