package com.lingyejun.dating.chap2_3.practice.taught;

import com.lingyejun.dating.chap1.practice.Phone;
import com.lingyejun.dating.chap1.practice.strategypattern.PhonePredicate;

/**
 * 手机过滤策略具体实现类
 *
 * @author yiyh
 * @date 2021-02-11 17:20
 */
public class SourcePhonePredicateImpl implements PhonePredicate {

    /**
     * 判断手机是否满足某一个条件
     *
     * @param phone 需要校验的手机
     * @return 判断结果
     */
    @Override
    public boolean percolate(Phone phone) {
        System.out.println("This is phone");
        return false;
    }
}
