package com.lingyejun.dating.chap4.practice;

import com.lingyejun.dating.chap1.Phone;
import com.lingyejun.dating.chap1.PhoneMain;
import com.lingyejun.dating.chap1.practice.PhonePro;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream 流学习
 *
 * @author yiyh
 * @date 2021-02-14 19:34
 */
public class Chap4Learn {

    /**
     * 按照价格排序后，得到手机名称列表
     * <p>
     * 这是原始的版本
     *
     * @return 手机名称列表
     */
    @Test
//    public List<String> convertSortNameList() {
    public void convertSortNameList() {
        // 先拿到原始的手机集合
        List<Phone> phoneList = PhoneMain.initPhoneList();
        //按照价格排序
        phoneList.sort(new Comparator<Phone>() {
            @Override
            public int compare(Phone o1, Phone o2) {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        });
        //取出对应的手机名称
        List<String> nameList = new ArrayList<>();
        for (Phone phone : phoneList) {
            nameList.add(phone.getProductName());
        }
//        nameList.forEach(System.out::println);
        for (String name : nameList) {
            System.out.println(name);
        }
//        return nameList;
    }

    /**
     * 以stream流的形式进行过滤
     * 按照价格排序后，得到手机名称列表
     */
    @Test
    public void convertByStream() {
        List<String> nameList = PhoneMain.initPhoneList()
                .stream()
                .sorted(Comparator.comparing(Phone::getPrice))
                .map(Phone::getProductName)
                .collect(Collectors.toList());
        for (String name : nameList) {
            System.out.println(name);
        }
    }

    /**
     * 以stream流的形式进行过滤
     * 按照颜色排序后，得到手机名称列表
     */
    @Test
    public void convertColorByStream() {
        List<PhonePro> phoneProList = PhonePro.initPhoneList();
        List<String> nameList = phoneProList.stream()
                .filter(phone -> Objects.equals(phone.getColor(), "银色"))
                .sorted(Comparator.comparing(PhonePro::getPrice))
                .map(PhonePro::getProductName)
                .collect(Collectors.toList());
        for (String name : nameList) {
            System.out.println(name);
        }
    }

    /**
     * 测试Stream流只执行一次
     */
    @Test
    public void testExecuteOnce() {
        Stream<Phone> phoneStream = PhoneMain.initPhoneList().stream();
        phoneStream.forEach(phone -> System.out.println(phone.getPrice()));
        //流只能遍历和消费一次
        phoneStream.forEach(phone1 -> System.out.println(phone1.getPrice()));
    }


    /**
     * 遍历数据时的区别
     */
    @Test
    public void differentFor() {
        List<Phone> phoneList = PhoneMain.initPhoneList();
        List<Integer> phonePriceList = new ArrayList<>();
        //使用for-each循环外部迭代
        phoneList.forEach(phone -> {
            phonePriceList.add(phone.getPrice());
        });

        //使用其背后的迭代器做外部迭代
        Iterator<Phone> iterator = phoneList.iterator();
        while (iterator.hasNext()) {
            Phone phone = iterator.next();
            phonePriceList.add(phone.getPrice());
        }

        //使用流做内部迭代
        List<Integer> priceList = phoneList.stream()
                .map(Phone::getPrice)
                .collect(Collectors.toList());
    }

    /**
     * 流的操作类型
     */
    @Test
    public void streamOperatorType(){
        List<Phone> phoneList = PhoneMain.initPhoneList();

        //手机列表中，拿到价格排序前两个的手机名称
        List<String> phoneNameList = phoneList.stream()
//                .sorted(Comparator.comparing(Phone::getPrice))
                .sorted(Comparator.comparing(Phone::getPrice).reversed())
                .limit(2)
                .map(Phone::getProductName)
                .collect(Collectors.toList());
        phoneNameList.forEach(name -> System.out.println(name));
    }

    /**
     * 测试流的合并
     */
    @Test
    public void streamJoin(){
        List<String> nameList = PhoneMain.initPhoneList()
                .stream()
                .filter(phone -> {
                    System.out.println("filter: " + phone.getProductName());
                    return Objects.equals(phone.getColor(), "银色");
                })
                .map(phone -> {
                    System.out.println("map: " + phone.getProductName());
                    return phone.getProductName();
                })
                .collect(Collectors.toList());
        System.out.println(nameList);
    }
}
