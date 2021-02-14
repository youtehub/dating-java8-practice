package com.lingyejun.dating.chap3.practice.train;

import com.lingyejun.dating.chap1.practice.PhonePrcs;

import java.util.Comparator;

/**
 * 实现手机的排序
 *
 * @author yiyh
 * @date 2021-02-14 16:08
 */
public class PhoneSortImpl implements Comparator<PhonePrcs> {

    @Override
    public int compare(PhonePrcs o1, PhonePrcs o2) {
        return o1.getPrice().compareTo(o2.getPrice());
    }
}
