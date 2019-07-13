package com.maxmall.common.security;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * The class Security user.
 *
 * @author maxmall.net@gmail.com
 */
public class SecurityUser implements UserDetails {
	private static final long serialVersionUID = 4872628781561412463L;

	//用户权限列表
	private Collection<? extends GrantedAuthority> authorities = Lists.newArrayList();

	/**
	 * 用户id.
	 */
	private Long userId;
	/**
	 * 用户昵称.
	 */
	private String nickName;
	/**
	 * 用户登录名.
	 */
	private String loginName;
	/**
	 * 用户密码.
	 */
	private String loginPwd;
	/**
	 * 状态.
	 */
	private Integer status;
	/**
	 * 是否主账户.
	 */
	private Boolean isMaster;
	/**
	 * 商户id.
	 */
	private Long merchantId;
	/**
	 * 商户名称.
	 */
	private String merchantName;
	/**
	 * 商户主图片.
	 */
	private String merchantPic;
	/**
	 * 商户编号.
	 */
	private String merchantSn;

	public SecurityUser(Long userId, String loginName, String loginPwd, String nickName, Integer status) {
		this.setUserId(userId);
		this.setLoginName(loginName);
		this.setLoginPwd(loginPwd);
		this.setNickName(nickName);
		this.setStatus(status);
	}

	public SecurityUser(Long userId, String loginName, String loginPwd, String nickName, Integer status, Collection<GrantedAuthority> grantedAuthorities) {
		this.setUserId(userId);
		this.setLoginName(loginName);
		this.setLoginPwd(loginPwd);
		this.setNickName(nickName);
		this.setStatus(status);
		this.authorities = grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.getLoginPwd();
	}

	@Override
	public String getUsername() {
		return this.getLoginName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {

		return this.status == 0;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(Boolean master) {
		isMaster = master;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantPic() {
		return merchantPic;
	}

	public void setMerchantPic(String merchantPic) {
		this.merchantPic = merchantPic;
	}

	public String getMerchantSn() {
		return merchantSn;
	}

	public void setMerchantSn(String merchantSn) {
		this.merchantSn = merchantSn;
	}
}
