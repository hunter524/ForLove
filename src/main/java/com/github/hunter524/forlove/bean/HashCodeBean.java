package com.github.hunter524.forlove.bean;

import com.google.common.collect.ComparisonChain;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HashCodeBean implements Comparable<HashCodeBean> {
    private String name;
    private int age;
    private String sex;

    public HashCodeBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

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
        return ComparisonChain.start().compare(this.age,o.age).result();
    }

    @Override
    public String toString() {
        return "HashCodeBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
