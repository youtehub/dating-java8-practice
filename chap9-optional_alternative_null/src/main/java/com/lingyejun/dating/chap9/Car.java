package com.lingyejun.dating.chap9;

import lombok.*;

/**
 * @Author: lingyejun
 * @Date: 2019/12/21
 * @Describe: 
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car {

    private String carName;

    private Insurance insurance;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
