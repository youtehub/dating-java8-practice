package com.lingyejun.dating.chap1.practice.strategypattern;

import com.lingyejun.dating.chap1.practice.PhonePro;
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
     * @param phonePro 需要校验的手机
     * @return 判断结果
     */
    @Override
    public boolean percolate(PhonePro phonePro) {
        if (color.equals(phonePro.getColor())) {
            return true;
        }
        return false;
    }
}
