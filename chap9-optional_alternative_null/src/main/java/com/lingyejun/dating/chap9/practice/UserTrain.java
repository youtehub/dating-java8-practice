package com.lingyejun.dating.chap9.practice;

import lombok.*;

/**
 * 用户实体类
 *
 * @author yiyh
 * @date 2021-02-17 21:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserTrain {

    private String userName;

    private CarPra carPra;

    public UserTrain(String userName) {
        this.userName = userName;
    }
}
