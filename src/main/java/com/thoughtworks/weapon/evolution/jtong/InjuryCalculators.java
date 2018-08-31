package com.thoughtworks.weapon.evolution.jtong;


import com.thoughtworks.weapon.evolution.jtong.dsl.AttackResultBuildDSL;
import com.thoughtworks.weapon.evolution.jtong.dsl.AttackResultDSLEnv;
import com.thoughtworks.weapon.evolution.jtong.dsl.AttackResultContext;
import com.thoughtworks.weapon.evolution.jtong.effects.Effect;

import java.util.function.Function;

public class InjuryCalculators {


    public static InjuryCalculator Stream_NORMAL_PERSON_MADE_INJURY_CALCULATOR = (factors) -> new AttackResultBuildDSL(factors)
            .startWith(calculateStateAffection())
            .andThen(getSourceOrigionalAp())
            .andThen(getTargetOriginalDp())
            .andThen(calculateInjuryAmount())
            .calculate();

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> calculateStateAffection() {
        return (AttackResultDSLEnv env) -> {
            Player source = env.getAttackFactors().getSource();
            source.getStates().stream().forEach(state -> {
                Injury sourceInjury = new Injury(state.getHarmValue(), state.getType());
                source.applyInjury(sourceInjury);
                if (source.getHp() <= 0) {
                    env.setBreak(true);
                }
                env.getAttackResultContext().setSourceInjury(sourceInjury);
            });

            return env;
        };
    }

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> calculateInjuryAmount() {
        return (AttackResultDSLEnv env) -> {
            AttackResultContext resultContext = env.getAttackResultContext();
            int amount = getInjuryAmount(resultContext.getAp(), resultContext.getDp());
            env.getAttackResultContext().setInjuryAmount(amount);
            return env;
        };
    }

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> getTargetOriginalDp() {
        return (AttackResultDSLEnv env) -> {
            int dp = getOriginalDp(env.getAttackFactors());
            env.getAttackResultContext().setDp(dp);
            return env;
        };
    }

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> getSourceOrigionalAp() {
        return (AttackResultDSLEnv env) -> {
            int ap = getOriginalAp(env.getAttackFactors());
            env.getAttackResultContext().setAp(ap);
            return env;
        };
    }


    public static InjuryCalculator Stream_SOLIDER_MADE_INJURY_CALCULATOR = (factors) -> {
        return new AttackResultBuildDSL(factors)
                .startWith(getSourceOrigionalAp())
                .andThen(appendSourceWeaponAp())
                .andThen(getTargetOriginalDp())
                .andThen(getWeaponSkillEffect())
                .andThen(calculateInjuryAmount())
                .andThen(applyEffect())
                .andThen(getSourceWeaponName())
                .calculate();
    };

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> applyEffect() {
        return (AttackResultDSLEnv env) -> {
            Effect effect = env.getAttackResultContext().getEffect();
            if (effect == null) {
                return env;
            }
            effect.apply(env.getAttackFactors(), env.getAttackResultContext());
            return env;
        };
    }

    public static Function<AttackResultDSLEnv, AttackResultDSLEnv> getWeaponSkillEffect() {
        return (AttackResultDSLEnv env) -> {
            Weapon weapon = env.getAttackFactors().getSource().getWeapon();
            if (weapon.getSkill() == null) {
                return env;
            }
            //假装计算一下概率
            //好，计算完了，概率命中
            Effect triggeredEffect = weapon.getSkill().getEffect();
            env.getAttackResultContext().setEffect(triggeredEffect);
            return env;
        };
    }

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> getSourceWeaponName() {
        return (AttackResultDSLEnv env) -> {
            String weaponName = env.getAttackFactors().getSource().getWeapon().getName();
            env.getAttackResultContext().setWeaponName(weaponName);
            return env;
        };
    }

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> appendSourceWeaponAp() {
        return (AttackResultDSLEnv env) -> {
            int originalAp = env.getAttackResultContext().getAp();
            int weaponAp = env.getAttackFactors().getSource().getWeapon().getAp();
            env.getAttackResultContext().setAp(originalAp + weaponAp);
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
        if (factors.getWeapon() != null) {
            weaponAp += factors.getWeapon().getAp();
        }
        return weaponAp;
    }


}
