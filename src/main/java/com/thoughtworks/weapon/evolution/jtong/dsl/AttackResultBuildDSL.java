package com.thoughtworks.weapon.evolution.jtong.dsl;

import com.thoughtworks.weapon.evolution.jtong.AttackFactors;
import com.thoughtworks.weapon.evolution.jtong.AttackResult;
import com.thoughtworks.weapon.evolution.jtong.Injury;
import com.thoughtworks.weapon.evolution.jtong.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AttackResultBuildDSL {

    private AttackFactors attackFactors;
    private List<Function<AttackResultDSLEnv, AttackResultDSLEnv>> injuryContextFunctions = new ArrayList<>();

    public AttackResultBuildDSL(AttackFactors attackFactors){
        this.attackFactors = attackFactors;
    }

    public AttackResultBuildDSL startWith(Function<AttackResultDSLEnv, AttackResultDSLEnv> injuryContextFunction){
        injuryContextFunctions = new ArrayList<>();
        injuryContextFunctions.add(injuryContextFunction);
        return this;
    }

    public AttackResultBuildDSL andThen(Function<AttackResultDSLEnv, AttackResultDSLEnv> injuryContextFunction){
        injuryContextFunctions.add(injuryContextFunction);
        return this;
    }


    public AttackResult calculate(){

//        AttackResultDSLEnv resultEnv = injuryContextFunctions.apply(dslEnv);
        AttackResultDSLEnv resultEnv = doCalculate();

        Injury targetInjury = resultEnv.makeInjury();
        Injury sourceInjury = resultEnv.makeSourceInjury();
        Player target = resultEnv.getAttackFactors().getTarget();
        Player source = resultEnv.getAttackFactors().getSource();

        return new AttackResult(source, sourceInjury, target, targetInjury, resultEnv.isBreak());
    }

    private AttackResultDSLEnv doCalculate() {
        AttackResultDSLEnv dslEnv = new AttackResultDSLEnv(this.attackFactors, new AttackResultContext());

        for (Function<AttackResultDSLEnv, AttackResultDSLEnv> injuryContextFunction: injuryContextFunctions) {
            dslEnv = injuryContextFunction.apply(dslEnv);
            if(dslEnv.isBreak()){
                break;
            }
        }
        return dslEnv;
    }


}
