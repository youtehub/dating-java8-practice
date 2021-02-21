package com.lingyejun.dating.chap9.practice01;

import lombok.*;

import java.util.Optional;

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

    private CarTrain carTrain;

    private Optional<HouseTrain> houseTrain;

    public UserTrain(String userName) {
        this.userName = userName;
    }
}
