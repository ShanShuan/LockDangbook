package com.shanshuan.book.entity;
/**
 *Created by Zifeng Wang 2016-6-27����4:22:50
 */
public class User {
	private int id;
	private String email;
	private boolean emailVerify;
	private String emailVerifyCode;
	private String lastLoginIp;
	private long lastLoginTime;
	private String nickname;
	private String password;
	private int userIntegral;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEmailVerify() {
		return emailVerify;
	}
	public void setEmailVerify(boolean emailVerify) {
		this.emailVerify = emailVerify;
	}
	public String getEmailVerifyCode() {
		return emailVerifyCode;
	}
	public void setEmailVerifyCode(String emailVerifyCode) {
		this.emailVerifyCode = emailVerifyCode;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserIntegral() {
		return userIntegral;
	}
	public void setUserIntegral(int userIntegral) {
		this.userIntegral = userIntegral;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", emailVerify="
				+ emailVerify + ", emailVerifyCode=" + emailVerifyCode
				+ ", lastLoginIp=" + lastLoginIp + ", lastLoginTime="
				+ lastLoginTime + ", nickname=" + nickname + ", password="
				+ password + ", userIntegral=" + userIntegral + "]";
	}
	public User(int id, String email, boolean emailVerify,
			String emailVerifyCode, String lastLoginIp, long lastLoginTime,
			String nickname, String password, int userIntegral) {
		super();
		this.id = id;
		this.email = email;
		this.emailVerify = emailVerify;
		this.emailVerifyCode = emailVerifyCode;
		this.lastLoginIp = lastLoginIp;
		this.lastLoginTime = lastLoginTime;
		this.nickname = nickname;
		this.password = password;
		this.userIntegral = userIntegral;
	}
	public User() {
		super();
	}
	
}
