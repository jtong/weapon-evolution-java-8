package com.thoughtworks.weapon.evolution.jtong;

import com.thoughtworks.weapon.evolution.jtong.effects.Effect;
import com.thoughtworks.weapon.evolution.jtong.state.StateTypes;

public class Injury {

    private int amount;
    private StateTypes stateType;

    public StateTypes getStateType() {
        return stateType;
    }

    private String weaponName;
    private Effect effect;

    public Injury(int amount, String weaponName) {

        this.amount = amount;
        this.weaponName = weaponName;
    }

    public Injury(int amount, StateTypes type) {

        this.amount = amount;
        this.stateType = type;
    }

    public Injury(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }


    public String getWeaponName() {
        return weaponName;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }
}
