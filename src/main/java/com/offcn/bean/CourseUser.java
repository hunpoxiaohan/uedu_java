package com.offcn.bean;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CourseUser {

    private Integer id;
    private Integer cid;
    private Integer uid;
    private Course course;
    private User user;
}
