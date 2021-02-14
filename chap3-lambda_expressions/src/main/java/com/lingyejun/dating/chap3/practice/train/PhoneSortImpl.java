package com.lingyejun.dating.chap3.practice.train;

import com.lingyejun.dating.chap1.practice.PhonePro;

import java.util.Comparator;

/**
 * 实现手机的排序
 *
 * @author yiyh
 * @date 2021-02-14 16:08
 */
public class PhoneSortImpl implements Comparator<PhonePro> {

    @Override
    public int compare(PhonePro o1, PhonePro o2) {
        return o1.getPrice().compareTo(o2.getPrice());
    }
}
