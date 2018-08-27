package com.thoughtworks.weapon.evolution.jtong;

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

               + this.target.getName() + "受到了" + this.injury.getAmount()+ "点伤害,"+ target.getName() +"剩余生命："+ this.target.getHp();
    }
}
