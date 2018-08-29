package com.thoughtworks.weapon.evolution.jtong.dsl;

public class InjuryResultContext {
    private int ap;
    private int dp;
    private int injuryAmount;
    private String weaponName;

    public InjuryResultContext() {
    }

    public InjuryResultContext setAp(int ap) {
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
}
