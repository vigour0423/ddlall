package com.ddl.learn.guava.utilities;

import com.google.common.base.Optional;
import org.junit.Test;

import java.io.Serializable;
import java.util.Set;

import static com.google.common.base.Predicates.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class OptionalTest {

    class Man implements Serializable {
        private Godness god;

        public Man() { }

        public Man(Godness god) { this.god = god; }

        public Godness getGod() { return god; }

        public Man setGod(Godness god) {
            this.god = god;
            return this;
        }

        @Override
        public String toString() { return "Man [god=" + god + "]"; }

    }

    class Godness {
        private String name;

        public Godness() { }

        public Godness(String name) { this.name = name; }

        public String getName() { return name; }

        public void setName(String name) { this.name = name; }

        @Override
        public String toString() { return "Godness [name=" + name + "]"; }

    }

    @Test
    public void testOptional() {
        Optional<Integer> of = Optional.of(5);

        Optional<Object> empty = Optional.fromNullable(null);

        assertThat(of.isPresent(), equalTo(true));
        assertThat(empty.isPresent(), equalTo(false));

        Integer result = (Integer) Optional.fromNullable(null).or(1);
        assertThat(result, equalTo(1));

        Set<Integer> integers = of.asSet();
        assertThat(integers.size(), equalTo(1));
        try {
            integers.add(3);
            fail();
        } catch (Exception e) {
            assertThat(instanceOf(e.getClass()), is(instanceOf(UnsupportedOperationException.class)));
        }
    }

    @Test
    public void testOptionalJdk() {
        System.out.println(getGodnessName(null));

        System.out.println(getGodnessName(new Man()));

        System.out.println(getGodnessName(new Man().setGod(new Godness("jack"))));
    }

    private String getGodnessName(Man man) {
        return java.util.Optional.ofNullable(man)
                .map(Man::getGod)
                .map(Godness::getName)
                .orElse("苍老师");
    }

    private String getGodnessName2(Man man) {
        if (man != null) {
            Godness g = man.getGod();
            if (g != null) {
                return g.getName();
            }
        }
        return "苍老师";
    }
}
