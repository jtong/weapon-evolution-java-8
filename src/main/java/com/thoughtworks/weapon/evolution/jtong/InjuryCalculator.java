package com.thoughtworks.weapon.evolution.jtong;

import java.util.function.Function;

public enum InjuryCalculator {
    NORMAL_PERSON_MADE_INJURY_CALCULATOR( (factors)->{
        Player source = factors.getSource();
        Player target = factors.getTarget();
        int amount = source.getAp() - target.getDp();
        if (amount < 0) {
            amount = 0;
        }
        return new Injury(amount, null);
    } ),
    SOLIDER_MADE_INJURY_CALCULATOR( (factors)->{
        Player source = factors.getSource();
        Weapon weapon = factors.getWeapon();
        int weaponAp = 0;
        if(weapon!=null) {
            weaponAp+=weapon.getAp();
        }
        Player target = factors.getTarget();
        int amount = source.getAp() + weaponAp - target.getDp();
        if (amount < 0) {
            amount = 0;
        }
        return new Injury(amount, weapon.getName());
    } );

    private Function<InjuryFactors, Injury> handler;

    InjuryCalculator(Function<InjuryFactors, Injury> handler) {
        this.handler = handler;
    }


    public Injury calculate(Player source, Player target, Weapon weapon) {
        return this.handler.apply(new InjuryFactors(source, target,weapon));
    }
}
