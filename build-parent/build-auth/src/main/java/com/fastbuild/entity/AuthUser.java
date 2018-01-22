package com.fastbuild.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.List;

/**
 * <p>
 * 授权用户表
 * </p>
 *
 * @author xinqch
 * @since 2018-01-18
 */
@TableName("auth_user")
public class AuthUser extends Model<AuthUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
	private String id;
    /**
     * 授权用户名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 授权用户密码
     */
	@TableField("user_password")
	private String userPassword;
    /**
     * 授权用户电子邮箱
     */
	@TableField("user_email")
	private String userEmail;
    /**
     * 授权用户手机号
     */
	@TableField("user_mobile")
	private String userMobile;
    /**
     * 授权用户真实姓名
     */
	@TableField("real_name")
	private String realName;
    /**
     * 授权用户信息
     */
	@TableField("user_message")
	private String userMessage;
    /**
     * 授权用户状态
     */
	@TableField("user_status")
	private String userStatus;
    /**
     * 用户冻结时间
     */
	@TableField("freeze_time")
	private Date freezeTime;
    /**
     * 用户注册时间
     */
	@TableField("register_time")
	private Date registerTime;
    /**
     * 用户修改时间
     */
	@TableField("update_time")
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Date getFreezeTime() {
		return freezeTime;
	}

	public void setFreezeTime(Date freezeTime) {
		this.freezeTime = freezeTime;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AuthUser{" +
			", id=" + id +
			", userName=" + userName +
			", userPassword=" + userPassword +
			", userEmail=" + userEmail +
			", userMobile=" + userMobile +
			", realName=" + realName +
			", userMessage=" + userMessage +
			", userStatus=" + userStatus +
			", freezeTime=" + freezeTime +
			", registerTime=" + registerTime +
			", updateTime=" + updateTime +
			"}";
	}
}
