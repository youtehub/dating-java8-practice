package com.lingyejun.dating.chap3.practice.taught;

import com.lingyejun.dating.chap1.Phone;
import com.lingyejun.dating.chap1.PhonePredicate;
import org.junit.Test;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 方法引用的学习
 *
 * @author yiyh
 * @date 2021-02-12 16:53
 */
public class MethodReference {

//

    /**
     * 指向静态方法的方法引用
     */
    @Test
    public void pointStaticMethodReference() {
        //默认方式
        Function<String, Integer> defaultFunction = (String str) -> Integer.parseInt(str);
        Integer integer = defaultFunction.apply("15");
//        System.out.println("defaultFunction的执行结果：" + integer);

//        指向静态方法的方法引用(例如Integer的parseInt方法，写作Integer::parseInt)。
        Function<String, Integer> staticMethodFunction = Integer::parseInt;
        Integer staticMethod = staticMethodFunction.apply("20");
        System.out.println("staticMethodFunction的执行结果：" + staticMethod);
    }

    /**
     * 指向任意类型实例方法的方法引用
     */
    @Test
    public void pointInstanceMethod() {
        Function<String, Integer> defaultFunction = (String str) -> str.length();
        Integer defaults = defaultFunction.apply("abc");
//        System.out.println("defaultFunction的执行结果："+defaults);

        Function<String, Integer> instanceMethodFunction = String::length;
        Integer instanceMethod = instanceMethodFunction.apply("abcde");
        System.out.println("InstantiateMethod的执行结果：" + instanceMethod);
    }


    /**
     * 指向现有对象的实例方法的方法引用
     * <p>
     * 调用外部的对象
     */
    @Test
    public void pointObjectInstanceMethod() {
        PhonePredicate phonePredicate = (Phone phone) -> true;
        Function<Phone, Boolean> defaultFunction = (Phone phone) -> phonePredicate.test(phone);
        Boolean instance = defaultFunction.apply(new Phone());
        System.out.println("defaultFunction的执行结果：" + instance);

        Function<Phone, Boolean> objectInstanceMethod = phonePredicate::test;
        Boolean objectInstance = objectInstanceMethod.apply(new Phone());
        System.out.println("objectInstanceMethod的执行结果：" + objectInstance);

        BiPredicate<List<String>, String> contains01 = (list, element) -> list.contains(element);
        BiPredicate<List<String>, String> contains02 = List::contains;
    }


    /**
     * 构造方法的引用
     */
    @Test
    public void constructorReference() {
        Supplier<Phone> supplier = Phone::new;
        Function<Integer, Phone> supplier1 = Phone::new;
    }

}
