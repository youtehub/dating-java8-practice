package com.lingyejun.dating.chap5;

import lombok.*;

/**
 * @Author: lingyejun
 * @Date: 2019/11/16
 * @Describe:
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@ToString
public class House {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public House(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "House{" +
                "address='" + address + '\'' +
                '}';
    }
}
