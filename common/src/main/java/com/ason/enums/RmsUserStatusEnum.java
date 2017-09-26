package com.ason.enums;

/**
 * Created by Ason on 2017-09-10.
 */
public enum RmsUserStatusEnum {
    OPEN(1, "启用"),
    FREEZE(2, "冻结"),
    DELETE(3, "删除");

    private Integer status;
    private String remark;

    RmsUserStatusEnum(Integer status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public static RmsUserStatusEnum getByStatus(int status) {
        for (RmsUserStatusEnum ruse : RmsUserStatusEnum.values()) {
            if (ruse.getStatus() == status) {
                return ruse;
            }
        }
        return null;
    }
}
