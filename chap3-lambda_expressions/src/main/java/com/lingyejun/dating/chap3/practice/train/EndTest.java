package com.lingyejun.dating.chap3.practice.train;

import com.lingyejun.dating.chap1.practice.PhonePro;

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
        List<PhonePro> phoneProList = PhonePro.initPhoneList();

        //1.第一个方案。传递代码
        phoneProList.sort(new PhoneSortImpl());

        //2.使用匿名类
        phoneProList.sort(new Comparator<PhonePro>() {
            @Override
            public int compare(PhonePro o1, PhonePro o2) {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        });

        //3.使用lambda表达式
        phoneProList.sort((PhonePro o1, PhonePro o2) -> o1.getPrice().compareTo(o2.getPrice()));
        phoneProList.sort((phone1, phone2) -> phone1.getPrice().compareTo(phone2.getPrice()));
        phoneProList.sort(Comparator.comparing(phone -> phone.getPrice()));

        //4.用方法引用
        phoneProList.sort(Comparator.comparing(PhonePro::getPrice));
    }
}
