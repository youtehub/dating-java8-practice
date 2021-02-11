package com.lingyejun.dating.chap1.practice.strategypattern;

import com.lingyejun.dating.chap1.practice.Phone;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 手机过滤策略业务类
 *
 * @author yiyh
 * @date 2021-02-10 19:45
 */
public class PhonePredicateBusiness {

    private PhonePredicateBusiness() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 根据指定的过滤策略选出符合类型的手机
     *
     * @param phoneList      手机列表
     * @param phonePredicate 过滤策略
     * @return 符合标准的手机
     */
    public static List<Phone> usePredicateImpl(List<Phone> phoneList, PhonePredicate phonePredicate) {
        List<Phone> filteredPhone = new ArrayList<>();
        for (Phone phone : phoneList) {
            if (phonePredicate.percolate(phone)) {
                filteredPhone.add(phone);
            }
        }
        return filteredPhone;

    }
}
