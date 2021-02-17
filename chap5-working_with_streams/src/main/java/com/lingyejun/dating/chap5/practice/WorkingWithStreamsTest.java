package com.lingyejun.dating.chap5.practice;

import com.lingyejun.dating.chap5.Worker;
import com.lingyejun.dating.chap5.WorkingWithStreams;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 流操作的具体学习
 *
 * @author yiyh
 * @date 2021-02-17 6:39
 */
public class WorkingWithStreamsTest {

    /**
     * 工人信息初始化
     */
    private static final List<Worker> WORKERLIST = WorkingWithStreams.initWorkerList();

    //筛选和切片  filter、distinct、limit、skip

    /**
     * 学习使用 filter
     */
    @Test
    public void learnFilter() {
        //需求：过滤出性别为男性的工人
        List<Worker> manWorkers = WORKERLIST.stream()
                .filter(worker -> "男".equals(worker.getSex()))
                .collect(Collectors.toList());
        System.out.println("性别为男性的工人:");
        manWorkers.forEach(System.out::println);

        //需求：过滤出已经结婚的工人
        List<Worker> isMarriedWorkers = WORKERLIST.stream()
                .filter(worker -> worker.getMarried())
                .collect(Collectors.toList());
        System.out.println("已经结婚的工人:");
        isMarriedWorkers.forEach(System.out::println);
    }

    /**
     * 学习使用 distinct
     */
    @Test
    public void learnDistinct() {
        //需求：筛选出列表中所有的偶数，并确保没有重复
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        List<Integer> integers = numbers.stream()
                .filter(num -> num % 2 == 0)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("这些偶数是：" + integers);

        //需求：过滤出不相等的薪水,也就是去除重复的薪水
        List<Integer> salaryList = WORKERLIST.stream()
                .map(Worker::getSalary)
                .distinct()
//                .sorted()
                .collect(Collectors.toList());
        System.out.println("不相等的薪水: " + salaryList);
    }

    /**
     * 学习使用 limit
     */
    @Test
    public void learnLimit() {
        //需求：按照集合原始顺序截取前3个员工
        List<Worker> workers = WORKERLIST.stream()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("截取的前3个员工：");
        workers.forEach(System.out::println);

        //需求：截取无序流Set的前两个
        Set<Integer> set1 = new HashSet<>();
        set1.add(2);
        set1.add(5);
        set1.add(7);
        set1.add(1);
        System.out.println("截取无序流Set的前两个:");
        set1.stream()
                .limit(2)
                .forEach(num -> System.out.print(" " + num));
        System.out.println();
    }

    /**
     * 学习使用 skip
     */
    @Test
    public void learnSkip() {
        //需求：按照集合原始顺序跳过前3个员工
        List<Worker> skipWorkers = WORKERLIST.stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("跳过前3个员工后的信息：");
        skipWorkers.forEach(System.out::println);
    }


    // 映射 map、flatMap

    /**
     * 学习使用 map
     */
    @Test
    public void learnMap() {
        //需求：获取所有员工的姓名
        //个人理解，姓名可能重复，需要去重
        List<String> nameList = WORKERLIST.stream()
                .map(Worker::getName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("所有员工的姓名: " + nameList);

        //需求：获取所有员工的性别，做一个性别列表
        List<String> sexList = WORKERLIST.stream()
                .map(Worker::getSex)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("性别列表: " + sexList);

        //需求：获取所有员工的姓名,并计算姓名的长度
        Map<String, Integer> nameLengthMap = WORKERLIST.stream()
                .map(Worker::getName)
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println("姓名和姓名长度各自为：");
        System.out.println("姓名  姓名长度");
        nameLengthMap.forEach((key, val) -> {
            System.out.println(key + "   " + val);
        });
    }

    /**
     * 学习使用 flatMap
     */
    @Test
    public void learnFlatMap() {

    }
}
