package com.offcn.bean;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CourseDetail {

    private Integer id;
    private String name;
    private String type;
    private String url;
    private String start_data;
    private Integer cid;
}
