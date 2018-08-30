package com.thoughtworks.weapon.evolution.jtong.skills;

import com.thoughtworks.weapon.evolution.jtong.effects.Effect;
import com.thoughtworks.weapon.evolution.jtong.effects.Effects;
import com.thoughtworks.weapon.evolution.jtong.effects.PoisonEffect;

public class Skills {

    public static Skill Deadly_Strike = new Skill(Effects.DeadlyStrikeEffect);

    public static Skill Poison_Strike(int harmValue){
        return new Skill(new PoisonEffect(harmValue));
    }
}
