package com.github.hunter524.forlove.bean;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuavaApiDemo {
    public static void main(String[] args) {

//        Optional
        Optional<HashCodeBean> hashCodeBeanOptional = Optional.of(new HashCodeBean("optional", 2));
        assert hashCodeBeanOptional.isPresent();
        System.out.println("pass optional isPresent assert");

        Optional<Object> objectOptional = Optional.fromNullable(null);
        assert !objectOptional.isPresent();
        System.out.println("pass optional is Not Present assert ");
//        Preconditions (运行遇到偶数则抛出异常)
        try {
            Preconditions.checkArgument(System.currentTimeMillis() % 2 == 0,
                    "tisp1: %s tips2 %s", "tipval1", "tipval2");
        } catch (Throwable thr) {
            thr.printStackTrace();
        }

//        ComprisonChain
        HashCodeBean big = new HashCodeBean("big", 20);
        HashCodeBean small = new HashCodeBean("small", 15);
        System.out.println("big compare to small:" + big.compareTo(small));
//        Ordering （第一层排序规则为 HashCodeBean#compareto 定义的年龄排序，第二层排序规则为使用onResultOf 定义的名称字母排序规则)
        HashCodeBean big2 = new HashCodeBean("abig", 20);
        Ordering<HashCodeBean> secondaryComparator = Ordering.natural()
                                                             .onResultOf(input -> input.getName());
        Ordering<HashCodeBean> natural = Ordering.natural()
                                                 .nullsFirst()
                                                 .compound(secondaryComparator);
        ArrayList<HashCodeBean> elements = new ArrayList<>();
        elements.add(big);
        elements.add(small);
        elements.add(null);
        elements.add(big2);
        List<HashCodeBean> sortedList = natural.sortedCopy(elements);
        System.out.println("sorted copy list:" + Arrays.deepToString(sortedList.toArray()));
//        Throwables
        throwMethod();
//        immutable Collections
        ImmutableList<String> immutableList = ImmutableList.of("a", "b", "c");
//        construct immutable list from builder
        ImmutableList.builder();
    }

    public static final void throwMethod() {
        try {
            System.out.println(System.currentTimeMillis());
            throw new Throwable();
        } catch (Throwable throwable) {
//            该处不能抛出异常（否则方法要声明 Throwable 受查异常)
//            throw throwable;
            Throwables.throwIfUnchecked(throwable);
        }
    }
}
