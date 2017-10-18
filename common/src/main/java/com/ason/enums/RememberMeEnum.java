package com.ason.enums;

/**
 * Created by Ason on 2017/10/18.
 */
public enum RememberMeEnum {
    /**
     * on
     */
    ON(1, "on"),
    /**
     *
     * off
     */
    OFF(2, "off");

    private Integer status;
    private String remark;

    RememberMeEnum(Integer status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public static RememberMeEnum getByStatus(int status) {
        for (RememberMeEnum rme : RememberMeEnum.values()) {
            if (rme.getStatus() == status) {
                return rme;
            }
        }
        return null;
    }
}
