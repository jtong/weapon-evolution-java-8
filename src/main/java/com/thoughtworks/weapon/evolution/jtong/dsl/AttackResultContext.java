package com.thoughtworks.weapon.evolution.jtong.dsl;

import com.thoughtworks.weapon.evolution.jtong.Injury;
import com.thoughtworks.weapon.evolution.jtong.effects.Effect;

public class AttackResultContext {
    private int ap;
    private int dp;
    private int injuryAmount;
    private String weaponName;
    private Effect effect;
    private Injury sourceInjury;

    public Injury getSourceInjury() {
        return sourceInjury;
    }

    public AttackResultContext() {
    }

    public AttackResultContext setAp(int ap) {
        this.ap = ap;
        return this;
    }

    public int getAp() {
        return ap;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public int getDp() {
        return dp;
    }

    public void setInjuryAmount(int injuryAmount) {
        this.injuryAmount = injuryAmount;
    }

    public int getInjuryAmount() {
        return injuryAmount;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setSourceInjury(Injury sourceInjury) {

        this.sourceInjury = sourceInjury;
    }

}
