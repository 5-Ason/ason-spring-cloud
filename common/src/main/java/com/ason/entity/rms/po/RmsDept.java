package com.ason.entity.rms.po;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @since 2017-09-07
 */
@TableName("rms_dept")
public class RmsDept extends Model<RmsDept> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 部门名称
     */
	private String name;
    /**
     * 备注说明
     */
	private String remark;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "RmsDept{" +
			"id=" + id +
			", name=" + name +
			", remark=" + remark +
			"}";
	}
}
