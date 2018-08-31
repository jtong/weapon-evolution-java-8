package com.thoughtworks.weapon.evolution.jtong.effects;

import com.thoughtworks.weapon.evolution.jtong.AttackFactors;
import com.thoughtworks.weapon.evolution.jtong.dsl.AttackResultContext;

public interface Effect {
    void apply(AttackFactors attackFactors, AttackResultContext attackResultContext);
}
