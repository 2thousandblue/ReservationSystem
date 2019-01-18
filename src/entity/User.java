package entity;
/**
 * 用户类
 * @author 我的袜子都是洞
 * @date: 2019-01-18
 */
public class User {
	// uid
	private int id;
	// 用户名
	private String loginname;
	// 密码
	private String password;
	// 姓名
	private String username;
	// 身份证号
	private String identity;
	// 性别
	private String sex;
	// 手机号
	private String phone;
	// 电子邮箱
	private String email;
	// 地址
	private String address;
	public User() {
		super();
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the loginname
	 */
	public String getLoginname() {
		return loginname;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return the identity
	 */
	public String getIdentity() {
		return identity;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param loginname the loginname to set
	 */
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param identity the identity to set
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "User [address=" + address + ", email=" + email + ", identity=" + identity + ", loginname=" + loginname + ", password=" + password + ", phone=" + phone + ", sex=" + sex + ", username="
				+ username + "]";
	}
	
}
