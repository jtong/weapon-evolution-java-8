package com.thoughtworks.weapon.evolution.s2;

import java.util.List;

public interface EventMaker {
    AttackEvent make(List<Factor> factors);
}
