package com.xlh.cors.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2020年12月05日 10:43 胡晓磊 Exp $
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -9170823711586249377L;

    private Long id;
    private String username;
    private Integer age;
}
