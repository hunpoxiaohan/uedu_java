package com.offcn.utils;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResultVO {
    private int code;
    private String message;
    private Object data;
}
