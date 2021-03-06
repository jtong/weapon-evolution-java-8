package com.thoughtworks.weapon.evolution.jtong;

import com.thoughtworks.weapon.evolution.jtong.state.PoisonState;
import com.thoughtworks.weapon.evolution.jtong.state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.thoughtworks.weapon.evolution.jtong.NoArmor.NO_ARMOR;

public class Player {
    private final String name;
    private Armor armor = NO_ARMOR;
    private int hp;
    private int ap;
    private Role role;
    private Weapon weapon;
    private int dp;
    private List<State> states = new ArrayList<>();

    public Player(String name, int hp, int ap) {
        this.name = name;
        this.hp = hp;
        this.ap = ap;
        this.role = Role.NORMAL_PERSON;
    }
    public Player(String name, int hp, int ap, Role role, Weapon weapon) {
        this.name = name;
        this.hp = hp;
        this.ap = ap;
        this.role = role;
        this.weapon = weapon;
    }

    public Player(String name, int hp, int ap, Role role, Weapon weapon, Armor armor) {
        this(name, hp, ap, role, weapon);
        this.armor = armor;
    }

    public AttackResult attack(Player target) {
        AttackResult attackResult = this.role.calculateInjury(this, target, this.weapon);
        Injury injury = attackResult.getInjury();
        target.applyInjury(injury);
        return attackResult;
    }

    void applyInjury(Injury injury) {
        this.hp -= injury.getAmount();
    }


    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAp() {
        return ap;
    }

    public int getDp() {
        return dp+this.armor.getDp();
    }

    public Armor getArmor() {
        return armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public List<State> getStates() {
        return states;
    }

    public void addState(State state) {
        if(states.stream().filter(oldState -> oldState.getType().equals(state.getType())).findFirst().isPresent()){
            states = states.stream().map(oldState ->{
                if(oldState.getType().equals(state.getType())){
                    return oldState.getType().buildState(oldState.getHarmValue()+state.getHarmValue());
                }
                return oldState;
            }).collect(Collectors.toList());

        } else {
            states.add(state);

        }
    }
}
