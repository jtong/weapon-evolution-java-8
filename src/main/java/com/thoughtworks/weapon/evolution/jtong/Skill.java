package com.thoughtworks.weapon.evolution.jtong;

import com.thoughtworks.weapon.evolution.jtong.effects.Effect;
import com.thoughtworks.weapon.evolution.jtong.effects.Effects;

public enum Skill {
    Deadly_Strike(Effects.DeadlyStrikeEffect);

    private Effect effect;

    Skill(Effect effect) {

        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }
}
