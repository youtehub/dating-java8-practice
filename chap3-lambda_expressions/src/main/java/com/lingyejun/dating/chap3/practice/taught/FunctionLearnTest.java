package com.lingyejun.dating.chap3.practice.taught;

import com.lingyejun.dating.chap1.practice.PhonePrcs;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * 学习Function下的接口使用
 *
 * @author yiyh
 * @date 2021-02-11 21:52
 */
public class FunctionLearnTest {

    /**
     * 学习Function函数的使用
     */
    @Test
    public void learnFunction() {
        // R apply(T t);
        Function<Integer, Integer> functionAdd = (Integer param) -> param + 2;
        Integer add = functionAdd.apply(5);
//        System.out.println("functionAdd()执行结果：" + add);

        // R apply(T t);
        Function<String, String> functionStr = (String str) -> str + "!!!!";
        String str = functionStr.apply("Hello World");
//        System.out.println("functionStr()执行结果：" + str);

        // R apply(T t);
        Function<Integer, Integer> functionMultiply = (Integer param) -> param * 2;
        Integer multiply = functionMultiply.apply(10);
//        System.out.println("functionMultiply()执行结果：" + multiply);

        //<V> Function<V, R> compose(Function<? super V, ? extends T> before)
        //compose()组合两个Function函数，param * 2 compose param+2 = (param+2) * 2
        Function<Integer, Integer> functionCompose = functionMultiply.compose(functionAdd);
        Integer compose = functionCompose.apply(20);
//        System.out.println("functionMultiply()执行结果：" + compose);

        // <V> Function<T, V> andThen(Function<? super R, ? extends V> after)
        //按照先后顺序拼接两个Function, param * 2 andThen param+2 = (param*2) + 2
        Function<Integer, Integer> functionAndThen = functionMultiply.andThen(functionAdd);
        Integer andThen = functionAndThen.apply(15);
//        System.out.println("functionAndThen()执行结果：" +andThen);

        //<T> Function<T, T> identity()
        //输入什么就返回什么,简化代码
        Function<String, String> functionIdentity = Function.identity();
        List<PhonePrcs> phonePrcsList = PhonePrcs.initPhoneList();
        Map<Integer, PhonePrcs> productName = phonePrcsList.stream()
                .filter(phone -> "iPhone 11 Pro".equals(phone.getProductName()))
//                .collect(Collectors.toMap(Phone::getProductName, phone -> phone));
                .collect(Collectors.toMap(PhonePrcs::getId, Function.identity()));
        System.out.println(productName);


    }

    /**
     * 学习Consumer函数的使用
     */
    @Test
    public void learnConsumer() {
//        void accept(T t);
        //吃掉外部传进来的T，在方法内部消化掉，什么也不返回
        Consumer<Integer> consumerNum = (Integer num) -> System.out.println("accept()执行返回的结果：" + num);
//        consumerNum.accept(223);

//        Consumer<T> andThen(Consumer<? super T> after)
        Consumer<Integer> consumerStr = (Integer str) -> System.out.println("Consumer2 执行了：" + str + " Done!");
        Consumer<Integer> consumerAndThen = consumerNum.andThen(consumerStr);
        consumerAndThen.accept(333);
    }

    /**
     * 学习Supplier函数的使用
     */
    @Test
    public void learnSupplier() {
        //T get();
        //无中生有，凭空生成一个东西出来
        Supplier<Integer> supplier = () -> 10;
        Integer supplierNum = supplier.get();
        System.out.println(supplierNum);
    }

