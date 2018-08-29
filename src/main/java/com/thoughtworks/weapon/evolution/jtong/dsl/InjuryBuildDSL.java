package com.thoughtworks.weapon.evolution.jtong.dsl;

import com.thoughtworks.weapon.evolution.jtong.AttackFactors;
import com.thoughtworks.weapon.evolution.jtong.Injury;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class InjuryBuildDSL {

    private AttackFactors attackFactors;
    private Function<InjuryDSLEnv, InjuryDSLEnv> injuryContextFunction;

    public InjuryBuildDSL(AttackFactors attackFactors){
        this.attackFactors = attackFactors;
    }

    public InjuryBuildDSL with(Function<InjuryDSLEnv, InjuryDSLEnv> injuryContextFunction){
        if(this.injuryContextFunction == null){
            this.injuryContextFunction = injuryContextFunction;
        }else{
            this.injuryContextFunction =
                    this.injuryContextFunction.andThen(injuryContextFunction);
        }
        return this;
    }


    public Injury calculate(){
        InjuryDSLEnv dslEnv = new InjuryDSLEnv(this.attackFactors, new InjuryResultContext());
        return injuryContextFunction.apply(dslEnv).makeInjury();
    }


}
