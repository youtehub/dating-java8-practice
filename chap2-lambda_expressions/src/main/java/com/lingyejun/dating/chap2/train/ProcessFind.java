package com.lingyejun.dating.chap2.train;

import com.lingyejun.dating.chap1.practice.PhonePro;

import java.util.List;

/**
 * 手机查找的流程定义接口
 *
 * @author yiyh
 * @date 2021-02-11 19:23
 */
@FunctionalInterface
public interface ProcessFind {

    /**
     * 筛选符合条件的手机
     *
     * @param list 需要筛选的手机
     * @return 符合条件的手机列表
     */
    List<PhonePro> queryPhoneList(List<PhonePro> list);
}
