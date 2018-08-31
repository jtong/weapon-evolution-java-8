package com.thoughtworks.weapon.evolution.jtong.dsl;

import com.thoughtworks.weapon.evolution.jtong.AttackFactors;
import com.thoughtworks.weapon.evolution.jtong.AttackResult;
import com.thoughtworks.weapon.evolution.jtong.Injury;
import com.thoughtworks.weapon.evolution.jtong.Player;

import java.util.function.Function;

public class AttackResultBuildDSL {

    private AttackFactors attackFactors;
    private Function<AttackResultDSLEnv, AttackResultDSLEnv> injuryContextFunction;

    public AttackResultBuildDSL(AttackFactors attackFactors){
        this.attackFactors = attackFactors;
    }

    public AttackResultBuildDSL with(Function<AttackResultDSLEnv, AttackResultDSLEnv> injuryContextFunction){
        if(this.injuryContextFunction == null){
            this.injuryContextFunction = injuryContextFunction;
        }else{
            this.injuryContextFunction =
                    this.injuryContextFunction.andThen(injuryContextFunction);
        }
        return this;
    }


    public AttackResult calculate(){
        AttackResultDSLEnv dslEnv = new AttackResultDSLEnv(this.attackFactors, new AttackResultContext());
        AttackResultDSLEnv resultEnv = injuryContextFunction.apply(dslEnv);
        Injury targetInjury = resultEnv.makeInjury();
        Injury sourceInjury = resultEnv.makeSourceInjury();
        Player target = dslEnv.getAttackFactors().getTarget();
        Player source = dslEnv.getAttackFactors().getSource();

        return new AttackResult(source, sourceInjury, target, targetInjury);
    }


}
