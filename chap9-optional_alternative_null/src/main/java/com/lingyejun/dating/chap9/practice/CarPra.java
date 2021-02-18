package com.lingyejun.dating.chap9.practice;

import lombok.*;

/**
 * 汽车实体类
 *
 * @author yiyh
 * @date 2021-02-17 21:28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarPra {

    private String carName;

    private InsuracePra insuracePra;
}
