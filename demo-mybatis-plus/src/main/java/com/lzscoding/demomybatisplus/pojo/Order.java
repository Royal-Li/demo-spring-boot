package com.lzscoding.demomybatisplus.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * order
 * @author 
 */
@Data
public class Order implements Serializable {
    private Integer id;

    private String orderId;

    private Integer productId;

    private static final long serialVersionUID = 1L;
}