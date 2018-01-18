package com.fastbuild.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 授权客户端资源表
 * </p>
 *
 * @author xinqch
 * @since 2018-01-18
 */
@TableName("auth_client_resource")
public class AuthClientResource extends Model<AuthClientResource> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 客户端ID
     */
	@TableField("client_id")
	private String clientId;
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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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
		return "AuthClientResource{" +
			", id=" + id +
			", clientId=" + clientId +
			", resourceId=" + resourceId +
			"}";
	}
}
