package com.thoughtworks.weapon.evolution.jtong.effects;

import com.thoughtworks.weapon.evolution.jtong.AttackFactors;
import com.thoughtworks.weapon.evolution.jtong.dsl.InjuryResultContext;

public class DeadlyStrikeEffect implements Effect{
    @Override
    public void apply(AttackFactors attackFactors, InjuryResultContext injuryResultContext) {
        injuryResultContext.setInjuryAmount(injuryResultContext.getInjuryAmount()*3);
    }
}
