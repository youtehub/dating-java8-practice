package com.lingyejun.dating.chap2.practice;

import com.lingyejun.dating.chap2.practice.strategypattern.PhoneColorPredicate;
import com.lingyejun.dating.chap2.practice.strategypattern.PhonePredicate;
import com.lingyejun.dating.chap2.practice.strategypattern.PhonePredicateBusiness;
import com.lingyejun.dating.chap2.practice.strategypattern.PhonePricePredicate;
import org.junit.Test;

import java.util.List;

/**
 * 手机按照策略模式修改后的过滤测试类

 * @author  yiyh
 * @date    2021-02-10 20:01
 */
public class PhoneMainPercolateTest {

    /**
     * 根据颜色过滤手机
     * <p>
     */
    @Test
    public void screenPhoneByColor() {
        List<Phone> phones = Phone.initPhoneList();
        PhoneColorPredicate colorPredicate = new PhoneColorPredicate("深空灰色");
        List<Phone> phoneList = PhonePredicateBusiness.usePredicateImpl(phones, colorPredicate);
        System.out.println("深空灰色手机：" + phoneList);
    }

    /**
     * 根据价格过滤手机
     * <p>
     */
    @Test
    public void screenPhoneByPrice() {
        List<Phone> phones = Phone.initPhoneList();
        PhonePricePredicate pricePredicate = new PhonePricePredicate(8800);
        List<Phone> phoneList = PhonePredicateBusiness.usePredicateImpl(phones, pricePredicate);
        System.out.println("深空灰色手机：" + phoneList);
    }

    /**
     *  以匿名类的形式实现根据价格过滤手机
     * <p>
     */
    @Test
    public void screenPhoneByPriceAndAdapter() {
        List<Phone> phones = Phone.initPhoneList();
        PhonePredicate phonePredicate01 = new PhonePredicate() {
            @Override
            public boolean percolate(Phone phone) {
                String color = "暗夜绿色";
                if (color.equals(phone.getColor())) {
                    return true;
                }
                return false;
            }
        };
        PhonePredicate phonePredicate02 = new PhonePredicate() {
            @Override
            public boolean percolate(Phone phone) {
                return "暗夜绿色".equals(phone.getColor());
            }
        };
        List<Phone> phoneList = PhonePredicateBusiness.usePredicateImpl(phones, phonePredicate02);
        System.out.println("暗夜绿色色手机：" + phoneList);
    }

    /**
     *  以Lambda的形式实现根据价格过滤手机
     * <p>
     */
    @Test
    public void screenPhoneByPriceAndLambda() {
        List<Phone> phones = Phone.initPhoneList();
        PhonePredicate phonePredicate01 = (Phone phone) -> {
            String color = "暗夜绿色";
            if (color.equals(phone.getColor())) {
                return true;
            }
            return false;
        };
        PhonePredicate phonePredicate02 = (Phone phone) -> "暗夜绿色".equals(phone.getColor());
        List<Phone> phoneList = PhonePredicateBusiness.usePredicateImpl(phones, phonePredicate02);
        System.out.println("暗夜绿色色手机：" + phoneList);
    }

}
