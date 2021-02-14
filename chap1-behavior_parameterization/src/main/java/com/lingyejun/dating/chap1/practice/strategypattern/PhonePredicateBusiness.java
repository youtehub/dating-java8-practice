package com.lingyejun.dating.chap1.practice.strategypattern;

import com.lingyejun.dating.chap1.practice.PhonePrcs;

import java.util.ArrayList;
import java.util.List;

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
     * @param phonePrcsList      手机列表
     * @param phonePredicate 过滤策略
     * @return 符合标准的手机
     */
    public static List<PhonePrcs> usePredicateImpl(List<PhonePrcs> phonePrcsList, PhonePredicate phonePredicate) {
        List<PhonePrcs> filteredPhonePrcs = new ArrayList<>();
        for (PhonePrcs phonePrcs : phonePrcsList) {
            if (phonePredicate.percolate(phonePrcs)) {
                filteredPhonePrcs.add(phonePrcs);
            }
        }
        return filteredPhonePrcs;

    }
}
