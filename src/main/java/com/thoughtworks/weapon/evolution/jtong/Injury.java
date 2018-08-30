package com.thoughtworks.weapon.evolution.jtong;

import com.thoughtworks.weapon.evolution.jtong.effects.Effect;

public class Injury {

    private int amount;
    private String weaponName;
    private Effect effect;

    public Injury(int amount, String weaponName) {

        this.amount = amount;
        this.weaponName = weaponName;
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
