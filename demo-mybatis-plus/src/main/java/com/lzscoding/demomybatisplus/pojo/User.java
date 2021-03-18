package com.lzscoding.demomybatisplus.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * user
 * @author 
 */
@Data
@Builder
public class User implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    private static final long serialVersionUID = 1L;
}