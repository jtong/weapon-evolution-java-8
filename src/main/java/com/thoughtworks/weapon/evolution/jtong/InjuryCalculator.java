package com.thoughtworks.weapon.evolution.jtong;

@FunctionalInterface
public interface InjuryCalculator {
    AttackResult apply(AttackFactors factors);
}
