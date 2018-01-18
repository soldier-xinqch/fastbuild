package com.fastbuild.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 授权资源表
 * </p>
 *
 * @author xinqch
 * @since 2018-01-18
 */
@TableName("auth_resource")
public class AuthResource extends Model<AuthResource> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 资源ID
     */
	@TableField("resource_id")
	private String resourceId;
    /**
     * 资源名称
     */
	@TableField("resource_name")
	private String resourceName;
    /**
     * 资源地址
     */
	@TableField("resource_url")
	private String resourceUrl;
    /**
     * 资源类型
     */
	@TableField("resource_type")
	private String resourceType;
    /**
     * 资源简称
     */
	@TableField("resource_pre_name")
	private String resourcePreName;
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

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourcePreName() {
		return resourcePreName;
	}

	public void setResourcePreName(String resourcePreName) {
		this.resourcePreName = resourcePreName;
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
		return "AuthResource{" +
			", id=" + id +
			", resourceId=" + resourceId +
			", resourceName=" + resourceName +
			", resourceUrl=" + resourceUrl +
			", resourceType=" + resourceType +
			", resourcePreName=" + resourcePreName +
			", bak=" + bak +
			"}";
	}
}
