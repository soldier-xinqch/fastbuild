package com.fastbuild.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 授权客户端表
 * </p>
 *
 * @author xinqch
 * @since 2018-01-18
 */
@TableName("auth_client")
public class AuthClient extends Model<AuthClient> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
	private String id;
    /**
     * 客户端ID-区别主键ID
     */
	@TableField("client_id")
	private String clientId;
    /**
     * 客户端名称
     */
	@TableField("client_name")
	private String clientName;
    /**
     * 客户端密码
     */
	@TableField("client_password")
	private String clientPassword;
    /**
     * 鉴权类型-"authorization_code","password","implicit","client_credentials"
     */
	@TableField("auth_type")
	private String authType;
    /**
     * 授权动作
     */
	@TableField("auth_scope")
	private String authScope;
    /**
     * 过期时间
     */
	@TableField("expire_time")
	private Integer expireTime;
    /**
     * 注册时间
     */
	@TableField("register_time")
	private Date registerTime;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Date updateTime;
    /**
     * 冻结时间
     */
	@TableField("freeze_time")
	private Date freezeTime;
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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getAuthScope() {
		return authScope;
	}

	public void setAuthScope(String authScope) {
		this.authScope = authScope;
	}

	public Integer getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Integer expireTime) {
		this.expireTime = expireTime;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getFreezeTime() {
		return freezeTime;
	}

	public void setFreezeTime(Date freezeTime) {
		this.freezeTime = freezeTime;
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
		return "AuthClient{" +
			", id=" + id +
			", clientId=" + clientId +
			", clientName=" + clientName +
			", clientPassword=" + clientPassword +
			", authType=" + authType +
			", authScope=" + authScope +
			", expireTime=" + expireTime +
			", registerTime=" + registerTime +
			", updateTime=" + updateTime +
			", freezeTime=" + freezeTime +
			", bak=" + bak +
			"}";
	}
}
