package com.lingyejun.dating.chap3;

import com.lingyejun.dating.chap1.Phone;

import java.util.List;

/**
 * @Author: lingyejun
 * @Date: 2019/10/27
 * @Describe:
 * @Modified By:
 */
@FunctionalInterface
public interface ProcessFind {

    List<Phone> queryPhoneList(List<Phone> list);
}
