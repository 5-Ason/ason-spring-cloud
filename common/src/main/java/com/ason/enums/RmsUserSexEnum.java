package com.ason.enums;

/**
 * Created by Ason on 2017-09-10.
 */
public enum RmsUserSexEnum {
    MAN(1, "男"),
    WOMAN(2, "女");

    private Integer status;
    private String remark;

    RmsUserSexEnum(Integer status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public static RmsUserSexEnum getByStatus(int status) {
        for (RmsUserSexEnum ruse : RmsUserSexEnum.values()) {
            if (ruse.getStatus() == status) {
                return ruse;
            }
        }
        return null;
    }
}
