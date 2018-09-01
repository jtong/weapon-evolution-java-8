package com.thoughtworks.weapon.evolution.jtong;
import com.thoughtworks.weapon.evolution.jtong.skills.Skills;
import com.thoughtworks.weapon.evolution.jtong.state.PoisonState;
import com.thoughtworks.weapon.evolution.jtong.state.State;
import com.thoughtworks.weapon.evolution.jtong.state.StateTypes;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@DisplayName("weapon evolution java 8 suite")
class PlayerTest {

    @BeforeAll
    static void initTestEnv() {
    }

    @BeforeEach
    void initEveryMethod() {
    }

    @Test
    @DisplayName("normal person attack")
    void testNormal() {
        Player zhangsan = new Player("张三", 100, 10);
        Player lisi = new Player("李四", 100, 8);
        AttackResult attack_string = zhangsan.attack(lisi);
        assertThat(attack_string.toStringValue(), is("普通人张三攻击了普通人李四,李四受到了10点伤害,李四剩余生命：90"));
    }

    @Test
    @DisplayName("solider attack normal person")
    void testSolider() {
        Player zhangsan = new Player("张三", 100, 10, Role.SOLIDER, new Weapon("优质木棒", 2));
        Player lisi = new Player("李四", 100, 8);
        AttackResult attack_string = zhangsan.attack(lisi);
        assertThat(attack_string.toStringValue(), is("战士张三用优质木棒攻击了普通人李四,李四受到了12点伤害,李四剩余生命：88"));
    }

    @Test
    @DisplayName("normal person attack solider with armor")
    void testSolider2() {
        Player zhangsan = new Player("张三", 100, 10, Role.SOLIDER, new Weapon("优质木棒", 2), new Armor("优质皮甲", 2));
        Player lisi = new Player("李四", 100, 8);
        AttackResult attack_string = lisi.attack(zhangsan);
        assertThat(attack_string.toStringValue(), is("普通人李四攻击了战士张三,张三受到了6点伤害,张三剩余生命：94"));
    }


    @Test
    @DisplayName("Deadly Strike,solider attack normal person")
    void testSoliderDeadlyStrike() {
        Player zhangsan = new Player("张三", 100, 10, Role.SOLIDER, new Weapon("利剑", 2, Skills.Deadly_Strike));
        Player lisi = new Player("李四", 100, 8);
        AttackResult attack_string = zhangsan.attack(lisi);
        assertThat(attack_string.toStringValue(), is("战士张三用利剑攻击了普通人李四,张三发动了全力一击,李四受到了36点伤害,李四剩余生命：64"));
    }


    @Test
    @DisplayName("Poison Strike,solider attack normal person")
    void testSoliderPoisonStrike() {
        Player zhangsan = new Player("张三", 100, 10, Role.SOLIDER, new Weapon("优质毒剑", 2, Skills.Poison_Strike(2)));
        Player lisi = new Player("李四", 100, 8);
        AttackResult attack_string = zhangsan.attack(lisi);
        assertThat(attack_string.toStringValue(), is("战士张三用优质毒剑攻击了普通人李四,李四受到了12点伤害,李四中毒了,李四剩余生命：88"));
        List<State> states = lisi.getStates();
        assertThat(states.size(), is(1));
        assertThat(states.get(0).getType(), is(StateTypes.Poison));
    }

    @Test
    @DisplayName("Poisoned normal person attack")
    void testPoisoned1() {
        Player zhangsan = new Player("张三", 100, 10, Role.SOLIDER, new Weapon("优质毒剑", 2, Skills.Poison_Strike(2)), new Armor("优质皮甲", 2));
        Player lisi = new Player("李四", 100, 8);
        lisi.addState(new PoisonState(2));
        AttackResult attack_string = lisi.attack(zhangsan);
        assertThat(attack_string.toStringValue(), is("李四受到2点毒性伤害, 李四剩余生命：98\n普通人李四攻击了战士张三,张三受到了6点伤害,张三剩余生命：94"));
        List<State> states = lisi.getStates();
        assertThat(states.size(), is(1));
        assertThat(states.get(0).getType(), is(StateTypes.Poison));
    }


    @Test
    @DisplayName("Poisoned normal person die on attacking")
    void testPoisoned2() {
        Player zhangsan = new Player("张三", 100, 10, Role.SOLIDER, new Weapon("优质毒剑", 2, Skills.Poison_Strike(2)), new Armor("优质皮甲", 2));
        Player lisi = new Player("李四", 2, 8);
        lisi.addState(new PoisonState(2));
        AttackResult attack_string = lisi.attack(zhangsan);
        assertThat(attack_string.toStringValue(), is("李四受到2点毒性伤害, 李四剩余生命：0"));
        List<State> states = lisi.getStates();
        assertThat(states.size(), is(1));
        assertThat(states.get(0).getType(), is(StateTypes.Poison));
    }

    @Test
    @DisplayName("Poisoned normal person die on attacking with multiple states")
    void testPoisoned3() {
        Player zhangsan = new Player("张三", 100, 10, Role.SOLIDER, new Weapon("优质毒剑", 2, Skills.Poison_Strike(2)), new Armor("优质皮甲", 2));
        Player lisi = new Player("李四", 3, 8);
        lisi.addState(new PoisonState(2));
        lisi.addState(new PoisonState(3));
        AttackResult attack_string = lisi.attack(zhangsan);
        assertThat(attack_string.toStringValue(), is("李四受到5点毒性伤害, 李四剩余生命：-2"));
        List<State> states = lisi.getStates();
        assertThat(states.size(), is(1));
        assertThat(states.get(0).getType(), is(StateTypes.Poison));
    }
}
