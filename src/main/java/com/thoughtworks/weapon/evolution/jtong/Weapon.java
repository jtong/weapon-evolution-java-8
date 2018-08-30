package com.thoughtworks.weapon.evolution.jtong;

public class Weapon {
    private String name;
    private int ap;
    private Skill skill;

    public Weapon(String name, int ap) {
        this.name = name;
        this.ap = ap;
    }

    public Weapon(String name, int ap, Skill skill) {
        this(name, ap);
        this.skill = skill;
    }

    public int getAp() {
        return ap;
    }

    public String getName() {
        return name;
    }

    public Skill getSkill() {
        return skill;
    }
}
