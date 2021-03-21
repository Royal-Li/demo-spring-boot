package com.lzscoding.demomybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * order
 *
 * @author
 */
@Data
public class Order implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private Integer productId;

    private static final long serialVersionUID = 1L;
}