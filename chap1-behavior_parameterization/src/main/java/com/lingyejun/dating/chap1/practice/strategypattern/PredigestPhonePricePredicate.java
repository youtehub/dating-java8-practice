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
public class PredigestPhonePricePredicate implements PhonePredicate {

    /**
     * 价格
     */
    private Integer price;

    /**
     * 判断手机是否满足某一个条件
     *
     * @param phonePro 需要校验的手机
     * @return 判断结果
     */
    @Override
    public boolean percolate(PhonePro phonePro) {
        return phonePro.getPrice() > price;
    }
}
