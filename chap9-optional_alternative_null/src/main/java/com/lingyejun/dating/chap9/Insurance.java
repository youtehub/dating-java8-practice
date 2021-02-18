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
public class Insurance {

    private String insuranceName;

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }
}
