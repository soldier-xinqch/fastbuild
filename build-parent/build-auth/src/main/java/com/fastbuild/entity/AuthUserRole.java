package com.fastbuild.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 授权用户角色关联表
 * </p>
 *
 * @author xinqch
 * @since 2018-01-18
 */
@TableName("auth_user_role")
public class AuthUserRole extends Model<AuthUserRole> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 授权用户ID
     */
	@TableField("user_id")
	private String userId;
    /**
     * 授权角色ID
     */
	@TableField("role_id")
	private String roleId;
    /**
     * 授权角色名称
     */
	@TableField("role_name")
	private String roleName;
    /**
     * 授权角色类型
     */
	@TableField("role_type")
	private String roleType;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AuthUserRole{" +
			", id=" + id +
			", userId=" + userId +
			", roleId=" + roleId +
			", roleName=" + roleName +
			", roleType=" + roleType +
			", createTime=" + createTime +
			"}";
	}
}
