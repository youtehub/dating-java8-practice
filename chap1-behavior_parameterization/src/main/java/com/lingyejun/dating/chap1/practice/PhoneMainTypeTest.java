package com.lingyejun.dating.chap1.practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 手机过滤的公有泛型示例类
 *
 * @author yiyh
 * @date 2021-02-10 21:33
 */
public class PhoneMainTypeTest<T> {
    /**
     * 根据指定的过滤策略选出符合类型的手机
     *
     * @param list      需要处理的对象集合
     * @param predicate 过滤策略
     * @return 符合标准的手机
     */
    public List<T> usePredicateImpl(List<T> list, Predicate<T> predicate) {
        List<T> filterLise = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                filterLise.add(t);
            }
        }
        return filterLise;
    }

    /**
     * 根据指定的过滤策略选出符合类型的手机,结合stream实现
     *
     * @param list      需要处理的对象集合
     * @param predicate 过滤策略
     * @return 符合标准的手机
     */
    public List<T> usePredicateImplByStream(List<T> list, Predicate<T> predicate) {
        List<T> filterList = new ArrayList<>();
        //形式一
        filterList = list.stream()
                .map(t -> predicate.test(t) ? t : null)
                .filter(t -> t != null)
                .collect(Collectors.toList());
        //形式二
        filterList = list.stream()
                .filter(t -> predicate.test(t))
                .collect(Collectors.toList());
        return filterList;
    }


    /**
     * 测试根据指定的过滤策略选出符合类型的手机方法
     */
    @Test
    public void usePredicateImplTest() {
        List<PhonePro> phonePrcs = PhonePro.initPhoneList();
        PhoneMainTypeTest<PhonePro> phoneMainTypeTest = new PhoneMainTypeTest();
        Predicate<PhonePro> phonePredicate = (PhonePro phone) -> "256GB".equals(phone.getSpec());
        List<PhonePro> filterList = phoneMainTypeTest.usePredicateImpl(phonePrcs, phonePredicate);
        System.out.println(filterList);
    }

    /**
     * 测试根据指定的过滤策略选出符合类型的手机,结合stream实现的方法
     */
    @Test
    public void usePredicateImplByStreamTest() {
        List<PhonePro> phonePrcs = PhonePro.initPhoneList();
        PhoneMainTypeTest<PhonePro> phoneMainTypeTest = new PhoneMainTypeTest();
        Predicate<PhonePro> phonePredicate = (PhonePro phone) -> "256GB".equals(phone.getSpec());
        List<PhonePro> filterList = phoneMainTypeTest.usePredicateImplByStream(phonePrcs, phonePredicate);
        System.out.println(filterList);
    }


    /**
     * 建立一个线程
     */
    @Test
    public void buildThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("abc");
            }
        });
        Thread thread1 = new Thread(() -> System.out.println("cde"));

        thread.start();
        thread1.start();
    }
}
