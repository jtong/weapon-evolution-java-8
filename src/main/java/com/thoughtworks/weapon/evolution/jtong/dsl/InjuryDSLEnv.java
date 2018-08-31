package com.thoughtworks.weapon.evolution.jtong.dsl;

import com.thoughtworks.weapon.evolution.jtong.AttackFactors;
import com.thoughtworks.weapon.evolution.jtong.Injury;

import java.util.List;

public class InjuryDSLEnv {
    private final AttackFactors attackFactors;
    private final InjuryResultContext injuryResultContext;


    public InjuryDSLEnv(AttackFactors attackFactors, InjuryResultContext injuryResultContext) {
        this.attackFactors = attackFactors;
        this.injuryResultContext = injuryResultContext;
    }

    public AttackFactors getAttackFactors() {
        return attackFactors;
    }

    public InjuryResultContext getInjuryResultContext() {
        return injuryResultContext;
    }

    public Injury makeInjury() {
        Injury injury = new Injury(this.injuryResultContext.getInjuryAmount(), this.injuryResultContext.getWeaponName());
        injury.setEffect(this.injuryResultContext.getEffect());
        return injury;
    }

    public Injury makeSourceInjury() {
        return this.injuryResultContext.getSourceInjury();
    }

}
