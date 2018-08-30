package com.thoughtworks.weapon.evolution.jtong.effects;

import com.thoughtworks.weapon.evolution.jtong.AttackFactors;
import com.thoughtworks.weapon.evolution.jtong.dsl.InjuryResultContext;

public interface Effect {
    void apply(AttackFactors attackFactors, InjuryResultContext injuryResultContext);
}
