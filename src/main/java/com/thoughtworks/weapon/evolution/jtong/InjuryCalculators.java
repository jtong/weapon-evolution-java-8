package com.thoughtworks.weapon.evolution.jtong;


import com.thoughtworks.weapon.evolution.jtong.dsl.InjuryBuildDSL;
import com.thoughtworks.weapon.evolution.jtong.dsl.InjuryDSLEnv;
import com.thoughtworks.weapon.evolution.jtong.dsl.InjuryResultContext;
import com.thoughtworks.weapon.evolution.jtong.effects.Effect;

import java.util.function.Function;

public class InjuryCalculators {
    public static InjuryCalculator NORMAL_PERSON_MADE_INJURY_CALCULATOR =  (factors)->{
        int ap = getOriginalAp(factors);
        int dp = getOriginalDp(factors);
        int amount = getInjuryAmount(ap, dp);

        return new Injury(amount, null);
    };


    public static InjuryCalculator Stream_NORMAL_PERSON_MADE_INJURY_CALCULATOR =  (factors)-> new InjuryBuildDSL(factors)
            .with(getSourceOrigionalAp()
                    .andThen(getTargetOriginalDp())
                    .andThen(calculateInjuryAmount()))
            .calculate();

    private static Function<InjuryDSLEnv, InjuryDSLEnv> calculateInjuryAmount() {
        return (InjuryDSLEnv env) -> {
            InjuryResultContext resultContext = env.getInjuryResultContext();
            int amount = getInjuryAmount(resultContext.getAp(), resultContext.getDp());
            env.getInjuryResultContext().setInjuryAmount(amount);
            return env;
        };
    }

    private static Function<InjuryDSLEnv, InjuryDSLEnv> getTargetOriginalDp() {
        return (InjuryDSLEnv env) -> {
            int dp = getOriginalDp(env.getAttackFactors());
            env.getInjuryResultContext().setDp(dp);
            return env;
        };
    }

    private static Function<InjuryDSLEnv, InjuryDSLEnv> getSourceOrigionalAp() {
        return (InjuryDSLEnv env) -> {
            int ap = getOriginalAp(env.getAttackFactors());
            env.getInjuryResultContext().setAp(ap);
            return env;
        };
    }

    public static InjuryCalculator SOLIDER_MADE_INJURY_CALCULATOR = (attackFactors)->{
        int dp = getOriginalDp(attackFactors);
        int originalAp = getOriginalAp(attackFactors);
        int ap = appendWeaponAp(originalAp, attackFactors);

        int amount = getInjuryAmount(ap, dp);
        return new Injury(amount, attackFactors.getWeapon().getName());
    };

    public static InjuryCalculator Stream_SOLIDER_MADE_INJURY_CALCULATOR =  (factors)-> {
        return new InjuryBuildDSL(factors)
                        .with(getSourceOrigionalAp().andThen(appendSourceWeaponAp())
                                .andThen(getTargetOriginalDp())
                                .andThen(getWeaponSkillEffect())
                                .andThen(calculateInjuryAmount())
                                .andThen(applyEffect())
                                .andThen(getSourceWeaponName()))
                        .calculate();
    };

    private static Function<InjuryDSLEnv, InjuryDSLEnv> applyEffect() {
        return (InjuryDSLEnv env) -> {
            Effect effect = env.getInjuryResultContext().getEffect();
            effect.apply(env.getAttackFactors(), env.getInjuryResultContext());
            return env;
        };
    }

    public static Function<InjuryDSLEnv, InjuryDSLEnv> getWeaponSkillEffect() {
        return (InjuryDSLEnv env) -> {
            Weapon weapon = env.getAttackFactors().getSource().getWeapon();
            //假装计算一下概率
            //好，计算完了，概率命中
            Effect triggeredEffect = weapon.getSkill().getEffect();
            env.getInjuryResultContext().setEffect(triggeredEffect);
            return env;
        };
    }
    private static Function<InjuryDSLEnv, InjuryDSLEnv> getSourceWeaponName() {
        return (InjuryDSLEnv env) -> {
            String weaponName = env.getAttackFactors().getSource().getWeapon().getName();
            env.getInjuryResultContext().setWeaponName(weaponName);
            return env;
        };
    }

    private static Function<InjuryDSLEnv, InjuryDSLEnv> appendSourceWeaponAp() {
        return (InjuryDSLEnv env) -> {
            int originalAp = env.getInjuryResultContext().getAp();
            int weaponAp = env.getAttackFactors().getSource().getWeapon().getAp();
            env.getInjuryResultContext().setAp(originalAp+weaponAp);
            return env;
        };
    }



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
