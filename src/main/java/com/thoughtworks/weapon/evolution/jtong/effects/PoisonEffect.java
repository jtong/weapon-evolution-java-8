package com.thoughtworks.weapon.evolution.jtong.effects;

import com.thoughtworks.weapon.evolution.jtong.AttackFactors;
import com.thoughtworks.weapon.evolution.jtong.Player;
import com.thoughtworks.weapon.evolution.jtong.dsl.AttackResultContext;
import com.thoughtworks.weapon.evolution.jtong.state.PoisonState;

public class PoisonEffect implements Effect{
    private int harmValue;

    public PoisonEffect(int harmValue) {
        this.harmValue = harmValue;
    }

    @Override
    public void apply(AttackFactors attackFactors, AttackResultContext attackResultContext) {
        Player target = attackFactors.getTarget();
        target.addState(new PoisonState(this.harmValue));
    }
}
