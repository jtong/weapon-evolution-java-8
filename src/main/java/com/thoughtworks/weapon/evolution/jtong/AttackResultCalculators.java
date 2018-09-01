package com.thoughtworks.weapon.evolution.jtong;


import com.thoughtworks.weapon.evolution.jtong.dsl.AttackResultBuildDSL;
import com.thoughtworks.weapon.evolution.jtong.dsl.AttackResultDSLEnv;
import com.thoughtworks.weapon.evolution.jtong.dsl.AttackResultContext;
import com.thoughtworks.weapon.evolution.jtong.effects.Effect;

import java.util.function.Function;

public class AttackResultCalculators {


    public static AttackResultCalculator Stream_NORMAL_PERSON_MADE_INJURY_CALCULATOR = (factors) -> new AttackResultBuildDSL(factors)
            .startWith(calculate_State_Affection())
            .then(get_source_origional_ap())
            .then(get_target_original_dp())
            .then(calculate_injury_amount())
            .calculate();

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> calculate_State_Affection() {
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

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> calculate_injury_amount() {
        return (AttackResultDSLEnv env) -> {
            AttackResultContext resultContext = env.getAttackResultContext();
            int amount = getInjuryAmount(resultContext.getAp(), resultContext.getDp());
            env.getAttackResultContext().setInjuryAmount(amount);
            return env;
        };
    }

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> get_target_original_dp() {
        return (AttackResultDSLEnv env) -> {
            int dp = getOriginalDp(env.getAttackFactors());
            env.getAttackResultContext().setDp(dp);
            return env;
        };
    }

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> get_source_origional_ap() {
        return (AttackResultDSLEnv env) -> {
            int ap = getOriginalAp(env.getAttackFactors());
            env.getAttackResultContext().setAp(ap);
            return env;
        };
    }


    public static AttackResultCalculator Stream_SOLIDER_MADE_INJURY_CALCULATOR = (factors) -> new AttackResultBuildDSL(factors)
            .startWith(calculate_State_Affection())
            .then(get_source_origional_ap())
            .then(append_source_weapon_ap())
            .then(get_target_original_dp())
            .then(get_weapon_skill_effect())
            .then(calculate_injury_amount())
            .then(apply_effect())
            .then(get_source_weapon_name())
            .calculate();

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> apply_effect() {
        return (AttackResultDSLEnv env) -> {
            Effect effect = env.getAttackResultContext().getEffect();
            if (effect == null) {
                return env;
            }
            effect.apply(env.getAttackFactors(), env.getAttackResultContext());
            return env;
        };
    }

    public static Function<AttackResultDSLEnv, AttackResultDSLEnv> get_weapon_skill_effect() {
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

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> get_source_weapon_name() {
        return (AttackResultDSLEnv env) -> {
            String weaponName = env.getAttackFactors().getSource().getWeapon().getName();
            env.getAttackResultContext().setWeaponName(weaponName);
            return env;
        };
    }

    private static Function<AttackResultDSLEnv, AttackResultDSLEnv> append_source_weapon_ap() {
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
