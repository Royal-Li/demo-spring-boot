package com.lzscoding.demozookeeper.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * order
 *
 * @author
 */
@Data
public class Order implements Serializable {
    private Integer id;

    private String userId;

    private Integer productId;

    private static final long serialVersionUID = 1L;
}