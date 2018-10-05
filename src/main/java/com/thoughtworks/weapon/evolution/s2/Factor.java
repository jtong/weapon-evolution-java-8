package com.thoughtworks.weapon.evolution.s2;

import java.util.function.Function;

public interface Factor {
    Function apply(Function applier);
}
