package com.lingyejun.dating.chap9.practice01;

import org.junit.Test;

import java.util.Objects;
import java.util.Optional;

/**
 * 练习Optional
 *
 * @author yiyh
 * @date 2021-02-21 19:19
 */
public class TrainOptionalTest {

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    private static UserTrain getUserPra() {
        UserTrain userTrain = UserTrain.builder().userName("张三").build();
        CarTrain carTrain = new CarTrain();
        carTrain.setCarName("red car");

        InsuraceTrain insuraceTrain = new InsuraceTrain();
        insuraceTrain.setInsuranceName("speed protected");

        carTrain.setInsuraceTrain(insuraceTrain);
        userTrain.setCarTrain(carTrain);
        return userTrain;
    }

    private static final UserTrain USER_TRAIN = getUserPra();
    private static final UserTrain USER_OF_NULL = null;

    /**
     * 尝试获取用户的用户名称，不存在则返回默认值
     */
    @Test
    public void getUserName() {
        String userName = Optional.ofNullable(USER_TRAIN)
                .orElseGet(UserTrain::new)
                .getUserName();
        System.out.println("正常的用户名：" + userName);

        String nullUserName = Optional.ofNullable(USER_OF_NULL)
                .orElseGet(UserTrain::new)
                .getUserName();
        System.out.println("空的用户名：" + nullUserName);
    }

    /**
     * 尝试获取用户的carName,不存在则返回null
     */
    @Test
    public void getCarName() {
        String carName = Optional.ofNullable(USER_TRAIN)
                .map(UserTrain::getCarTrain)
                .map(CarTrain::getCarName)
                .orElse(null);
        System.out.println("正常的carName：" + carName);

        String nullCarName = Optional.ofNullable(USER_OF_NULL)
                .map(UserTrain::getCarTrain)
                .map(CarTrain::getCarName)
                .orElse(null);
        System.out.println("空的carName：" + nullCarName);
    }

    /**
     * 用户名存在的时候则转为大写
     */
    @Test
    public void convertUsernameToUppercase() {
        USER_TRAIN.setUserName("abcd");
        Optional.ofNullable(USER_TRAIN)
                .map(UserTrain::getUserName)
                .ifPresent(userName -> System.out.println(userName.toUpperCase()));

        Optional.ofNullable(USER_OF_NULL)
                .map(UserTrain::getUserName)
                .ifPresent(nullUserName -> System.out.println(nullUserName.toUpperCase()));
    }

    /**
     * 过滤出来用户名称是张三的用户
     */
    @Test
    public void filterByUsername() {

        Optional.ofNullable(USER_TRAIN)
                .filter(userTrain -> Objects.equals(userTrain.getUserName(), "张三"))
                .map(UserTrain::getUserName)
                .ifPresent(System.out::println);

        Optional.ofNullable(USER_OF_NULL)
                .filter(userTrain -> Objects.equals(userTrain.getUserName(), "张三"))
                .map(UserTrain::getUserName)
                .ifPresent(System.out::println);

        USER_TRAIN.setUserName("lisi");
        String userName = Optional.ofNullable(USER_TRAIN)
                .filter(userTrain -> Objects.equals(userTrain.getUserName(), "张三"))
                .map(UserTrain::getUserName)
                .orElse("");
        System.out.println(userName);
    }


    /**
     * 将张三的用户名称更改为李四
     */
    @Test
    public void modifyUsername() {
        String contrastiveUserName = "张三";
        UserTrain newUserTrain = Optional.ofNullable(USER_TRAIN)
                .map(userTrain -> {
                    if (Objects.equals(userTrain.getUserName(), contrastiveUserName)) {
                        userTrain.setUserName("李四");
                    }
                    return userTrain;
                })
                .orElseGet(UserTrain::new);
        System.out.println(newUserTrain);

        Optional.ofNullable(USER_TRAIN)
                .filter(user -> Objects.equals(user.getUserName(), contrastiveUserName))
                .ifPresent(user -> {
                    user.setUserName("李四");
                });
        System.out.println("对象" + USER_TRAIN);
    }


}
