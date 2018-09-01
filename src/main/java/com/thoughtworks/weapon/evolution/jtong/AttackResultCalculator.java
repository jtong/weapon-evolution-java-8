package com.thoughtworks.weapon.evolution.jtong;

@FunctionalInterface
public interface AttackResultCalculator {
    AttackResult apply(AttackFactors factors);
}
