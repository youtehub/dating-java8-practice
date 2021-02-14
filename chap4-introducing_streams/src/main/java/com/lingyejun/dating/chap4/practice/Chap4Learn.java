package com.lingyejun.dating.chap4.practice;

import com.lingyejun.dating.chap1.Phone;
import com.lingyejun.dating.chap1.PhoneMain;
import com.lingyejun.dating.chap1.practice.PhonePrcs;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        List<PhonePrcs> phonePrcsList = PhonePrcs.initPhoneList();
        List<String> nameList = phonePrcsList.stream()
                .filter(phone -> Objects.equals(phone.getColor(),"银色"))
                .sorted(Comparator.comparing(PhonePrcs::getPrice))
                .map(PhonePrcs::getProductName)
                .collect(Collectors.toList());
        for (String name : nameList) {
            System.out.println(name);
        }
    }

}
