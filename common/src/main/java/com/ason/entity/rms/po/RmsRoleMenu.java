package com.ason.entity.rms.po;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @since 2017-09-07
 */
@TableName("rms_role_menu")
public class RmsRoleMenu extends Model<RmsRoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 角色id
     */
	@TableField("role_id")
	private Integer roleId;
    /**
     * 菜单id
     */
	@TableField("menu_id")
	private Integer menuId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "RmsRoleMenu{" +
			"id=" + id +
			", roleId=" + roleId +
			", menuId=" + menuId +
			"}";
	}
}
