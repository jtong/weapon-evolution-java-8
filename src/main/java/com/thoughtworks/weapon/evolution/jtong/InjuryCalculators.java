package com.thoughtworks.weapon.evolution.jtong;


public class InjuryCalculators {
    public static InjuryCalculator NORMAL_PERSON_MADE_INJURY_CALCULATOR =  (factors)->{
        Player source = factors.getSource();
        Player target = factors.getTarget();
        int amount = source.getAp() - target.getDp();
        if (amount < 0) {
            amount = 0;
        }
        return new Injury(amount, null);
    };

    public static InjuryCalculator SOLIDER_MADE_INJURY_CALCULATOR = (factors)->{
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
    };



}
