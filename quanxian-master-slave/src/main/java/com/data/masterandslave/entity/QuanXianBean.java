package com.data.masterandslave.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by liyonglin on 2019-12-23 12:48
 */
@Data
public class QuanXianBean implements Serializable {
    private Integer userId;
    private Set<Integer> roleId;
    private Set<String> resources;
    private Long timevalue;
    private String token;
    private String userName;
}
