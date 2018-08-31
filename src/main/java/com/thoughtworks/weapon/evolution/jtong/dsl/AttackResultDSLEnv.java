package com.thoughtworks.weapon.evolution.jtong.dsl;

import com.thoughtworks.weapon.evolution.jtong.AttackFactors;
import com.thoughtworks.weapon.evolution.jtong.Injury;

public class AttackResultDSLEnv {
    private final AttackFactors attackFactors;
    private final AttackResultContext attackResultContext;


    public AttackResultDSLEnv(AttackFactors attackFactors, AttackResultContext attackResultContext) {
        this.attackFactors = attackFactors;
        this.attackResultContext = attackResultContext;
    }

    public AttackFactors getAttackFactors() {
        return attackFactors;
    }

    public AttackResultContext getAttackResultContext() {
        return attackResultContext;
    }

    public Injury makeInjury() {
        Injury injury = new Injury(this.attackResultContext.getInjuryAmount(), this.attackResultContext.getWeaponName());
        injury.setEffect(this.attackResultContext.getEffect());
        return injury;
    }

    public Injury makeSourceInjury() {
        return this.attackResultContext.getSourceInjury();
    }

}
