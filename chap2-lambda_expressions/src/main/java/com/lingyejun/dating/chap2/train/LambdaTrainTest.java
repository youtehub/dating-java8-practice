package com.lingyejun.dating.chap2.train;

import com.lingyejun.dating.chap1.practice.PhonePro;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    public static List<PhonePro> getPhoneListByName(List<PhonePro> list) {
        List<PhonePro> phoneProList = new ArrayList<>();
        for (PhonePro phonePro : list) {
            if ("iPhone 11 Pro".equals(phonePro.getProductName())) {
                phoneProList.add(phonePro);
            }
        }
        return phoneProList;
    }


    /**
     * 1.记得行为参数化，这是第一步
     */
    @Test
    public void parameteRization() {
        ProcessFind processFind = new ProcessFind() {
            @Override
            public List<PhonePro> queryPhoneList(List<PhonePro> list) {
                List<PhonePro> phoneProList = new ArrayList<>();
                for (PhonePro phone : list) {
                    if ("iPhone 11 Pro".equals(phone.getProductName())) {
                        phoneProList.add(phone);
                    }
                }
                return phoneProList;
            }
        };
        List<PhonePro> phoneProList = PhonePro.initPhoneList();
        List<PhonePro> queryPhoneProList = processFind.queryPhoneList(phoneProList);
        System.out.println(queryPhoneProList);

        // 2.使用函数式接口来传递行为 -> 将行为进行抽象，提取到ProcessFind接口当中
        // 3.执行一个行为 -> 在外部代码中执行函数式接口当中的抽象方法
        // 4.传递Lambda
        ProcessFind processFindLambda = (List<PhonePro> list) -> {
            List<PhonePro> phoneProList01 = new ArrayList<>();
            for (PhonePro phone : list) {
                if ("iPhone 11 Pro".equals(phone.getProductName())) {
                    phoneProList01.add(phone);
                }
            }
            return phoneProList01;

//            phoneList01 = list.stream()
//                    .filter(phone -> "iPhone 11 Pro".equals(phone.getProductName()))
//                    .collect(Collectors.toList());
//            return phoneList01;

//            return list.stream()
//                    .filter(phone -> "iPhone 11 Pro".equals(phone.getProductName()))
//                    .collect(Collectors.toList());
        };
        List<PhonePro> queryPhoneProList01 = processFindLambda.queryPhoneList(phoneProList);
        System.out.println(queryPhoneProList01);
    }


}
