package com.thoughtworks.weapon.evolution.jtong;


public class InjuryCalculators {
    public static InjuryCalculator NORMAL_PERSON_MADE_INJURY_CALCULATOR =  (factors)->{
        int ap = getOriginalAp(factors);
        int dp = getOriginalDp(factors);
        int amount = getInjuryAmount(ap, dp);

        return new Injury(amount, null);
    };
    
    public static InjuryCalculator SOLIDER_MADE_INJURY_CALCULATOR = (attackFactors)->{
        int dp = getOriginalDp(attackFactors);
        int originalAp = getOriginalAp(attackFactors);
        int ap = appendWeaponAp(originalAp, attackFactors);

        int amount = getInjuryAmount(ap, dp);
        return new Injury(amount, attackFactors.getWeapon().getName());
    };



    private static int getInjuryAmount(int ap, int dp) {
        int amount = ap - dp;
        if (amount < 0) {
            amount = 0;
        }
        return amount;
    }

    private static int getOriginalDp(AttackFactors factors) {
        Player target = factors.getTarget();
        return target.getDp();
    }

    private static int getOriginalAp(AttackFactors factors) {
        Player source = factors.getSource();
        return source.getAp();
    }

    private static int appendWeaponAp(int originalAp, AttackFactors factors) {
        int weaponAp = getWeaponAp(factors);
        return originalAp + weaponAp;
    }

    private static int getWeaponAp(AttackFactors factors) {
        int weaponAp = 0;
        if(factors.getWeapon() !=null) {
            weaponAp+= factors.getWeapon().getAp();
        }
        return weaponAp;
    }


}
