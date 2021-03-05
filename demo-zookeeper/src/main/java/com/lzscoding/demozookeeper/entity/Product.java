package com.lzscoding.demozookeeper.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 180626
 */
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Integer stock;
    private Integer version;

}
