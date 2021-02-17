package com.lingyejun.dating.chap5.practice;

import com.lingyejun.dating.chap5.Car;
import com.lingyejun.dating.chap5.House;
import com.lingyejun.dating.chap5.Worker;
import com.lingyejun.dating.chap5.WorkingWithStreams;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<String> wordList = Arrays.asList("Hello", "World");
        //需求：得到一个字符列表，且没有重复字符
        //第一种方式来完成，将每个单词分割成字符数组
        List<String[]> word01 = wordList.stream()
                .map(str -> str.split(""))
                .collect(Collectors.toList());
        List<String[]> word02 = word01.stream()
                .distinct()
                .collect(Collectors.toList());

        //第二种方式，Arrays.stream形式来解决,得到一个流的列表，还是无法解决
        Stream<Stream<String>> streamStream = wordList.stream()
                .map(str -> str.split(""))
                .map(Arrays::stream);

        //第三种方式，flatMap
        Stream<String> stream2 = wordList.stream()
                .map(str -> str.split(""))
                .flatMap(Arrays::stream);
        List<String> word03 = stream2.distinct()
                .collect(Collectors.toList());
        System.out.println("得到的字符数组：" + word03);
    }

    /**
     * 练习使用 flatMap
     */
    @Test
    public void trainFlatMap() {
        //查找员工拥有的所有汽车信息
        List<List<Car>> work9 = WORKERLIST.stream()
                .map(Worker::getCarList)
                .collect(Collectors.toList());

        //获取工人所拥有的汽车，去重得到不重复的汽车品牌列表

        //第一步，首先得到汽车对象
        List<Car> work10 = WORKERLIST.stream()
                .map(Worker::getCarList)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        //第二步，进行去重操作
        List<String> brandList = WORKERLIST.stream()
                .map(worker -> worker.getCarList())
                .filter(list -> list != null)
                .flatMap(list -> list.stream())
                .map(car -> car.getBrand())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("汽车的品牌名称：" + brandList);

        //需求：得到所有员工的住址列表
        List<String> addressList = WORKERLIST.stream()
                .map(Worker::getHouseList)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .map(House::getAddress)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("所有员工的住址列表: " + addressList);
    }

    // 查找匹配 allMatch、anyMatch、noneMatch、findFirst、findAny

    /**
     * 学习使用 allMatch
     */
    @Test
    public void learnAllMatch() {
        //需求：所有员工是否为男性
        boolean allMatch = WORKERLIST.stream()
                .allMatch(worker -> "男".equals(worker.getSex()));
        System.out.println("所有员工是否为男性: " + allMatch);
    }

    /**
     * 学习使用 anyMatch
     */
    @Test
    public void learnAnyMatch() {
        //需求：是否存在没有结婚的人
        boolean anyMatch = WORKERLIST.stream()
                .anyMatch(worker -> !worker.getMarried());
        System.out.println("是否存在没有结婚的人: " + anyMatch);

        //需求：过滤出单身的人名
        List<String> nameList = WORKERLIST.stream()
                .filter(worker -> !worker.getMarried())
                .map(Worker::getName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("单身的人名: " + nameList);
    }

    /**
     * 学习使用 noneMatch
     */
    @Test
    public void learnNoneMatch() {
        //所有的人的工资不超过20000
        boolean noneMatch = WORKERLIST.stream()
                .noneMatch(worker -> worker.getSalary() > 20000);
        System.out.println("所有的人的工资不超过20000: " + noneMatch);

    }


    /**
     * 学习使用 findAny
     */
    @Test
    public void learnFindAny() {
        //找出任意一个没有结婚的人
        Optional<Worker> optionalWorker = WORKERLIST.stream()
                .filter(worker -> !worker.getMarried())
                .findAny();
        Worker worker = new Worker();
        if (optionalWorker.isPresent()) {
            worker = optionalWorker.get();
        }
        System.out.println(worker);
    }

    /**
     * 学习使用 findFirst
     */
    @Test
    public void learnFindFirst() {
        //找一个未婚的女性中的第一个
        Optional<Worker> optionalWorker = WORKERLIST.stream()
                .filter(worker -> !worker.getMarried() && worker.getSex().equals("女"))
                .findFirst();
        System.out.println(optionalWorker);
    }

    // 归约 reduce

    /**
     * 学习使用 reduce
     */
    @Test
    public void learnReduce() {
        List<Integer> integerList = Arrays.asList(1, 2, 4, 5, 7, 8);
        Integer sum = integerList.stream().reduce(1, (a, b) -> a * b);
        System.out.println("integerList求和后：" + sum);

        //找到工人中工资最多是多少
        Optional<Integer> maxSalary = WORKERLIST.stream()
                .map(Worker::getSalary)
                .reduce(Integer::max);
        Optional<Integer> minSalary = WORKERLIST.stream()
                .map(Worker::getSalary)
                .reduce(Integer::max);
        System.out.println("工资最多是: " + maxSalary.get());
        System.out.println("工资最少是: " + minSalary.get());
    }

}
