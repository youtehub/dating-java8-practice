package com.lingyejun.dating.chap1.practice.strategypattern;

import com.lingyejun.dating.chap1.practice.Phone;

/**
 * 手机过滤策略接口
 *
 * @author yiyh
 * @date 2021-02-10 19:45
 */
@FunctionalInterface
public interface PhonePredicate {

    /**
     * 判断手机是否满足某一个条件
     *
     * @param phone 需要校验的手机
     * @return 判断结果
     */
    boolean percolate(Phone phone);
}
