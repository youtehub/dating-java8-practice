package com.lingyejun.dating.chap1.practice;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 苹果手机实体类
 *
 * @author yiyh
 * @date 2021-02-10 17:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Phone {


    /**
     * 编号
     */
    private Integer id;

    /**
     * 名称
     */

    private String productName;

    /**
     * 颜色
     */
    private String color;

    /**
     * 制式
     */
    private String spec;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 初始化手机列表
     *
     * @return List<Phone> 手机列表
     */
    public static List<Phone> initPhoneList() {
        List<Phone> phones = new ArrayList<>();
        Phone phone1 = new Phone(1, "iPhone 11 Pro", "深空灰色", "64GB", 8699);
        Phone phone2 = new Phone(2, "iPhone 11 Pro", "银色", "64GB", 8700);
        Phone phone3 = Phone.builder().id(3).productName("iPhone 11 Pro Max").color("银色").spec("256GB").price(8900).build();

        phones.add(phone1);
        phones.add(phone2);
        phones.add(phone3);

        return phones;
    }

}
