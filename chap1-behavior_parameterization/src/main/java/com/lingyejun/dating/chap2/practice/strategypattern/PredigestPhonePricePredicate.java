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
public class PredigestPhonePricePredicate implements PhonePredicate {

    /**
     * 价格
     */
    private Integer price;

    /**
     * 判断手机是否满足某一个条件
     *
     * @param phone 需要校验的手机
     * @return 判断结果
     */
    @Override
    public boolean percolate(Phone phone) {
        return phone.getPrice() > price;
    }
}
