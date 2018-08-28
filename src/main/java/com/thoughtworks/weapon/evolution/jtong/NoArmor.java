package com.thoughtworks.weapon.evolution.jtong;

public class NoArmor extends Armor {
    public NoArmor() {
        super(null, 0);
    }

    public final static NoArmor NO_ARMOR = new NoArmor();
}
