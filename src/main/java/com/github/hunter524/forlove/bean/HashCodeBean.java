package com.github.hunter524.forlove.bean;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HashCodeBean implements Comparable<HashCodeBean> {
    private String name;
    private int age;
    private String sex;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashCodeBean that = (HashCodeBean) o;
        return age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(sex, that.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, sex);
    }

    @Override
    public int compareTo(@NotNull HashCodeBean o) {
        return 0;
    }
}
