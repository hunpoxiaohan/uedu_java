package com.offcn.bean;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Course {
    private int cid , courseType,status;
    private double coursePrice;
    private String courseName,descs,courseImage,courseVideo,createTime;

    private List<CourseDetail> detailList;
}
