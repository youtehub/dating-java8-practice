package com.lingyejun.dating.chap2.taught;

import com.google.common.base.Supplier;
import com.lingyejun.dating.chap1.Phone;
import com.lingyejun.dating.chap1.practice.strategypattern.PhonePredicate;
import com.lingyejun.dating.chap3.practice.taught.SourcePhonePredicateImpl;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Lambda初始化介绍
 *
 * @author yiyh
 * @date 2021-02-11 17:06
 */
public class LambdaMainTest {

    /**
     * Lambda结构介绍
     */
    @Test
    public void lambdaIntroduce() {
        //(parameters) -> expression
        Predicate<Phone> booleanFunction01 = (Phone phone) -> "红色".equals(phone.getColor());

        //(parameters) -> { statements; }
        Predicate<Phone> booleanFunction02 = (Phone phone) -> {
            return "红色".equals(phone.getColor());
        };

        Supplier<Phone> phoneSupplier = () -> new Phone();

        Consumer<Phone> phoneConsumer = (Phone phone) -> {
            System.out.println(phone.getSpec());
        };
    }


    /**
     * 函数式接口学习，Lambda就是函数式接口的一个具体实现的实例
     */
    @Test
    public void functionalInterface(){
        PhonePredicate phonePredicate01 = new SourcePhonePredicateImpl();
        com.lingyejun.dating.chap1.PhonePredicate lambdaType = (Phone phone) -> {
            System.out.println("This is phone");
            return true;
        };
        if(lambdaType.test(new Phone())){
            System.out.println("这是一个Phone");
        }
    }


    /**
     * 运行Runnable线程定义方式
     * @param runnable runnable定义线程
     */
    public static void processRun(Runnable runnable) {
        runnable.run();
    }

    /**
     * 使用Runnable定义线程来验证函数式接口
     */
    @Test
    public void runnableInterface(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable By 匿名类");
            }
        };

        Runnable runnable2 = () -> System.out.println("Runnable By Lambda");

        processRun(runnable);
        processRun(runnable2);
        processRun(() -> System.out.println("Runnable By Lambda"));
    }

}
