package com.fastbuild.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 授权角色资源关系表
 * </p>
 *
 * @author xinqch
 * @since 2018-01-18
 */
@TableName("auth_role_resource")
public class AuthRoleResource extends Model<AuthRoleResource> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 角色ID
     */
	@TableField("role_id")
	private String roleId;
    /**
     * 角色类型
     */
	@TableField("role_type")
	private String roleType;
    /**
     * 资源ID
     */
	@TableField("resource_id")
	private String resourceId;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AuthRoleResource{" +
			", id=" + id +
			", roleId=" + roleId +
			", roleType=" + roleType +
			", resourceId=" + resourceId +
			"}";
	}
}
