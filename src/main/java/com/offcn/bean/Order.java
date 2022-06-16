package com.offcn.bean;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private String courseName,orderId;
    private double price;
    private Integer cid,uid;
}
