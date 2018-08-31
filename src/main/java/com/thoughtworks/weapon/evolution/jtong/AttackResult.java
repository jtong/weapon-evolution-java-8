package com.thoughtworks.weapon.evolution.jtong;

import com.thoughtworks.weapon.evolution.jtong.effects.DeadlyStrikeEffect;
import com.thoughtworks.weapon.evolution.jtong.effects.Effect;
import com.thoughtworks.weapon.evolution.jtong.effects.PoisonEffect;
import com.thoughtworks.weapon.evolution.jtong.state.StateTypes;

public class AttackResult {
    private Injury sourceInjury;
    private Injury injury;

    public boolean isBreak() {
        return isBreak;
    }

    private boolean isBreak;

    public Injury getInjury() {
        return injury;
    }

    private final Player source;
    private final Player target;

    public AttackResult(Player source, Injury sourceInjury, Player target, Injury targetInjury, boolean isBreak) {
        this.source = source;
        this.sourceInjury = sourceInjury;
        this.target = target;
        this.injury = targetInjury;
        this.isBreak = isBreak;
    }

    public String toStringValue() {

        String stateString = calculateStateString();

        return stateString + (isBreak ? "" : ("".equals(stateString)?"":"\n") + buildAttackString());
    }

    private String buildAttackString() {
        return this.source.getRole() + this.source.getName() + (injury.getWeaponName() != null ? ("用" + injury.getWeaponName()) : "")
                + "攻击了" + this.target.getRole() + this.target.getName() + ","
                + calculateEffectString(this.source, this.target, injury.getEffect())
                + this.target.getName() + "受到了" + this.injury.getAmount() + "点伤害,"
                + calculatePostEffectString(this.source, this.target, injury.getEffect())
                + target.getName() + "剩余生命：" + this.target.getHp();
    }

    private String calculateStateString() {
        if (this.sourceInjury != null) {
            return this.source.getName()+"受到"+this.sourceInjury.getAmount()+"点"+translateStateType(this.sourceInjury.getStateType())+"伤害, "
                    + this.source.getName() + "剩余生命："+ this.source.getHp() ;
        }
        return "";
    }

    private String translateStateType(StateTypes stateType) {
        if(stateType == StateTypes.Poison){
            return "毒性";
        }
        return "";
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
