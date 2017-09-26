package com.ason.entity;

import java.io.Serializable;

/**
 * 自定义实体父类 ， 这里可以放一些公共字段信息
 */
public class SuperEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
