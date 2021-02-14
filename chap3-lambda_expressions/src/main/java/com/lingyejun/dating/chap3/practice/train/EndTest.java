package com.lingyejun.dating.chap3.practice.train;

import com.lingyejun.dating.chap1.practice.Phone;

import java.util.Comparator;
import java.util.List;

/**
 * 使用函数式接口
 *
 * @author yiyh
 * @date 2021-02-14 15:56
 */
public class EndTest {

    public static void main(String[] args) {
        // 使用不同的策略排序手机列表
        List<Phone> phoneList = Phone.initPhoneList();

        //1.第一个方案。传递代码
        phoneList.sort(new PhoneSortImpl());

        //2.使用匿名类
        phoneList.sort(new Comparator<Phone>() {
            @Override
            public int compare(Phone o1, Phone o2) {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        });

        //3.使用lambda表达式
        phoneList.sort((Phone o1, Phone o2) -> o1.getPrice().compareTo(o2.getPrice()));
        phoneList.sort((phone1, phone2) -> phone1.getPrice().compareTo(phone2.getPrice()));
        phoneList.sort(Comparator.comparing(phone -> phone.getPrice()));

        //4.用方法引用
        phoneList.sort(Comparator.comparing(Phone::getPrice));
    }
}
