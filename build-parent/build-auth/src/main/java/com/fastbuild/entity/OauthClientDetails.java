package com.fastbuild.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author xinqch
 * @since 2018-01-22
 */
@TableName("oauth_client_details")
public class OauthClientDetails extends Model<OauthClientDetails> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
	private String id;
    /**
     * 客户端ID
     */
	@TableField("client_id")
	private String clientId;
    /**
     * 客户端名称
     */
	@TableField("client_name")
	private String clientName;
    /**
     * 资源ID
     */
	@TableField("resource_ids")
	private String resourceIds;
    /**
     * 客户端密码
     */
	@TableField("client_secret")
	private String clientSecret;
    /**
     * 授权动作，read 等
     */
	private String scope;
    /**
     * 鉴权类型-"authorization_code","password","implicit","client_credentials"
     */
	@TableField("authorized_grant_types")
	private String authorizedGrantTypes;
    /**
     * 重定向url
     */
	@TableField("web_server_redirect_uri")
	private String webServerRedirectUri;
	private String authorities;
    /**
     * token 过期时间
     */
	@TableField("access_token_validity")
	private Integer accessTokenValidity;
    /**
     * 刷新token 过期时间
     */
	@TableField("refresh_token_validity")
	private Integer refreshTokenValidity;
	@TableField("additional_information")
	private String additionalInformation;
	private String autoapprove;
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

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getAutoapprove() {
		return autoapprove;
	}

	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
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
		return "OauthClientDetails{" +
			", id=" + id +
			", clientId=" + clientId +
			", clientName=" + clientName +
			", resourceIds=" + resourceIds +
			", clientSecret=" + clientSecret +
			", scope=" + scope +
			", authorizedGrantTypes=" + authorizedGrantTypes +
			", webServerRedirectUri=" + webServerRedirectUri +
			", authorities=" + authorities +
			", accessTokenValidity=" + accessTokenValidity +
			", refreshTokenValidity=" + refreshTokenValidity +
			", additionalInformation=" + additionalInformation +
			", autoapprove=" + autoapprove +
			", registerTime=" + registerTime +
			", updateTime=" + updateTime +
			", freezeTime=" + freezeTime +
			", bak=" + bak +
			"}";
	}
}
