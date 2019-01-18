package entity;
/**
 * 管理员类
 * @author 我的袜子都是洞
 * @date: 2019-01-18
 */
public class Admin {
	// 管理员的id
	private int id;
	// 登陆名
	private String loginname;
	// 姓名
	private String username;
	// 密码
	private String password;
	// 身份证号
	private String identity;
	// 手机
	private String phone;
	public Admin() {
		super();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the loginname
	 */
	public String getLoginname() {
		return loginname;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the identity
	 */
	public String getIdentity() {
		return identity;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param loginname the loginname to set
	 */
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param identity the identity to set
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Admin [id=" + id + ", identity=" + identity + ", loginname=" + loginname + ", password=" + password + ", phone=" + phone + ", username=" + username + "]";
	}
	
}
