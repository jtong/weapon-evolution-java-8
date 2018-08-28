package com.thoughtworks.weapon.evolution.jtong;

@FunctionalInterface
public interface InjuryCalculator {
    Injury apply(AttackFactors factors);
}
