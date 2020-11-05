package com.github.hunter524.forlove.bean;

public class ClassAssignable {
    public static void main(String[] args) {
        boolean assignableFrom = CharSequence.class.isAssignableFrom(String.class);
        System.out.println("charsequence is assignable from string:"+assignableFrom);
    }
}
