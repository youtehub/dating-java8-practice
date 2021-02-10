package com.lingyejun.dating.chap2.practice.strategypattern;

import com.lingyejun.dating.chap2.practice.Phone;
import lombok.*;

/**
 * 根据颜色判断的策略实现类
 *
 * @author yiyh
 * @date 2021-02-10 19:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PhoneColorPredicate implements PhonePredicate {

    /**
     * 颜色
     */
    private String color;

    /**
     * 判断手机是否满足某一个条件
     *
     * @param phone 需要校验的手机
     * @return 判断结果
     */
    @Override
    public boolean percolate(Phone phone) {
        if (color.equals(phone.getColor())) {
            return true;
        }
        return false;
    }
}
