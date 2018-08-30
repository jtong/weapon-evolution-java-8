package com.thoughtworks.weapon.evolution.jtong.dsl;

import com.thoughtworks.weapon.evolution.jtong.AttackFactors;
import com.thoughtworks.weapon.evolution.jtong.Injury;

public class InjuryDSLEnv {
    private final AttackFactors attackFactors;
    private final InjuryResultContext injuryResultContext;

    public void setInjury(Injury injury) {
        this.injury = injury;
    }

    private Injury injury;

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
}
