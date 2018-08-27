package com.thoughtworks.weapon.evolution.jtong;

public class Weapon {
    private final String name;
    private final int ap;

    public Weapon(String name, int ap) {
        this.name = name;
        this.ap = ap;
    }

    public int getAp() {
        return ap;
    }

    public String getName() {
        return name;
    }
}
