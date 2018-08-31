package com.thoughtworks.weapon.evolution.jtong.effects;

import com.thoughtworks.weapon.evolution.jtong.AttackFactors;
import com.thoughtworks.weapon.evolution.jtong.dsl.AttackResultContext;

public class DeadlyStrikeEffect implements Effect{
    @Override
    public void apply(AttackFactors attackFactors, AttackResultContext attackResultContext) {
        attackResultContext.setInjuryAmount(attackResultContext.getInjuryAmount()*3);
    }
}
