package com.lingyejun.dating.chap9.practice;

import org.junit.Test;

import java.util.Optional;

/**
 * 练习Optional
 *
 * @author yiyh
 * @date 2021-02-17 21:25
 */
public class OptionalTest {


    /**
     * 根据用户名获取房产保险名称,错误方式
     *
     * @param userTrain 用户信息
     * @return 保险名称
     */
    public String getErrorInsuranceName(UserTrain userTrain) {
        return userTrain.getCarPra().getInsuracePra().getInsuranceName();
    }

    /**
     * 根据用户名获取房产保险名称,原始方式
     *
     * @param userTrain 用户信息
     * @return 保险名称
     */
    public String getInsuranceName(UserTrain userTrain) {
        if (userTrain != null) {
            CarPra carPra = userTrain.getCarPra();
            if (carPra != null) {
                InsuracePra insuracePra = carPra.getInsuracePra();
                if (insuracePra != null) {
                    return insuracePra.getInsuranceName();
                }
            }
        }
        return "not found";
    }

    /**
     * 根据用户名获取房产保险名称,常见方式
     *
     * @param userTrain 用户信息
     * @return 保险名称
     */
    public String getInsuranceNameBak(UserTrain userTrain) {
        if (userTrain == null) {
            return "not found";
        }
        if (userTrain.getCarPra() == null) {
            return "not found";
        }
        if (userTrain.getCarPra().getInsuracePra() == null) {
            return "not found";
        }
        return userTrain.getCarPra().getInsuracePra().getInsuranceName();
    }


    /**
     * 获取汽车里的保险对象
     */
    @Test
    public void getCar() {
        UserTrain userTrain = new UserTrain();
        CarPra carPra = new CarPra();
        carPra.setCarName("red car");

        InsuracePra insuracePra = new InsuracePra();
        insuracePra.setInsuranceName("speed protected");

        carPra.setInsuracePra(insuracePra);
        userTrain.setCarPra(carPra);

        System.out.println(getInsuranceName(null));
    }

    /**
     * 练习使用Optional.of
     */
    @Test
    public void trainOptionalOf() {
        Optional<Object> optional01 = Optional.of(null);
        Optional<Object> optional02 = Optional.ofNullable(null);

        System.out.println();
    }

    /**
     * 练习使用Optional.get
     */
    @Test
    public void trainOptionalGet() {
        UserTrain userTrain = getUserPra();

        Optional<UserTrain> optional01 = Optional.ofNullable(userTrain);
        UserTrain userTrain2 = optional01.get();
        System.out.println(userTrain2);

        UserTrain userTrain1 = null;
        Optional<UserTrain> optional02 = Optional.ofNullable(userTrain1);
        UserTrain userTrain3 = optional02.get();
        System.out.println(userTrain3);

    }

    private UserTrain getUserPra() {
        UserTrain userTrain = UserTrain.builder().userName("张三").build();
        CarPra carPra = new CarPra();
        carPra.setCarName("red car");

        InsuracePra insuracePra = new InsuracePra();
        insuracePra.setInsuranceName("speed protected");

        carPra.setInsuracePra(insuracePra);
        userTrain.setCarPra(carPra);
        return userTrain;
    }

    /**
     * 练习使用Optional.orElse
     */
    @Test
    public void trainOptionalOrElse() {
        UserTrain userTrain = getUserPra();

        Optional<UserTrain> optional01 = Optional.ofNullable(userTrain);
        UserTrain userTrain2 = optional01.orElse(new UserTrain());
        System.out.println(userTrain2);

        UserTrain userTrain1 = null;
        Optional<UserTrain> optional02 = Optional.ofNullable(userTrain1);
        UserTrain userTrain3 = optional02.orElse(new UserTrain());
        System.out.println(userTrain3);

    }

    /**
     * 练习使用Optional.orElseGet
     */
    @Test
    public void trainOptionalOrElseGet() {
        UserTrain userTrain = getUserPra();

        Optional<UserTrain> optional01 = Optional.ofNullable(userTrain);
        UserTrain userTrain2 = optional01.orElseGet(() -> new UserTrain());
        System.out.println(userTrain2);

        UserTrain userTrain1 = null;
        Optional<UserTrain> optional02 = Optional.ofNullable(userTrain1);
        UserTrain userTrain3 = optional02.orElseGet(UserTrain::new);
        System.out.println(userTrain3);

        Optional<UserTrain> optional03 = Optional.ofNullable(userTrain1);
        UserTrain userTrain4 = optional03.orElseGet(() -> UserTrain.builder().userName("李四").build());
        UserTrain userTrain5 = optional03.orElseGet(UserTrain.builder().userName("李四")::build);
        System.out.println(userTrain5);

    }

    /**
     * 对比，Optional.orElseGet 和 Optional.orElse
     */
    @Test
    public void contrastOrElseAndOrElseGet() {
        UserTrain userTrain = getUserPra();

        Optional<UserTrain> optional01 = Optional.ofNullable(userTrain);
        UserTrain userTrain2 = optional01.orElseGet(() -> new UserTrain());
        System.out.println(userTrain2);

        UserTrain userTrain1 = null;
        Optional<UserTrain> optional02 = Optional.ofNullable(userTrain1);
        UserTrain userTrain3 = optional02.orElseGet(UserTrain::new);
        System.out.println(userTrain3);

        Optional<UserTrain> optional03 = Optional.ofNullable(userTrain1);
        UserTrain userTrain4 = optional03.orElseGet(() -> UserTrain.builder().userName("李四").build());
        UserTrain userTrain5 = optional03.orElseGet(UserTrain.builder().userName("李四")::build);
        System.out.println(userTrain5);

    }

    public UserTrain orElseGetUserTrain(){
        UserTrain userTrain = new UserTrain();
        userTrain.setUserName("李四");
        System.out.println(userTrain.getUserName());
        return userTrain;
    }


}
