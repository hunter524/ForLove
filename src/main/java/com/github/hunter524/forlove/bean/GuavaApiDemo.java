package com.github.hunter524.forlove.bean;

import java.util.Objects;

public class GuavaApiDemo {
    public static void main(String[] args) {
        HashCodeBean big = new HashCodeBean("big", 20);
        HashCodeBean small = new HashCodeBean("small", 15);
        System.out.println("big compare to small:"+big.compareTo(small));
    }
}
