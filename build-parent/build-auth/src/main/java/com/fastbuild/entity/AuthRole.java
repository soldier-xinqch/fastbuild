package com.fastbuild.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xinqch
 * @since 2018-01-18
 */
@TableName("auth_role")
public class AuthRole extends Model<AuthRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 授权用户角色
     */
	private String id;
    /**
     * 角色类型
     */
	@TableField("role_type")
	private String roleType;
    /**
     * 角色名称
     */
	@TableField("role_name")
	private String roleName;
    /**
     * 角色 授权信息
     */
	@TableField("role_auth")
	private String roleAuth;
    /**
     * 角色状态
     */
	private String status;
    /**
     * 扩展字段
     */
	private String bak;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleAuth() {
		return roleAuth;
	}

	public void setRoleAuth(String roleAuth) {
		this.roleAuth = roleAuth;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBak() {
		return bak;
	}

	public void setBak(String bak) {
		this.bak = bak;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AuthRole{" +
			", id=" + id +
			", roleType=" + roleType +
			", roleName=" + roleName +
			", roleAuth=" + roleAuth +
			", status=" + status +
			", bak=" + bak +
			"}";
	}
}
