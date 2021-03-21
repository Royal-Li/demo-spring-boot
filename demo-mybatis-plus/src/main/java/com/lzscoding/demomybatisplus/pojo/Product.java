package com.lzscoding.demomybatisplus.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * product
 * @author
 */
@Data
public class Product implements Serializable {
    private Integer id;

    private String name;

    private Integer stock;

    private Byte version;

    private static final long serialVersionUID = 1L;
}