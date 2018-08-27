package com.thoughtworks.weapon.evolution.jtong;

public class Injury {

    private int amount;
    private String weaponName;

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
}
