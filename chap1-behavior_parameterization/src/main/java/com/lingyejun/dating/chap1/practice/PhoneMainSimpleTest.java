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
     * @param phoneList 手机列表
     * @return phoneList 深空灰色的手机集合
     */
    public List<Phone> filterGrayPhoneList(List<Phone> phoneList) {
        List<Phone> filteredPhone = new ArrayList<>();
        for (Phone phone : phoneList) {
            if ("深空灰色".equals(phone.getColor())) {
                filteredPhone.add(phone);
            }
        }
        return filteredPhone;
    }

    /**
     * 从手机列表中过滤出金色的手机
     *
     * @param phoneList 手机列表
     * @return phoneList 金色的手机集合
     */
    public List<Phone> filterGoldPhoneList(List<Phone> phoneList) {
        List<Phone> filteredPhone = new ArrayList<>();
        for (Phone phone : phoneList) {
            if ("金色".equals(phone.getColor())) {
                filteredPhone.add(phone);
            }
        }
        return filteredPhone;
    }


    /**
     * 从手机列表中按颜色过滤出手机
     *
     * @param phoneList 手机列表
     * @param color     颜色
     * @param price     价格
     * @param choseFlag 选择值，true--按照颜色过滤，false--按照价格过滤
     * @return phoneList 金色的手机集合
     */
    public List<Phone> filterGoldPhoneListByCondition(List<Phone> phoneList, String color, Integer price, Boolean choseFlag) {
        List<Phone> filteredPhone = new ArrayList<>();
        for (Phone phone : phoneList) {

            if (choseFlag) {
                if (Objects.equals(color, phone.getColor())) {
                    filteredPhone.add(phone);
                }
            } else {
                if (phone.getPrice() > price) {
                    filteredPhone.add(phone);
                }
            }
        }
        return filteredPhone;
    }

    /**
     * 从手机列表中按颜色过滤出手机
     *
     * @param phoneList 手机列表
     * @param color     颜色
     * @return phoneList 金色的手机集合
     */
    public List<Phone> filterGoldPhoneListByColor(List<Phone> phoneList, String color) {
        List<Phone> filteredPhone = new ArrayList<>();
        for (Phone phone : phoneList) {
            if (Objects.equals(color, phone.getColor())) {
                filteredPhone.add(phone);
            }
        }
        return filteredPhone;
    }

    /**
     * 从手机列表中按颜色过滤出手机
     *
     * @param phoneList 手机列表
     * @param price     价格
     * @return phoneList 金色的手机集合
     */
    public List<Phone> filterGoldPhoneListByPrice(List<Phone> phoneList, Integer price) {
        List<Phone> filteredPhone = new ArrayList<>();
        for (Phone phone : phoneList) {
            if (phone.getPrice() > price) {
                filteredPhone.add(phone);
            }
        }
        return filteredPhone;
    }


    /**
     * 根据条件过滤手机
     * <p>
     * 需求1：我想看看颜色是深空灰色的手机有哪些？
     * 需求2：我想看看颜色是金色的手机有哪些？
     */
    @Test
    public void screenGrayPhone01() {
        List<Phone> phones = Phone.initPhoneList();
        List<Phone> filterGrayPhoneList = filterGrayPhoneList(phones);
        List<Phone> filterGoldPhoneList = filterGoldPhoneList(phones);
        System.out.println("深空灰色手机：" + filterGrayPhoneList);
        System.out.println("金色手机：" + filterGoldPhoneList);
    }


    /**
     * 根据条件过滤手机
     * <p>
     * 需求2：我想看看颜色是深空灰色的手机有哪些？
     */
    @Test
    public void screenGoldPhone02() {
        List<Phone> phones = Phone.initPhoneList();
        List<Phone> filterGoldPhoneList = filterGoldPhoneListByColor(phones, "深空灰色");
        List<Phone> filterSilverPhoneList = filterGoldPhoneListByColor(phones, "银色");
        System.out.println("深空灰色手机：" + filterGoldPhoneList);
        System.out.println("银色手机：" + filterSilverPhoneList);
    }

    /**
     * 根据条件过滤手机
     * <p>
     * 需求2：我想看看颜色是深空灰色的手机有哪些？
     */
    @Test
    public void screenGoldPhone03() {
        List<Phone> phones = Phone.initPhoneList();
        List<Phone> filterGoldPhoneList = filterGoldPhoneListByCondition(phones, "深空灰色", null, true);
        List<Phone> filterSilverPhoneList = filterGoldPhoneListByCondition(phones, "银色", null, true);
        List<Phone> filterPriceList = filterGoldPhoneListByCondition(phones, null, 8800, false);
        System.out.println("深空灰色手机：" + filterGoldPhoneList);
        System.out.println("银色手机：" + filterSilverPhoneList);
        System.out.println("价格超过8800的手机：" + filterPriceList);
    }

    /**
     * 根据条件过滤手机
     * <p>
     * 需求2：我想看看颜色是深空灰色的手机有哪些？
     */
    @Test
    public void screenGoldPhone04() {
        List<Phone> phones = Phone.initPhoneList();
        List<Phone> filterGoldPhoneList = filterGoldPhoneListByCondition(phones, null, null, false);
        List<Phone> filterSilverPhoneList = filterGoldPhoneListByCondition(phones, null, null, false);
        System.out.println("深空灰色手机：" + filterGoldPhoneList);
        System.out.println("银色手机：" + filterSilverPhoneList);
    }
}
