package com.lingyejun.dating.chap1.practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 苹果手机的执行类
 *
 * @author yiyh
 * @date 2021-02-10 17:54
 */
public class PhoneMainSimpleTest {


    /**
     * 从手机列表中过滤出深空灰色的手机
     *
     * @param phoneProList 手机列表
     * @return phoneList 深空灰色的手机集合
     */
    public List<PhonePro> filterGrayPhoneList(List<PhonePro> phoneProList) {
        List<PhonePro> filteredPhonePrcs = new ArrayList<>();
        for (PhonePro phonePro : phoneProList) {
            if ("深空灰色".equals(phonePro.getColor())) {
                filteredPhonePrcs.add(phonePro);
            }
        }
        return filteredPhonePrcs;
    }

    /**
     * 从手机列表中过滤出金色的手机
     *
     * @param phoneProList 手机列表
     * @return phoneList 金色的手机集合
     */
    public List<PhonePro> filterGoldPhoneList(List<PhonePro> phoneProList) {
        List<PhonePro> filteredPhonePrcs = new ArrayList<>();
        for (PhonePro phonePro : phoneProList) {
            if ("金色".equals(phonePro.getColor())) {
                filteredPhonePrcs.add(phonePro);
            }
        }
        return filteredPhonePrcs;
    }


    /**
     * 从手机列表中按颜色过滤出手机
     *
     * @param phoneProList 手机列表
     * @param color     颜色
     * @param price     价格
     * @param choseFlag 选择值，true--按照颜色过滤，false--按照价格过滤
     * @return phoneList 金色的手机集合
     */
    public List<PhonePro> filterGoldPhoneListByCondition(List<PhonePro> phoneProList, String color, Integer price, Boolean choseFlag) {
        List<PhonePro> filteredPhonePrcs = new ArrayList<>();
        for (PhonePro phonePro : phoneProList) {

            if (choseFlag) {
                if (Objects.equals(color, phonePro.getColor())) {
                    filteredPhonePrcs.add(phonePro);
                }
            } else {
                if (phonePro.getPrice() > price) {
                    filteredPhonePrcs.add(phonePro);
                }
            }
        }
        return filteredPhonePrcs;
    }

    /**
     * 从手机列表中按颜色过滤出手机
     *
     * @param phoneProList 手机列表
     * @param color     颜色
     * @return phoneList 金色的手机集合
     */
    public List<PhonePro> filterGoldPhoneListByColor(List<PhonePro> phoneProList, String color) {
        List<PhonePro> filteredPhonePrcs = new ArrayList<>();
        for (PhonePro phonePro : phoneProList) {
            if (Objects.equals(color, phonePro.getColor())) {
                filteredPhonePrcs.add(phonePro);
            }
        }
        return filteredPhonePrcs;
    }

    /**
     * 从手机列表中按颜色过滤出手机
     *
     * @param phoneProList 手机列表
     * @param price     价格
     * @return phoneList 金色的手机集合
     */
    public List<PhonePro> filterGoldPhoneListByPrice(List<PhonePro> phoneProList, Integer price) {
        List<PhonePro> filteredPhonePrcs = new ArrayList<>();
        for (PhonePro phonePro : phoneProList) {
            if (phonePro.getPrice() > price) {
                filteredPhonePrcs.add(phonePro);
            }
        }
        return filteredPhonePrcs;
    }


    /**
     * 根据条件过滤手机
     * <p>
     * 需求1：我想看看颜色是深空灰色的手机有哪些？
     * 需求2：我想看看颜色是金色的手机有哪些？
     */
    @Test
    public void screenGrayPhone01() {
        List<PhonePro> phonePrcs = PhonePro.initPhoneList();
        List<PhonePro> filterGrayPhoneProList = filterGrayPhoneList(phonePrcs);
        List<PhonePro> filterGoldPhoneProList = filterGoldPhoneList(phonePrcs);
        System.out.println("深空灰色手机：" + filterGrayPhoneProList);
        System.out.println("金色手机：" + filterGoldPhoneProList);
    }


    /**
     * 根据条件过滤手机
     * <p>
     * 需求2：我想看看颜色是深空灰色的手机有哪些？
     */
    @Test
    public void screenGoldPhone02() {
        List<PhonePro> phonePrcs = PhonePro.initPhoneList();
        List<PhonePro> filterGoldPhoneProList = filterGoldPhoneListByColor(phonePrcs, "深空灰色");
        List<PhonePro> filterSilverPhoneProList = filterGoldPhoneListByColor(phonePrcs, "银色");
        System.out.println("深空灰色手机：" + filterGoldPhoneProList);
        System.out.println("银色手机：" + filterSilverPhoneProList);
    }

    /**
     * 根据条件过滤手机
     * <p>
     * 需求2：我想看看颜色是深空灰色的手机有哪些？
     */
    @Test
    public void screenGoldPhone03() {
        List<PhonePro> phonePrcs = PhonePro.initPhoneList();
        List<PhonePro> filterGoldPhoneProList = filterGoldPhoneListByCondition(phonePrcs, "深空灰色", null, true);
        List<PhonePro> filterSilverPhoneProList = filterGoldPhoneListByCondition(phonePrcs, "银色", null, true);
        List<PhonePro> filterPriceList = filterGoldPhoneListByCondition(phonePrcs, null, 8800, false);
        System.out.println("深空灰色手机：" + filterGoldPhoneProList);
        System.out.println("银色手机：" + filterSilverPhoneProList);
        System.out.println("价格超过8800的手机：" + filterPriceList);
    }

    /**
     * 根据条件过滤手机
     * <p>
     * 需求2：我想看看颜色是深空灰色的手机有哪些？
     */
    @Test
    public void screenGoldPhone04() {
        List<PhonePro> phonePrcs = PhonePro.initPhoneList();
        List<PhonePro> filterGoldPhoneProList = filterGoldPhoneListByCondition(phonePrcs, null, null, false);
        List<PhonePro> filterSilverPhoneProList = filterGoldPhoneListByCondition(phonePrcs, null, null, false);
        System.out.println("深空灰色手机：" + filterGoldPhoneProList);
        System.out.println("银色手机：" + filterSilverPhoneProList);
    }
}
