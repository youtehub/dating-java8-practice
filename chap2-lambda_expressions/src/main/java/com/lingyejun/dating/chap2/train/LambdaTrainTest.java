package com.lingyejun.dating.chap2.train;

import com.lingyejun.dating.chap1.practice.Phone;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 练习Lambda，根据需求训练
 * 寻找手机名为Iphone11的手机列表
 *
 * @author yiyh
 * @date 2021-02-11 19:15
 */
public class LambdaTrainTest {

    /**
     * 根据产品名字筛选出手机
     *
     * @param list 手机列表
     * @return 筛选出的手机
     */
    public static List<Phone> getPhoneListByName(List<Phone> list) {
        List<Phone> phoneList = new ArrayList<>();
        for (Phone phone : list) {
            if ("iPhone 11 Pro".equals(phone.getProductName())) {
                phoneList.add(phone);
            }
        }
        return phoneList;
    }


    /**
     * 1.记得行为参数化，这是第一步
     */
    @Test
    public void parameteRization() {
        ProcessFind processFind = new ProcessFind() {
            @Override
            public List<Phone> queryPhoneList(List<Phone> list) {
                List<Phone> phoneList = new ArrayList<>();
                for (Phone phone : list) {
                    if ("iPhone 11 Pro".equals(phone.getProductName())) {
                        phoneList.add(phone);
                    }
                }
                return phoneList;
            }
        };
        List<Phone> phoneList = Phone.initPhoneList();
        List<Phone> queryPhoneList = processFind.queryPhoneList(phoneList);
        System.out.println(queryPhoneList);

        // 2.使用函数式接口来传递行为 -> 将行为进行抽象，提取到ProcessFind接口当中
        // 3.执行一个行为 -> 在外部代码中执行函数式接口当中的抽象方法
        // 4.传递Lambda
        ProcessFind processFindLambda = (List<Phone> list) -> {
            List<Phone> phoneList01 = new ArrayList<>();
            for (Phone phone : list) {
                if ("iPhone 11 Pro".equals(phone.getProductName())) {
                    phoneList01.add(phone);
                }
            }
            return phoneList01;

//            phoneList01 = list.stream()
//                    .filter(phone -> "iPhone 11 Pro".equals(phone.getProductName()))
//                    .collect(Collectors.toList());
//            return phoneList01;

//            return list.stream()
//                    .filter(phone -> "iPhone 11 Pro".equals(phone.getProductName()))
//                    .collect(Collectors.toList());
        };
        List<Phone> queryPhoneList01 = processFindLambda.queryPhoneList(phoneList);
        System.out.println(queryPhoneList01);
    }


}
