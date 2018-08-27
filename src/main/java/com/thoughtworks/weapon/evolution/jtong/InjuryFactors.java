package com.thoughtworks.weapon.evolution.jtong;


public class InjuryFactors implements ValueObject{
    private Player source;
    private Player target;
    private Weapon weapon;

    public Player getSource() {
        return source;
    }

    public Player getTarget() {
        return target;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public InjuryFactors(Player source, Player target, Weapon weapon) {
        this.source = source;
        this.target = target;
        this.weapon = weapon;
    }
}
