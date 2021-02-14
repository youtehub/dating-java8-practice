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
     * @param phonePrcsList 手机列表
     * @return phoneList 深空灰色的手机集合
     */
    public List<PhonePrcs> filterGrayPhoneList(List<PhonePrcs> phonePrcsList) {
        List<PhonePrcs> filteredPhonePrcs = new ArrayList<>();
        for (PhonePrcs phonePrcs : phonePrcsList) {
            if ("深空灰色".equals(phonePrcs.getColor())) {
                filteredPhonePrcs.add(phonePrcs);
            }
        }
        return filteredPhonePrcs;
    }

    /**
     * 从手机列表中过滤出金色的手机
     *
     * @param phonePrcsList 手机列表
     * @return phoneList 金色的手机集合
     */
    public List<PhonePrcs> filterGoldPhoneList(List<PhonePrcs> phonePrcsList) {
        List<PhonePrcs> filteredPhonePrcs = new ArrayList<>();
        for (PhonePrcs phonePrcs : phonePrcsList) {
            if ("金色".equals(phonePrcs.getColor())) {
                filteredPhonePrcs.add(phonePrcs);
            }
        }
        return filteredPhonePrcs;
    }


    /**
     * 从手机列表中按颜色过滤出手机
     *
     * @param phonePrcsList 手机列表
     * @param color     颜色
     * @param price     价格
     * @param choseFlag 选择值，true--按照颜色过滤，false--按照价格过滤
     * @return phoneList 金色的手机集合
     */
    public List<PhonePrcs> filterGoldPhoneListByCondition(List<PhonePrcs> phonePrcsList, String color, Integer price, Boolean choseFlag) {
        List<PhonePrcs> filteredPhonePrcs = new ArrayList<>();
        for (PhonePrcs phonePrcs : phonePrcsList) {

            if (choseFlag) {
                if (Objects.equals(color, phonePrcs.getColor())) {
                    filteredPhonePrcs.add(phonePrcs);
                }
            } else {
                if (phonePrcs.getPrice() > price) {
                    filteredPhonePrcs.add(phonePrcs);
                }
            }
        }
        return filteredPhonePrcs;
    }

    /**
     * 从手机列表中按颜色过滤出手机
     *
     * @param phonePrcsList 手机列表
     * @param color     颜色
     * @return phoneList 金色的手机集合
     */
    public List<PhonePrcs> filterGoldPhoneListByColor(List<PhonePrcs> phonePrcsList, String color) {
        List<PhonePrcs> filteredPhonePrcs = new ArrayList<>();
        for (PhonePrcs phonePrcs : phonePrcsList) {
            if (Objects.equals(color, phonePrcs.getColor())) {
                filteredPhonePrcs.add(phonePrcs);
            }
        }
        return filteredPhonePrcs;
    }

    /**
     * 从手机列表中按颜色过滤出手机
     *
     * @param phonePrcsList 手机列表
     * @param price     价格
     * @return phoneList 金色的手机集合
     */
    public List<PhonePrcs> filterGoldPhoneListByPrice(List<PhonePrcs> phonePrcsList, Integer price) {
        List<PhonePrcs> filteredPhonePrcs = new ArrayList<>();
        for (PhonePrcs phonePrcs : phonePrcsList) {
            if (phonePrcs.getPrice() > price) {
                filteredPhonePrcs.add(phonePrcs);
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
        List<PhonePrcs> phonePrcs = PhonePrcs.initPhoneList();
        List<PhonePrcs> filterGrayPhonePrcsList = filterGrayPhoneList(phonePrcs);
        List<PhonePrcs> filterGoldPhonePrcsList = filterGoldPhoneList(phonePrcs);
        System.out.println("深空灰色手机：" + filterGrayPhonePrcsList);
        System.out.println("金色手机：" + filterGoldPhonePrcsList);
    }


    /**
     * 根据条件过滤手机
     * <p>
     * 需求2：我想看看颜色是深空灰色的手机有哪些？
     */
    @Test
    public void screenGoldPhone02() {
        List<PhonePrcs> phonePrcs = PhonePrcs.initPhoneList();
        List<PhonePrcs> filterGoldPhonePrcsList = filterGoldPhoneListByColor(phonePrcs, "深空灰色");
        List<PhonePrcs> filterSilverPhonePrcsList = filterGoldPhoneListByColor(phonePrcs, "银色");
        System.out.println("深空灰色手机：" + filterGoldPhonePrcsList);
        System.out.println("银色手机：" + filterSilverPhonePrcsList);
    }

    /**
     * 根据条件过滤手机
     * <p>
     * 需求2：我想看看颜色是深空灰色的手机有哪些？
     */
    @Test
    public void screenGoldPhone03() {
        List<PhonePrcs> phonePrcs = PhonePrcs.initPhoneList();
        List<PhonePrcs> filterGoldPhonePrcsList = filterGoldPhoneListByCondition(phonePrcs, "深空灰色", null, true);
        List<PhonePrcs> filterSilverPhonePrcsList = filterGoldPhoneListByCondition(phonePrcs, "银色", null, true);
        List<PhonePrcs> filterPriceList = filterGoldPhoneListByCondition(phonePrcs, null, 8800, false);
        System.out.println("深空灰色手机：" + filterGoldPhonePrcsList);
        System.out.println("银色手机：" + filterSilverPhonePrcsList);
        System.out.println("价格超过8800的手机：" + filterPriceList);
    }

    /**
     * 根据条件过滤手机
     * <p>
     * 需求2：我想看看颜色是深空灰色的手机有哪些？
     */
    @Test
    public void screenGoldPhone04() {
        List<PhonePrcs> phonePrcs = PhonePrcs.initPhoneList();
        List<PhonePrcs> filterGoldPhonePrcsList = filterGoldPhoneListByCondition(phonePrcs, null, null, false);
        List<PhonePrcs> filterSilverPhonePrcsList = filterGoldPhoneListByCondition(phonePrcs, null, null, false);
        System.out.println("深空灰色手机：" + filterGoldPhonePrcsList);
        System.out.println("银色手机：" + filterSilverPhonePrcsList);
    }
}
