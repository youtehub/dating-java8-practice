package com.lingyejun.dating.chap9;

import lombok.*;

/**
 * @Author: lingyejun
 * @Date: 2019/12/22
 * @Describe:
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class House {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
