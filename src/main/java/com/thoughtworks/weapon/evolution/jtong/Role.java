package com.thoughtworks.weapon.evolution.jtong;

import static com.thoughtworks.weapon.evolution.jtong.AttackResultCalculators.*;


public enum Role {
    NORMAL_PERSON("普通人", Stream_NORMAL_PERSON_MADE_INJURY_CALCULATOR), SOLIDER("战士", Stream_SOLIDER_MADE_INJURY_CALCULATOR);

    private String displayName;
    private AttackResultCalculator attackResultCalculator;

    Role(String displayName, AttackResultCalculator attackResultCalculator) {
        this.displayName = displayName;

        this.attackResultCalculator = attackResultCalculator;
    }

    public AttackResult calculateInjury(Player source, Player target, Weapon weapon) {
        return this.attackResultCalculator.apply(new AttackFactors(source, target,weapon));
    }

    @Override
    public String toString() {
        return this.displayName.toString();
    }


}
