package com.thoughtworks.weapon.evolution.jtong;

import com.thoughtworks.weapon.evolution.jtong.effects.DeadlyStrikeEffect;
import com.thoughtworks.weapon.evolution.jtong.effects.Effect;
import com.thoughtworks.weapon.evolution.jtong.effects.PoisonEffect;

public class AttackResult {
    private Injury injury;
    private final Player source;
    private final Player target;

    public AttackResult(Injury injury, Player source, Player target) {
        this.injury = injury;
        this.source = source;
        this.target = target;
    }

    public String toStringValue() {

        return this.source.getRole() +  this.source.getName()+(injury.getWeaponName() != null ? ("用"+ injury.getWeaponName()) : "")
                +"攻击了" + this.target.getRole() + this.target.getName()+","
                + calculateEffectString(this.source, this.target, injury.getEffect())
               + this.target.getName() + "受到了" + this.injury.getAmount()+ "点伤害,"
                + calculatePostEffectString(this.source, this.target, injury.getEffect())
                + target.getName() +"剩余生命："+ this.target.getHp();
    }

    private String calculatePostEffectString(Player source, Player target, Effect effect) {
        if(effect instanceof PoisonEffect){
            return target.getName() + "中毒了,";
        }

        return "";
    }

    private String calculateEffectString(Player source, Player target, Effect effect) {
        if(effect instanceof DeadlyStrikeEffect){
            return source.getName() + "发动了全力一击,";
        }
        return "";
    }
}
