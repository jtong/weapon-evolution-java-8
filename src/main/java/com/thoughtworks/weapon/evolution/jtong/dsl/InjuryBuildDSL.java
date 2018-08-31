package com.thoughtworks.weapon.evolution.jtong.dsl;

import com.thoughtworks.weapon.evolution.jtong.AttackFactors;
import com.thoughtworks.weapon.evolution.jtong.AttackResult;
import com.thoughtworks.weapon.evolution.jtong.Injury;
import com.thoughtworks.weapon.evolution.jtong.Player;

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


    public AttackResult calculate(){
        InjuryDSLEnv dslEnv = new InjuryDSLEnv(this.attackFactors, new InjuryResultContext());
        InjuryDSLEnv resultEnv = injuryContextFunction.apply(dslEnv);
        Injury targetInjury = resultEnv.makeInjury();
        Injury sourceInjury = resultEnv.makeSourceInjury();
        Player target = dslEnv.getAttackFactors().getTarget();
        Player source = dslEnv.getAttackFactors().getSource();

        return new AttackResult(source, sourceInjury, target, targetInjury);
    }


}
