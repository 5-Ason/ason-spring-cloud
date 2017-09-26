package com.ason.entity.rms.po;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @since 2017-09-07
 */
@TableName("rms_user")
public class RmsUser extends Model<RmsUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
    /**
     * 登录账号(邮箱格式)
     */
	@Email(message = "账号(邮箱)格式不正确")
	@NotBlank(message = "登录账号不能为空")
	private String account;
    /**
     * 密码
     */
	@NotBlank(message = "密码不能为空")
	@Size(min = 6, max = 20, message = "密码长度为6-20")
	private String password;
    /**
     * 姓名
     */
	@NotBlank(message = "姓名不能为空")
	private String name;
    /**
     * 性别(1：男，2：女)
     */
	@NotNull(message = "性别不能为空")
	private Integer sex;
    /**
     * 生日
     */
	private String birthday;
    /**
     * 电话
     */
	private String phone;
    /**
     * 角色id
     */
	@TableField("role_id")
	private Integer roleId;
    /**
     * 部门id
     */
	@TableField("dept_id")
	private Integer deptId;
    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
	private Integer status;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Long createTime;
    /**
     * 创建者id
     */
	@NotNull(message = "创建者不能为空")
	@TableField("create_user_id")
	private Integer createUserId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 密码盐.
	 * @return
	 */
	public String getSalt() {
		return this.account + this.createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "RmsUser{" +
			"id=" + id +
			", account=" + account +
			", password=" + password +
			", name=" + name +
			", sex=" + sex +
			", birthday=" + birthday +
			", phone=" + phone +
			", roleId=" + roleId +
			", deptId=" + deptId +
			", status=" + status +
			", createTime=" + createTime +
			", createUserId=" + createUserId +
			"}";
	}
}
