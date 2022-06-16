package com.offcn.bean;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String name,phone,username,password,createtime,picture;
    private Integer uid,age,sex,status,role;
}