    /**
     * 学习Predicate函数的使用
     */
    @Test
    public void learnPredicate() {
        // boolean test(T t);
        Predicate<Integer> predicateTest = (Integer num) -> num > 10;
        boolean test = predicateTest.test(20);
//        System.out.println("num == 10的判断结果：" + test);

        //Predicate<T> and(Predicate<? super T> other)
        // and 与操作
        Predicate<Integer> predicateGreater = (Integer num) -> num < 20;
        Predicate<Integer> predicateAnd = predicateTest.and(predicateGreater);
        boolean testAnd = predicateAnd.test(9);
//        System.out.println("num > 10 and num<20的结果：" + testAnd);

        //Predicate<T> or(Predicate<? super T> other)
        //or 或操作
        Predicate<Integer> predicateOr = predicateTest.or(predicateGreater);
        boolean testOr = predicateOr.test(15);
//        System.out.println("num > 10 or num<20的结果：" + testOr);

        Predicate<Integer> predicateGreater01 = (Integer num) -> num > 8;
        Predicate<Integer> predicateOr01 = predicateTest.or(predicateGreater01);
        boolean testOr01 = predicateOr01.test(7);
//        System.out.println("num > 10 or num>8的结果：" + testOr01);

//        <T> Predicate<T> isEqual(Object targetRef)
        // !  取反操作
        boolean test1 = predicateGreater01.negate().test(7);
        System.out.println(test1);
    }

    /**
     * 学习BiFunction函数的使用
     */
    @Test
    public void learnBiFunction() {
        //R apply(T t, U u)
        BiFunction<Integer, Integer, Integer> addBiFunction = (Integer addend, Integer augend) -> addend + augend;
        Integer addResult = addBiFunction.apply(10, 15);
//        System.out.println("addend + augend的结果：" + addResult);

        //<V> BiFunction<T, U, V> andThen(Function<? super R, ? extends V> after
        Function<Integer, Integer> mulBiFunction = (Integer addend) -> addend * 2;
        BiFunction<Integer, Integer, Integer> mixBiFunction = addBiFunction.andThen(mulBiFunction);
        Integer mixResult = mixBiFunction.apply(10, 15);
        System.out.println("mixBiFunction()执行结果：" + mixResult);
    }

    /**
     * 学习BiConsumer函数的使用
     */
    @Test
    public void learnBiConsumer() {
        //void accept(T t, U u)
        BiConsumer<Integer, Integer> andBiConsumer = (Integer num1, Integer num2) -> System.out.println("andBiConsumer执行结果：" + (num1 + num2));
//        andBiConsumer.accept(1, 2);

        //BiConsumer<T, U> andThen(BiConsumer<? super T, ? super U> after)
        BiConsumer<Integer, Integer> mulBiConsumer = (Integer num1, Integer num2) -> System.out.println("mulBiConsumer执行结果：" + (num1 * num2));
        BiConsumer<Integer, Integer> mixBiConsumer = andBiConsumer.andThen(mulBiConsumer);
        mixBiConsumer.accept(1, 2);
    }

    /**
     * 学习BiPredicate函数的使用
     */
    @Test
    public void learnBiPredicate() {
        //void accept(T t, U u)
        BiPredicate<Integer, Integer> compareBiPredicate = (Integer param1, Integer param2) -> param1 > 10 && param2 < 15;
        boolean compareResult = compareBiPredicate.test(11, 14);
//        System.out.println("compareBiPredicated结果：" + compareResult);

        //BiPredicate<T, U> and(BiPredicate<? super T, ? super U> other)
        BiPredicate<Integer, Integer> greaterBiPredicate = (Integer param1, Integer param2) -> param1 > 13;
        BiPredicate<Integer, Integer> andBiPredicate = compareBiPredicate.and(greaterBiPredicate);
        boolean andResult = andBiPredicate.test(11, 14);
        System.out.println("andBiPredicate()执行结果：" + andResult);

        //BiPredicate<T, U> or(BiPredicate<? super T, ? super U> other)
        BiPredicate<Integer, Integer> orBiPredicate = compareBiPredicate.or(greaterBiPredicate);
        boolean orResult = orBiPredicate.test(11, 14);
        System.out.println("orBiPredicate()执行结果：" + orResult);

        //BiPredicate<T, U> negate()
        BiPredicate<Integer, Integer> negateBiPredicate = greaterBiPredicate.negate();
        boolean negateResult = negateBiPredicate.test(11, 14);
        System.out.println("negateBiPredicate()执行结果：" + negateResult);
    }
}
