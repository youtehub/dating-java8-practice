package com.lingyejun.dating.chap9.practice01;

import org.junit.Test;

import java.util.Objects;
import java.util.Optional;

/**
 * 学习Optional
 *
 * @author yiyh
 * @date 2021-02-17 21:25
 */
public class LearnOptionalTest {


    /**
     * 根据用户名获取房产保险名称,错误方式
     *
     * @param userTrain 用户信息
     * @return 保险名称
     */
    public String getErrorInsuranceName(UserTrain userTrain) {
        return userTrain.getCarTrain().getInsuraceTrain().getInsuranceName();
    }

    /**
     * 根据用户名获取房产保险名称,原始方式
     *
     * @param userTrain 用户信息
     * @return 保险名称
     */
    public String getInsuranceName(UserTrain userTrain) {
        if (userTrain != null) {
            CarTrain carTrain = userTrain.getCarTrain();
            if (carTrain != null) {
                InsuraceTrain insuraceTrain = carTrain.getInsuraceTrain();
                if (insuraceTrain != null) {
                    return insuraceTrain.getInsuranceName();
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
        if (userTrain.getCarTrain() == null) {
            return "not found";
        }
        if (userTrain.getCarTrain().getInsuraceTrain() == null) {
            return "not found";
        }
        return userTrain.getCarTrain().getInsuraceTrain().getInsuranceName();
    }


    /**
     * 获取汽车里的保险对象
     */
    @Test
    public void getCar() {
        UserTrain userTrain = new UserTrain();
        CarTrain carTrain = new CarTrain();
        carTrain.setCarName("red car");

        InsuraceTrain insuraceTrain = new InsuraceTrain();
        insuraceTrain.setInsuranceName("speed protected");

        carTrain.setInsuraceTrain(insuraceTrain);
        userTrain.setCarTrain(carTrain);

        System.out.println(getInsuranceName(null));
    }

    /**
     * 学习使用Optional.of
     */
    @Test
    public void studyOptionalOf() {
        Optional<Object> optional01 = Optional.of(null);
        Optional<Object> optional02 = Optional.ofNullable(null);

        System.out.println();
    }

    /**
     * 学习使用Optional.get
     */
    @Test
    public void studyOptionalGet() {
        UserTrain userTrain = getUserPra();

        Optional<UserTrain> optional01 = Optional.ofNullable(userTrain);
        UserTrain userTrain2 = optional01.get();
        System.out.println(userTrain2);

        UserTrain userTrain1 = null;
        Optional<UserTrain> optional02 = Optional.ofNullable(userTrain1);
        UserTrain userTrain3 = optional02.get();
        System.out.println(userTrain3);

    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    private UserTrain getUserPra() {
        UserTrain userTrain = UserTrain.builder().userName("张三").build();
        CarTrain carTrain = new CarTrain();
        carTrain.setCarName("red car");

        InsuraceTrain insuraceTrain = new InsuraceTrain();
        insuraceTrain.setInsuranceName("speed protected");

        carTrain.setInsuraceTrain(insuraceTrain);
        userTrain.setCarTrain(carTrain);
        return userTrain;
    }

    /**
     * 学习使用Optional.orElse
     */
    @Test
    public void studyOptionalOrElse() {
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
     * 学习使用Optional.orElseGet
     */
    @Test
    public void studyOptionalOrElseGet() {
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
        UserTrain userTrain2 = optional01.orElse(orElseGetUserTrain());
        System.out.println(userTrain2);

        Optional<UserTrain> optional03 = Optional.ofNullable(userTrain);
        UserTrain userTrain4 = optional03.orElseGet(() -> {
            return UserTrain.builder().userName("李四").build();
        });
        UserTrain userTrain5 = optional03.orElseGet(UserTrain.builder().userName("李四")::build);
        System.out.println(userTrain4);
    }

    /**
     * 验证orElse()是否为直接加载
     *
     * @return 用户对象
     */
    public UserTrain orElseGetUserTrain() {
        UserTrain userTrain = new UserTrain();
        userTrain.setUserName("李四");
        System.out.println(userTrain.getUserName());
        return userTrain;
    }


    /**
     * 学习使用Optional.orElseThrow
     */
    @Test
    public void studyOptionalorElseThrow() {
        UserTrain userTrainOfNull = null;
        UserTrain userTrain01 = Optional.ofNullable(userTrainOfNull).orElseThrow(() -> new ArrayIndexOutOfBoundsException());
        UserTrain userTrain02 = Optional.ofNullable(userTrainOfNull).orElseThrow(ArrayIndexOutOfBoundsException::new);

    }

    /**
     * 学习使用Optional.ifPresent
     */
    @Test
    public void studyOptionalIsPresent() {
        UserTrain userTrain = new UserTrain();
        UserTrain userTrainOfNull = null;

        Optional.ofNullable(userTrain).ifPresent(userTrainJudge -> System.out.println(userTrainJudge.getUserName() + " If Present"));
        Optional.ofNullable(userTrainOfNull).ifPresent(nullUserTrain -> System.out.println(nullUserTrain.getUserName() + " If Present"));

    }

    /**
     * 学习使用Optional.map
     */
    @Test
    public void studyOptionalMap() {
        UserTrain userTrain = new UserTrain();
        UserTrain userTrainOfNull = null;

        String userName = Optional.ofNullable(userTrain)
                .map(x -> userTrain.getUserName() + " Get By Map")
                .get();
        System.out.println(userName);

        String nullUserName = Optional.ofNullable(userTrainOfNull)
                .map(x -> userTrain.getUserName() + " Get By Map")
                .get();
        System.out.println(nullUserName);
    }

    /**
     * 学习使用Optional.flatMap
     */
    @Test
    public void studyOptionalFlatMap() {
        UserTrain userHaveHouse = getUserTrain();

        //使用Optional.map拿取对象
        Optional<HouseTrain> oph = Optional.ofNullable(userHaveHouse)
                .map(UserTrain::getHouseTrain)
                .get();

        //使用Optional.flatMap拿取对象
        HouseTrain houseTrain1 = Optional.ofNullable(userHaveHouse)
                .flatMap(UserTrain::getHouseTrain)
                .get();
        System.out.println(houseTrain1);
    }

    /**
     * 获取王五这个用户
     *
     * @return 用户信息
     */
    private UserTrain getUserTrain() {
        HouseTrain houseTrain = HouseTrain.builder()
                .address("Park Black Three")
                .build();
        UserTrain userHaveHouse = UserTrain.builder()
                .userName("wangwu")
                .houseTrain(Optional.of(houseTrain))
                .build();
        return userHaveHouse;
    }

    /**
     * 学习使用Optional.filter
     */
    @Test
    public void studyOptionalFilter() {
        UserTrain userHaveHouse = getUserTrain();
        Optional.ofNullable(userHaveHouse)
                .filter(uhh -> Objects.equals(uhh.getUserName(), "wangwu"))
                .flatMap(UserTrain::getHouseTrain)
                .ifPresent(uuh -> System.out.println(uuh.getAddress() + " Plus By Filter"));
    }

}
