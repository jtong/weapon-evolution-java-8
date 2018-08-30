package com.thoughtworks.weapon.evolution.jtong.skills;

import com.thoughtworks.weapon.evolution.jtong.effects.Effect;

public class Skill {
    private Effect effect;

    Skill(Effect effect) {

        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }
}
