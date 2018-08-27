package com.thoughtworks.weapon.evolution.jtong;
import org.junit.jupiter.api.*;

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
}
