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
public class PhonePro {


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
    public static List<PhonePro> initPhoneList() {
        List<PhonePro> phonePrcs = new ArrayList<>();
        PhonePro phonePro1 = new PhonePro(1, "iPhone 11 Pro", "深空灰色", "64GB", 8699);
        PhonePro phonePro2 = new PhonePro(2, "iPhone 11 Pro", "银色", "64GB", 8700);
        PhonePro phonePro3 = PhonePro.builder().id(3).productName("iPhone 11 Pro Max").color("银色").spec("256GB").price(8900).build();

        phonePrcs.add(phonePro1);
        phonePrcs.add(phonePro2);
        phonePrcs.add(phonePro3);

        return phonePrcs;
    }

}
